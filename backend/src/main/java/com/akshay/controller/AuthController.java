package com.akshay.controller;

import com.akshay.constant.LoggingConstants;
import com.akshay.request.AuthRequest;
import com.akshay.response.AuthResponse;
import com.akshay.service.auth.AuthService;
import io.swagger.v3.core.util.ReferenceTypeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    public static final String START_METHOD_LOG = "START: {},{}";
    //sign up

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> signUp(@RequestBody AuthRequest authRequest){


        var methodName = "AuthController:signUp";
        log.info(LoggingConstants.START_METHOD_LOG,methodName,authRequest);

        log.info(LoggingConstants.END_METHOD_LOG,methodName);

        return null;

    }

}
