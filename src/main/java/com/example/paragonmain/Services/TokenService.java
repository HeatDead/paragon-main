package com.example.paragonmain.Services;

public interface TokenService {
    boolean checkToken(String token);
    String checkRole(String token);
}
