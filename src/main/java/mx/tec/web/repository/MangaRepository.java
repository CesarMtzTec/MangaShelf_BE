/*
 * Repository
 * Version 1.0
 * November 24, 2021 
 * Copyright 2021 Tecnologico de Monterrey
 */
package mx.tec.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.tec.web.entity.Manga;

/**
 * Manga JPA Repository
 * @author eddy
  */
@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {}
