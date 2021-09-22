package com.example.agenda.services;

import com.example.agenda.CustomExceptions;
import com.example.agenda.model.Contato;
import com.example.agenda.model.Email;
import com.example.agenda.model.Endereco;
import com.example.agenda.model.Telefone;
import com.example.agenda.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ContatoServiceImpl implements ContatoService{

    @Autowired
    ContatoRepository contatoRepository;


    @Override
    public Contato adicionaContato(Contato contato) throws Exception {

         if(!contato.getUsuario().getContatoList().stream().filter(f->f.getNome().equals(contato.getNome()) && f.getSobrenome().equals(contato.getSobrenome())).collect(Collectors.toList()).isEmpty()){
            throw new CustomExceptions("Contato já existente");
        }

        return contatoRepository.save(contato);
    }

    @Override
    public void deletaContato(Integer id) {
        contatoRepository.delete(contatoRepository.getById(id));
    }

    @Override
    public Contato getContatoByid(Integer id) {
        return contatoRepository.getById(id);
    }

    @Override
    public Contato salvarFoto(Integer id, String foto) {
        Contato contato = contatoRepository.getById(id);
        contato.setFoto(foto);
        return contatoRepository.save(contato);
    }

    @Override
    public Contato adicionaTelefoneContato(Integer id, Telefone telefone) throws CustomExceptions {
        Contato contato = contatoRepository.getById(id);
        System.out.println(contato.getTelefones().toString());
        telefone.setContato(contato);
        Telefone telefone1 =  contato.adicionaTelefone(telefone);

        if(telefone1 == null){
            throw new CustomExceptions("Telefone já existente");
        }

        return contatoRepository.save(contato);
    }

    @Override
    public Contato adicionaEnderecoContato(Integer id, Endereco endereco) throws CustomExceptions {
        Contato contato = contatoRepository.getById(id);
        endereco.setContato(contato);
        Endereco endereco1 =   contato.adicionaEndereco(endereco);

        if(endereco1 == null){
            throw new CustomExceptions("Endereco já existente");
        }

        return contatoRepository.save(contato);
    }

    @Override
    public Contato adicionaEmailContato(Integer id, Email email) throws CustomExceptions {
        Contato contato = contatoRepository.getById(id);
        email.setContato(contato);
        Email email1 =  contato.adicionaEmail(email);

        if(email1 == null){
            throw new CustomExceptions("Email já existente");
        }
        return contatoRepository.save(contato);
    }
}
