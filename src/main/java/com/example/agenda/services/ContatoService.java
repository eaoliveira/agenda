package com.example.agenda.services;

import com.example.agenda.CustomExceptions;
import com.example.agenda.model.Contato;
import com.example.agenda.model.Email;
import com.example.agenda.model.Endereco;
import com.example.agenda.model.Telefone;

import java.util.List;

public interface ContatoService {

    public Contato adicionaContato(Contato contato) throws Exception;

    public void deletaContato(Integer id);

    public Contato getContatoByid(Integer id);

    public Contato salvarFoto(Integer id,String foto);

    public Contato adicionaTelefoneContato(Integer id, Telefone telefone) throws CustomExceptions;

    public Contato adicionaEnderecoContato(Integer id, Endereco endereco) throws CustomExceptions;

    public Contato adicionaEmailContato(Integer id, Email email) throws CustomExceptions;
}
