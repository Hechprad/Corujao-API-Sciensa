package io.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import io.swagger.entity.MovieEntity;

public interface MovieRepository  extends CrudRepository<MovieEntity, Long>{

	// get by id
	MovieEntity findMovieById(Long id);
	// get all
	Iterable<MovieEntity> findAll();
	// delete by id
	void delete(Long id);
	
}