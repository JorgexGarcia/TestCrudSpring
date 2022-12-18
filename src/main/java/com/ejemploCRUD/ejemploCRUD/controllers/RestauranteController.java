package com.ejemploCRUD.ejemploCRUD.controllers;

import com.ejemploCRUD.ejemploCRUD.Entity.Restaurante;
import com.ejemploCRUD.ejemploCRUD.dto.Mensaje;
import com.ejemploCRUD.ejemploCRUD.dto.RestauranteDto;
import com.ejemploCRUD.ejemploCRUD.services.RestauranteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin(origins = "*")
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;

    @GetMapping("/lista")
    public ResponseEntity<List<Restaurante>> List() {
        List<Restaurante> list = restauranteService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        if(!restauranteService.existById(id)){
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if(restauranteService.getOne(id).isPresent()){
            Restaurante restaurante  = restauranteService.getOne(id).get();
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/detailname/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        if(!restauranteService.existByName(name)){
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if(restauranteService.getByName(name).isPresent()){
            Restaurante restaurante  = restauranteService.getByName(name).get();
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RestauranteDto restauranteDto) {
        if(StringUtils.isBlank(restauranteDto.getNombre())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(restauranteService.existByName(restauranteDto.getNombre())){
            return new ResponseEntity<>(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Restaurante restaurante = new Restaurante(restauranteDto.getNombre(), restauranteDto.getDescripcion());
        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("Restaurante creado"), HttpStatus.OK);
    }
}
