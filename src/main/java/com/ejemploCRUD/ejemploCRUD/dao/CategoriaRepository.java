package com.ejemploCRUD.ejemploCRUD.dao;

import com.ejemploCRUD.ejemploCRUD.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByCategoria(String categoria);
    boolean existsByCategoria(String categoria);
}
