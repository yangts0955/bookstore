package com.example.dockerandmysql.login.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.example.dockerandmysql.exception.ForbiddenException;
import com.example.dockerandmysql.exception.NotFoundException;
import com.example.dockerandmysql.exception.UnAuthenticateException;
import com.example.dockerandmysql.login.jwt.JwtToken;
import com.example.dockerandmysql.model.userValidation;
import com.example.dockerandmysql.service.UserService;
import com.example.dockerandmysql.service.threadLocal.LocalUser;
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

    public PermissionInterceptor(){
        super();
    }

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()){
            return false;
        }

        // test case, make a token for test!!
        String token = JwtToken.makeToken(1);
//        String token = JwtToken.makeToken(2, 1);   //with user's scope defined by 1
        System.out.println(token);

        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        System.out.println(optionalMap.get().keySet());
        Map<String, Claim> map = optionalMap.orElseThrow(() -> new UnAuthenticateException(10002));

        //judge if user has permission
        boolean valid = this.hasPermission(scopeLevel.get(), map);

        if (valid){
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

    private void setToThreadLocal(Map<String, Claim> map){
        int uid = map.get("uid").asInt();
        int scope = map.get("scope").asInt();
        userValidation user = userService.getUserValidationById(uid);
        LocalUser.set(user, scope);
    }

    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map){
        //the scope of access required
        Integer level = scopeLevel.value();
        //user's scope
        Integer scope = map.get("scope").asInt();
        if (level > scope){
            throw new ForbiddenException(10004);
        }
        return true;
    }

    private Optional<ScopeLevel> getScopeLevel(Object handler){
        // get Methods' scope level
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (scopeLevel == null){
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }



}
