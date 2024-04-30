package com.study.springboot.config;


import com.study.springboot.enumeration.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final Long expireTime;

    private UserDetailsService userDetailsService;


    @Autowired
    public JwtUtil( UserDetailsService userDetailsService,
                   @Value("${jwt.secretKey}")  String secretKey,
                   @Value("${jwt.expiration_time}") Long expireTime) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.userDetailsService = userDetailsService;
        this.expireTime = expireTime;
    }


    public String createToken(String userId, UserRole userRole){
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", userRole.getValue());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public Authentication getAuthentication(String token){

        String userId = "";
        try{
//            String email = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
            userId = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        }catch (ExpiredJwtException e){
            e.printStackTrace();
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String token){
//        try{
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return !claims.getBody().getExpiration().before(new Date());
//
//        }catch (Exception e){
//            return false;
//        }



        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            e.printStackTrace();
        }catch (ExpiredJwtException e){
            e.printStackTrace();
        }catch (UnsupportedJwtException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        return false;
    }


}
