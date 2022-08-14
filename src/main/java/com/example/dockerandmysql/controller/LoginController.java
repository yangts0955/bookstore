package com.example.dockerandmysql.controller;

import com.example.dockerandmysql.model.userValidation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


//@RestController
//@RequestMapping("/login")
//public class LoginController {
//
//    @PostMapping("/")
//    public String login( @RequestBody userValidation user) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
//        // 尝试对传递的Authentication对象进行身份Authentication ，如果成功，则返回完全填充的Authentication对象（包括授予的权限）。
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        // 把authentication放到当前线程,便是认证完成
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String fastUUID = IdUtil.fastUUID();
//        // 把用户信息存到redis,并设置有效期
////        redisTemplate.opsForValue().set(fastUUID,authentication.getPrincipal(),6, TimeUnit.HOURS);
//        return ApiResponse.ofSuccess(Dict.create().set("tokenType","Bearer").set("token",fastUUID));
//    }
//}
