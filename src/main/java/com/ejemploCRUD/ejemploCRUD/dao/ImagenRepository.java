package com.ejemploCRUD.ejemploCRUD.dao;

import com.ejemploCRUD.ejemploCRUD.Entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface ImagenRepository extends JpaRepository<Imagen, Long> {


}
