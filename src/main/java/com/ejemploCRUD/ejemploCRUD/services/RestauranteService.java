package com.ejemploCRUD.ejemploCRUD.services;

import com.ejemploCRUD.ejemploCRUD.Entity.Restaurante;
import com.ejemploCRUD.ejemploCRUD.dao.RestauranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RestauranteService {

    @Autowired
    RestauranteRepository restauranteRepository;

    public List<Restaurante> list() {return restauranteRepository.findAll();}

    public Optional<Restaurante> getOne(long id) {return restauranteRepository.findById(id);}

    public Optional<Restaurante> getByName(String name) {return restauranteRepository.findByNombre(name);}

    public void save(Restaurante restaurante) {restauranteRepository.save(restaurante);}

    public void delete(long id) {restauranteRepository.deleteById(id);}

    public boolean existById(long id) { return restauranteRepository.existsById(id);}

    public boolean existByName(String name) { return restauranteRepository.existsByNombre(name);}
}
