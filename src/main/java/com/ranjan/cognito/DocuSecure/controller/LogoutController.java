package com.ranjan.cognito.DocuSecure.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller

public class LogoutController {
    
    @GetMapping("/custom-logout")
    public void customLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Invalidate Spring session
        HttpSession session=request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        //Call cognito session timeout url
        String clientId = "3j8oa6dcbpdm0c51mm5pdrtra9";
        String logoutUrl = "https://eu-west-22mxbswi4f.auth.eu-west-2.amazoncognito.com/logout"
                             + "?client_id=" + clientId
                             + "&logout_uri=" + URLEncoder.encode("http://localhost:8080/logged-out", StandardCharsets.UTF_8);
         response.sendRedirect(logoutUrl);
    
    }
    
}
