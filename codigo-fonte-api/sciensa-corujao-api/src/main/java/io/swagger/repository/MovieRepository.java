package io.swagger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.swagger.entity.MovieEntity;

/*
 * interface PagingAndSortingRepository extende CrudRepository
 * 
 * SORT EXAMPLE - http://localhost:5000/v1/movies?sort=title,asc
 * PAGE/SIZE EXAMPLE - http://localhost:5000/v1/movies?page=0&size=2
 * BOTH http://localhost:5000/v1/movies?page=0&size=2&sort=title,asc
 * SEARCH http://localhost:5000/v1/movies?search=nemo
 */

public interface MovieRepository  extends PagingAndSortingRepository<MovieEntity, Long> {}