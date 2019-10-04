package io.swagger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import io.swagger.entity.MovieEntity;

//PagingAndSortingRepository extends CrudRepository
//SORT EXAMPLE - http://localhost:5000/v1/movies?sort=title,asc
//PAGE/SIZE EXAMPLE - http://localhost:5000/v1/movies?page=0&size=2
//BOTH http://localhost:5000/v1/movies?page=0&size=2&sort=title,asc
public interface MovieRepository  extends PagingAndSortingRepository<MovieEntity, Long> {

	// get by id
	MovieEntity findMovieById(Long id);
	// get all
	Page<MovieEntity> findAll(Pageable pageable);
	// delete by id
	void delete(Long id);
	
}