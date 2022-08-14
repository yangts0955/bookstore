package com.example.dockerandmysql.service.threadLocal;

import com.example.dockerandmysql.model.userValidation;

import java.util.HashMap;
import java.util.Map;

public class LocalUser {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(userValidation user, Integer scope) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("scope", scope);
        LocalUser.threadLocal.set(map);
    }

    public static void remove() {
        LocalUser.threadLocal.remove();
    }

    public static userValidation getUser(){
        Map<String, Object> map = LocalUser.threadLocal.get();
        userValidation user = (userValidation) map.get("user");
        return user;
    }

    public static Integer getScope() {
        Map<String, Object> map = LocalUser.threadLocal.get();
        Integer scope = (Integer)map.get("scope");
        return scope;
    }
}
