package com.ejemploCRUD.ejemploCRUD.controllers;

import com.ejemploCRUD.ejemploCRUD.Entity.Categoria;
import com.ejemploCRUD.ejemploCRUD.Entity.Direccion;
import com.ejemploCRUD.ejemploCRUD.Entity.Imagen;
import com.ejemploCRUD.ejemploCRUD.Entity.Restaurante;
import com.ejemploCRUD.ejemploCRUD.dto.Mensaje;
import com.ejemploCRUD.ejemploCRUD.dto.RestauranteDto;
import com.ejemploCRUD.ejemploCRUD.services.CategoriaService;
import com.ejemploCRUD.ejemploCRUD.services.DireccionService;
import com.ejemploCRUD.ejemploCRUD.services.RestauranteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin(origins = "*")
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    DireccionService direccionService;

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

        Categoria categoria;
        Restaurante restaurante = new Restaurante(restauranteDto.getNombre(), restauranteDto.getDescripcion());

        //Categoria
        if(categoriaService.getByCategoria(restauranteDto.getCategoria().getCategoria()).isPresent()){
            categoria = categoriaService.getByCategoria(restauranteDto.getCategoria().getCategoria()).get();
            restaurante.setCategoria(categoria);
        }else{
            //No esta vacio
            if(StringUtils.isBlank(restauranteDto.getCategoria().getCategoria()))
                return new ResponseEntity<>(new Mensaje("La categoria es obligatoria"), HttpStatus.BAD_REQUEST);
            Categoria categoriaNew = restauranteDto.getCategoria();
            restaurante.setCategoria(categoriaNew);
            if(categoriaNew.getRestaurantes()!= null){
                categoriaNew.getRestaurantes().add(restaurante);
            }else{
                Set<Restaurante> restaurantes = new HashSet<Restaurante>();
                restaurantes.add(restaurante);
                categoriaNew.setRestaurantes(restaurantes);
            }
        }

        //Imagenes
        Set<Imagen> imagenes = restauranteDto.getImagenes();
        for(Imagen imagen: imagenes){
            imagen.setRestaurante(restaurante);
        }
        restaurante.setImagenes(imagenes);

        //Direccion
        if(direccionService.existByCalle(restauranteDto.getDireccion().getCalle())){
            return new ResponseEntity<>(new Mensaje("La dirección ya existe"), HttpStatus.BAD_REQUEST);
        }else {
            Direccion direccion = restauranteDto.getDireccion();
            restaurante.setDireccion(direccion);
            direccion.setRestaurante(restaurante);
        }

        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("Restaurante creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") long id, @RequestBody RestauranteDto restauranteDto){
        //Si no existe
        if(!restauranteService.existById((id))){
            return new ResponseEntity<>(new Mensaje("no existe "), HttpStatus.NOT_FOUND);
        }
        //Si existe
        if(restauranteService.existByName(restauranteDto.getNombre())
            && restauranteService.getByName(restauranteDto.getNombre()).isPresent()
            && restauranteService.getByName(restauranteDto.getNombre()).get().getId() != id){
            return new ResponseEntity<>(new Mensaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        //Si viene nombre vacio
        if(StringUtils.isBlank(restauranteDto.getNombre())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        //Comprobación dirección
        if(direccionService.existByCalle(restauranteDto.getDireccion().getCalle()))
            return new ResponseEntity<>(new Mensaje("La dirección ya existe"), HttpStatus.BAD_REQUEST);

        //Actualizo y guardo
        Restaurante restaurante = restauranteService.getOne(id).get();
        restaurante.setNombre(restauranteDto.getNombre());
        restaurante.setDescripcion(restaurante.getDescripcion());
        restaurante.setCategoria(restauranteDto.getCategoria());
        restaurante.setDireccion(restauranteDto.getDireccion());
        restaurante.setImagenes(restauranteDto.getImagenes());

        restauranteService.save(restaurante);
        return new ResponseEntity<>(new Mensaje("restaurante actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") long id){
        //Si no existe
        if(!restauranteService.existById((id))){
            return new ResponseEntity<>(new Mensaje("no existe "), HttpStatus.NOT_FOUND);
        }
        //Delete
        restauranteService.delete(id);
        return new ResponseEntity<>(new Mensaje("restaurante borrado"), HttpStatus.OK);
    }
}
