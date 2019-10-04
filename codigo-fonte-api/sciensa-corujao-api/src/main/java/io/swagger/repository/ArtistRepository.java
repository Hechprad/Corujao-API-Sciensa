package io.swagger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import io.swagger.entity.ArtistEntity;

public interface ArtistRepository extends PagingAndSortingRepository<ArtistEntity, Long> {

	// get by id
	ArtistEntity findArtistById(Long id);
	// get all
	Page<ArtistEntity> findAll(Pageable pageable);

}