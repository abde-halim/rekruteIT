package com.springboot.rekruteIT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/me")
    public ResponseEntity<?> me(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())){
            return ResponseEntity.status(401).body("Unauthenticated");
        }
        Map<String,Object> resp = new HashMap<>();
        resp.put("name", auth.getName());
        resp.put("authorities", auth.getAuthorities());
        return ResponseEntity.ok(resp);
    }
}
