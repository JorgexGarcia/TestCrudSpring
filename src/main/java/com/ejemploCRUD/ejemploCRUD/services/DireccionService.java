package com.ejemploCRUD.ejemploCRUD.services;

import com.ejemploCRUD.ejemploCRUD.Entity.Direccion;
import com.ejemploCRUD.ejemploCRUD.dao.DireccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    DireccionRepository direccionRepository;

    public List<Direccion> list() {return direccionRepository.findAll();}

    public Optional<Direccion> getOne(long id) {return direccionRepository.findById(id);}

    public Optional<Direccion> getByCalle(String calle) {return direccionRepository.findByCalle(calle);}

    public void save(Direccion direccion) {direccionRepository.save(direccion);}

    public void delete(long id) {direccionRepository.deleteById(id);}

    public boolean existById(long id) { return direccionRepository.existsById(id);}

    public boolean existByCalle(String calle) { return direccionRepository.existsByCalle(calle);}
}
