package com.ejemploCRUD.ejemploCRUD.dao;

import com.ejemploCRUD.ejemploCRUD.Entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    Optional<Direccion> findByCalle(String calle);
    boolean existsByCalle(String calle);
}
