package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.Parte;

public interface ParteRepository extends JpaRepository<Parte, String> {

}
