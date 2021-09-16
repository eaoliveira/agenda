package com.example.agenda.controller;

import com.example.agenda.model.*;
import com.example.agenda.model.dto.ContatoInput;
import com.example.agenda.repository.ContatoRepository;
import com.example.agenda.repository.UsuarioRepository;
import com.example.agenda.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contatos")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PreAuthorize("authentication.principal.id == #contatoInput.usuarioId")
    @PostMapping("salvar")
    public ResponseEntity adicionaContato(@RequestBody ContatoInput contatoInput){
        try{

            Usuario usuario = usuarioRepository.getById(contatoInput.getUsuarioId());

            Contato contato = new Contato(usuario,contatoInput.getNome(),contatoInput.getSobrenome(),contatoInput.getApelido(),contatoInput.getDataNascimento());

            contatoService.adicionaContato(contato);

            return  ResponseEntity.ok().body(contato);

        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel adicionar contato");
        }

    }

    @PostMapping("salvar/{id}/telefone")
    public ResponseEntity adicionaTelefone(
            @PathVariable Integer id,
            @RequestBody
            Telefone telefone){

        try {
            return   ResponseEntity.ok().body(  contatoService.adicionaTelefoneContato(id,telefone));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel adicionar contato");
        }
    }

    @PostMapping("salvar/{id}/email")
    public ResponseEntity adicionaEmail(
            @PathVariable Integer id,
            @RequestBody
                    Email email){

        try {
            return   ResponseEntity.ok().body(  contatoService.adicionaEmailContato(id,email));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel adicionar contato");
        }
    }

    @PostMapping("salvar/{id}/endereco")
    public ResponseEntity adicionaEndereco(
            @PathVariable Integer id,
            @RequestBody
                    Endereco endereco){

        try {
            return   ResponseEntity.ok().body(  contatoService.adicionaEnderecoContato(id,endereco));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel adicionar contato");
        }
    }
}
