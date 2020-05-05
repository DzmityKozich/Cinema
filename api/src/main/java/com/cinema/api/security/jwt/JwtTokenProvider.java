package com.cinema.api.security.jwt;

import com.cinema.api.service.impl.LoginModelServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private int validityInMilliseconds;

    @Autowired
    private LoginModelServiceImpl loginModelServiceImpl;

    public static final String BEARER_ = "Bearer_";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORITIES = "authorities";

    public String createToken(String username, String role){
        Date nowDate = new Date();
        String token = Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES, role)
                .setIssuedAt(nowDate)
                .setExpiration(new Date(nowDate.getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    public boolean validateToken(String token){
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    public Claims parseToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public UsernamePasswordAuthenticationToken setAuthentication(String token){
        Claims claims = parseToken(token);
        String username = claims.getSubject();
        UserDetails userDetails = loginModelServiceImpl.loadUserByUsername(username);
        final Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }
}
