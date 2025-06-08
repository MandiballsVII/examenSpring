package com.example.examen.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entidades.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
