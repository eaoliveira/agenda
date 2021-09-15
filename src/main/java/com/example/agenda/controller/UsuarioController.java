package com.example.agenda.controller;

import com.example.agenda.model.Usuario;
import com.example.agenda.model.dto.TokenDto;
import com.example.agenda.model.dto.UsuarioInput;
import com.example.agenda.repository.UsuarioRepository;
import com.example.agenda.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PreAuthorize("hasRole('ADM')")
    @GetMapping
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    @PostMapping
    public  ResponseEntity<String> salvar(@RequestBody UsuarioInput usuarioInput){
        Usuario usuario =new Usuario(usuarioInput.getEmail(),usuarioInput.getSenha());
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário foi adicionado com sucesso");
    }

    @PostMapping("/autentica")
    public ResponseEntity autentica(@RequestBody UsuarioInput input){
    try{
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(),input.getSenha()));
        String token = tokenService.geraToken(authentication);
        return ResponseEntity.ok(new TokenDto(token,"Bearer"));
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não foi possivel autenticar");
    }

    }

}