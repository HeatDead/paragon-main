package com.example.paragonmain.Filters;

import com.example.paragonmain.Services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Value("${auth.enabled}")
    private boolean enabled;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (!enabled)
            filterChain.doFilter(request, response);

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isBlank()) {
            System.out.println("SC_UNAUTHORIZED : " + authHeader);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else if (!checkAuthorization(authHeader)) {
            System.out.println("SC_FORBIDDEN");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else {
            System.out.println("do filter");
            filterChain.doFilter(request, response);
        }
    }

    private boolean checkAuthorization(String auth) {
        if (!auth.startsWith("Bearer ")){
            System.out.println(auth + " - " + "no 'Bearer'");
            return false;
        }

        String token = auth.substring(7);
        return tokenService.checkToken(token);
    }
}
