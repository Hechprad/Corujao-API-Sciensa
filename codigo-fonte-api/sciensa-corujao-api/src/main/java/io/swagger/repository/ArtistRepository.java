package io.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import io.swagger.entity.ArtistEntity;

public interface ArtistRepository extends CrudRepository<ArtistEntity, Long> {

	// get by id
	ArtistEntity findArtistById(Long id);
	// get all
	Iterable<ArtistEntity> findAll();

}