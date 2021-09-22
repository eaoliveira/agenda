package com.example.agenda.controller;

import com.example.agenda.CustomExceptions;
import com.example.agenda.model.*;
import com.example.agenda.model.dto.ContatoInput;
import com.example.agenda.model.dto.EmailInput;
import com.example.agenda.repository.ContatoRepository;
import com.example.agenda.repository.UsuarioRepository;
import com.example.agenda.services.ContatoService;
import com.example.agenda.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("contatos")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @Autowired
    EmailService emailService;

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

        }
        catch (CustomExceptions exceptions){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( exceptions.getMessage());
        }
        catch (Exception e){
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
        }
        catch (CustomExceptions exceptions){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( exceptions.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel adicionar contato");
        }
    }

    @PutMapping("salvar/{id}/photo")
    public ResponseEntity uploadPhoto(
            @PathVariable Integer id,
            @RequestBody MultipartFile foto
            )
    {
        File file = new File("/Users/ednaalvesdeoliveira/Downloads/upload/"+foto.getOriginalFilename());
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(foto.getBytes());
            fileOutputStream.close();
            String enderecofoto=file.getAbsolutePath();
            contatoService.salvarFoto(id,enderecofoto);
            return ResponseEntity.ok().body("Upload Realizado");
        }catch (IOException e){
            e.printStackTrace();

            return ResponseEntity.ok().body("Upload NÃO REALIZADO");
        }


    }




    @PostMapping("compartilhar/{id}")
    public ResponseEntity compartilharContato(
            @PathVariable Integer id,
            @RequestBody
                    EmailInput emailInput){

        try {
           Contato contato =  contatoService.getContatoByid(id);

           emailInput.setCorpo(contato.toString());

           emailService.enviaEmail(emailInput);

            return   ResponseEntity.ok().body("Email enviado");
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel enviar email");
        }
    }

    @PostMapping("salvar/{id}/email")
    public ResponseEntity adicionaEmail(
            @PathVariable Integer id,
            @RequestBody
                    Email email){

        try {
            return   ResponseEntity.ok().body(  contatoService.adicionaEmailContato(id,email));
        }
        catch (CustomExceptions exceptions){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( exceptions.getMessage());
        }
        catch (Exception e){
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
        }
        catch (CustomExceptions exceptions){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( exceptions.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Não foi possivel adicionar contato");
        }
    }
}
