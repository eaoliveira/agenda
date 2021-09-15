package com.example.agenda.controller;

import com.example.agenda.model.Contato;
import com.example.agenda.model.Telefone;
import com.example.agenda.model.Usuario;
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

    @PostMapping("salvar/telefone/{id}")
    public ResponseEntity adicionaTelefone(
            @PathVariable Integer id,
            @RequestBody
            Telefone telefone){

        return   ResponseEntity.ok().body(  contatoService.adicionaTelefoneContato(id,telefone));

//        try {
//            return   ResponseEntity.ok().body(  contatoService.adicionaTelefoneContato(id,telefone));
//        }catch (Exception e){
//            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel adicionar contato");
//        }
    }
}
