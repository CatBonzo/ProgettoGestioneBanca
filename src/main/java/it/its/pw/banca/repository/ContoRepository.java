package it.its.pw.banca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.its.pw.banca.model.Conto;

public interface ContoRepository extends JpaRepository<Conto, Long> {
}