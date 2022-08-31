package com.epam.bookstore.config.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.epam.bookstore.config.jwt.JwtToken;
import com.epam.bookstore.entity.User;
import com.epam.bookstore.exception.ApiException;
import com.epam.bookstore.exception.ResultCode;
import com.epam.bookstore.service.ServiceImpl.UserServiceImpl;
import com.epam.bookstore.config.threadLocal.LocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserServiceImpl userService;

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()) {
            return true;
        }

        String bearerToken = request.getHeader("Authorization");
        if (!StringUtils.hasText(bearerToken)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }

        if (!bearerToken.startsWith("Bearer")) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        String tokens[] = bearerToken.split(" ");
        if (!(tokens.length == 2)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        String token = tokens[1];

        //!!!!!!!!!!!!获取token验证???
        if (!JwtToken.verifyToken(token)){
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }


        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        Map<String, Claim> map = optionalMap.orElseThrow(() -> new ApiException(ResultCode.UNAUTHORIZED));

        //judge if user has permission
        boolean valid = this.hasPermission(scopeLevel.get(), map);

        if (valid) {
            this.setToThreadLocal(map);
        }
        return valid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalUser.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    private void setToThreadLocal(Map<String, Claim> map) {
        int uid = map.get("uid").asInt();
        int scope = map.get("scope").asInt();
        User user = userService.getUserValidationById(uid);
        LocalUser.set(user, scope);
    }

    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        //the scope of access required
        Integer level = scopeLevel.value();
        //user's scope
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new ApiException(ResultCode.FORBIDDEN);
        }
        return true;
    }

    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        // get Methods' scope level
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (scopeLevel == null) {
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }


}
