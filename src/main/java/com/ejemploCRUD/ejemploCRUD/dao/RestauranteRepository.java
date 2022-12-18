package com.ejemploCRUD.ejemploCRUD.dao;

import com.ejemploCRUD.ejemploCRUD.Entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    Optional<Restaurante> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
