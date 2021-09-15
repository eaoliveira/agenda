package com.example.agenda.repository;

import com.example.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContatoRepository extends JpaRepository<Contato,Integer>, JpaSpecificationExecutor<Contato> {
}
