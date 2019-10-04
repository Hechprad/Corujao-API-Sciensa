package io.swagger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import io.swagger.entity.GenreEntity;

public interface GenreRepository extends PagingAndSortingRepository<GenreEntity, Long>{

	// get by id
	GenreEntity findGenreById(Long id);
	// get all
	Page<GenreEntity> findAll(Pageable pageable);
	
}
