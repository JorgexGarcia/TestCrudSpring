package com.ejemploCRUD.ejemploCRUD.services;

import com.ejemploCRUD.ejemploCRUD.Entity.Categoria;
import com.ejemploCRUD.ejemploCRUD.dao.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Optional<Categoria> getByCategoria(String categoria) {return categoriaRepository.findByCategoria(categoria);}

    public boolean existByCategoria(String categoria) { return categoriaRepository.existsByCategoria(categoria);}

    public List<Categoria> list() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getOne(long id) { return categoriaRepository.findById(id);}

    public void save(Categoria categoria) { categoriaRepository.save(categoria);}

    public void delete(long id) {categoriaRepository.deleteById(id);}
}
