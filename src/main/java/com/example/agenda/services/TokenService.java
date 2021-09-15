package com.example.agenda.services;

import com.example.agenda.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private  String  secret = "1231";
    private  long expiration = 123123;

    public String geraToken(Authentication authentication){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date exp = new Date(hoje.getTime() +expiration) ;

        return Jwts.builder().setSubject(usuario.getId().toString()).setIssuedAt(hoje).setExpiration(exp).signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    public boolean validaToken(String token){
      try {
          Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
          return true;
      }catch (Exception e){
          return false;
      }
    }

    public Integer getUsuarioId(String token) {
        try {
          Claims claims= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return Integer.parseInt(claims.getSubject());
        }catch (Exception e){
            return null;
        }
    }
}
