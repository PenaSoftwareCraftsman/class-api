package com.unitech.classapi.infrastructure.security;

import com.unitech.classapi.application.port.PendingUserPort;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.DecodedToken;
import com.unitech.classapi.domain.entity.User;
import com.unitech.classapi.infrastructure.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UserPort userPort;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            DecodedToken decodedToken = jwtUtil.decodeToken(token);
            User user = this.userPort.fetchByEmail(decodedToken.getEmail());
            if(user == null) throw new RuntimeException("User not founded");
            var authentication = new UsernamePasswordAuthenticationToken(user, "ROLE_" + user.getRole(), user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
