package com.example.agenda.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class Usuario implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Perfil> perfis = new HashSet<>();

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Contato> contatoList = new ArrayList<>();


    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public  Usuario(){}

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Contato> adicionaContato(Contato contato){
        this.contatoList.add(contato);
        return  this.contatoList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Contato> getContatoList() {
        return contatoList;
    }
}
