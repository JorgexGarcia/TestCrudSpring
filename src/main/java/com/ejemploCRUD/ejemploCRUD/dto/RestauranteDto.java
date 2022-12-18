package com.ejemploCRUD.ejemploCRUD.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDto {


    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;


    public RestauranteDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public RestauranteDto() {
    }
}
