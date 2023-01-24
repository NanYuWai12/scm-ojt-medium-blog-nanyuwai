package com.scm.ojt.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scm.ojt.bl.dto.login.LoginDTO;
import com.scm.ojt.entity.User;
import com.scm.ojt.persistence.dao.UserDAO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    
    private static final long serialVersionUID = 1L;

    private String secret = "Secret";
    
    private long validity = 1440;
    
    @Autowired
    private UserDAO userDAO;
    
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email",user.getEmail());
        return doGenerateToken(claims, user.getEmail());
    }
    
    private Claims getAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getLoginId(String token) {
        return getClaim(token, Claims::getSubject);
    }
    
    public LoginDTO getLoginDTO(String token) {
        String email = this.getLoginId(token);
        User user =this.userDAO.dbGetUserByEmail(email);
        return (user == null) ? null : new LoginDTO(user);
    }
    
    public String refreshToken(String token) {
        Map<String, Object> claims = this.getAllClaims(token);
        return (claims == null) ? null : doGenerateToken(claims, this.getLoginId(token));
    }
    
    public Date getExpirationDate(String token) {
        return getClaim(token, Claims::getExpiration);
    }
    
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDate(token);
        return expiration.before(new Date());
    }
    public Boolean isValidToken(String token) {
        String email = this.getLoginId(token);
        User user = userDAO.dbGetUserByEmail(email);
        return (user != null && !isTokenExpired(token));
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (validity * 60) * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
