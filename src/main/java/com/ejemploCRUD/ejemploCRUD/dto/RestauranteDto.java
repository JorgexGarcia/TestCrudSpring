package com.ejemploCRUD.ejemploCRUD.dto;

import com.ejemploCRUD.ejemploCRUD.Entity.Categoria;
import com.ejemploCRUD.ejemploCRUD.Entity.Direccion;
import com.ejemploCRUD.ejemploCRUD.Entity.Imagen;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Getter
@Setter
public class RestauranteDto {


    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @Autowired
    private Categoria categoria;

    @Autowired
    private Direccion direccion;

    @Autowired
    private Set<Imagen> imagenes;

    public RestauranteDto(String nombre, String descripcion, Categoria categoria, Direccion direccion, Set<Imagen> imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.direccion = direccion;
        this.imagenes = imagenes;
    }

    public RestauranteDto() {
    }
}
