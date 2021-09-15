package com.example.agenda.services;

import com.example.agenda.model.Contato;
import com.example.agenda.model.Email;
import com.example.agenda.model.Endereco;
import com.example.agenda.model.Telefone;
import com.example.agenda.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoServiceImpl implements ContatoService{

    @Autowired
    ContatoRepository contatoRepository;


    @Override
    public Contato adicionaContato(Contato contato) {
        return contatoRepository.save(contato);
    }

    @Override
    public void deletaContato(Integer id) {
        contatoRepository.delete(contatoRepository.getById(id));
    }

    @Override
    public Contato adicionaTelefoneContato(Integer id, Telefone telefone) {
        Contato contato = contatoRepository.getById(id);
        contato.adicionaTelefone(telefone);
        return adicionaContato(contato);
    }

    @Override
    public Contato adicionaEnderecoContato(Integer id, Endereco endereco) {
        Contato contato = contatoRepository.getById(id);
        contato.adicionaEndereco(endereco);
        return adicionaContato(contato);
    }

    @Override
    public Contato adicionaEmailContato(Integer id, Email email) {
        Contato contato = contatoRepository.getById(id);
        contato.adicionaEmail(email);
        return adicionaContato(contato);
    }
}
