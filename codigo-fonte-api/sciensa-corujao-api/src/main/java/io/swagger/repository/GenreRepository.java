package io.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import io.swagger.entity.GenreEntity;

public interface GenreRepository extends CrudRepository<GenreEntity, Long>{

	// get by id
	GenreEntity findGenreById(Long id);
	// get all
	Iterable<GenreEntity> findAll();
	
}
