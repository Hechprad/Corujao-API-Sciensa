package io.swagger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.swagger.entity.GenreEntity;

/*
 * interface PagingAndSortingRepository extende CrudRepository
 * 
 * SORT EXAMPLE - http://localhost:5000/v1/genres?sort=description,asc
 * PAGE/SIZE EXAMPLE - http://localhost:5000/v1/genres?page=0&size=2
 * BOTH http://localhost:5000/v1/genres?page=0&size=2&sort=description,asc
 * SEARCH http://localhost:5000/v1/genres?search=romance
 */

public interface GenreRepository extends PagingAndSortingRepository<GenreEntity, Long>{}
