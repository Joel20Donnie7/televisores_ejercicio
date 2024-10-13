package com.gestiontelevisor.repository;

import com.gestiontelevisor.entity.Televisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelevisorRepository extends JpaRepository<Televisor,Integer> {
}