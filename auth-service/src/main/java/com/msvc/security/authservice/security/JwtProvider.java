package com.msvc.security.authservice.security;

import com.msvc.security.authservice.dto.RequestDto;
import com.msvc.security.authservice.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private RouteValidate routeValidate;

    @PostConstruct()
    protected void init(){
        SECRET = Base64.getEncoder().encodeToString(this.SECRET.getBytes());
    }

    public String createToken(AuthUser user){
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", user.getId());
        claims.put("role", user.getRol());
        return  Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
    public boolean validateToken(String token, RequestDto requestDto) {
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        } catch (Exception exception){
            return false;
        }
        if (!isAdmin(token) && routeValidate.isAdmin(requestDto))
            return false;
        return true;
    }

    public String getUsernameFromToken(String token) {
        try{
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception exception){
            return "Bad Token";
        }
    }

    private boolean isAdmin(String token){
        return (Jwts.parser().setSigningKey(SECRET)).parseClaimsJws(token).getBody().get("role").equals("admin");
    }
}
