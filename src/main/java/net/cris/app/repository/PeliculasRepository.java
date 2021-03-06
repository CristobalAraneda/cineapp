package net.cris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.cris.app.model.Pelicula;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {

}
