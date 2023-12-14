package com.enigma.shopeymarth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.shopeymarth.entity.AppUser;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String jwtScreet = "kunci";
    private final String appName = "Shopey Marth Application";

    public String generateToken(AppUser appUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtScreet.getBytes(StandardCharsets.UTF_8));
            String token = JWT.create()
                    .withIssuer(appName)// Info untuk application name yang kita buat
                    .withSubject(appUser.getId())// Menentukan object yang akan dibuat biasanya dari id
                    .withExpiresAt(Instant.now().plusSeconds(60))// Menetapkan waktu kadaluarsa token nanti, dalam sini kadaluarsanya adalah 60 detik setelah dibuat
                    .withIssuedAt(Instant.now())// Menentukan waktu token kapan dibuat
                    .withClaim("role", appUser.getRole().name())// Menambahkan claim atau info nama pengguna
                    .sign(algorithm);// Pemilihan pada versi algoritma yang kita pakai dan tidak bisa di ubah ubah
            return token;

        }catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }
    public Boolean verifyJwtToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtScreet.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        }catch (JWTVerificationException e) {
            throw new RuntimeException();
        }
    }

    public Map<String, String> getUserInfoByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtScreet.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("role", decodedJWT.getClaim("role").asString());
            return userInfo;

        }catch (JWTVerificationException e) {
            throw new RuntimeException();
        }
    }
}
