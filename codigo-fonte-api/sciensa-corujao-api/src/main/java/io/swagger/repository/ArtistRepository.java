package io.swagger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.swagger.entity.ArtistEntity;

/*
 * interface PagingAndSortingRepository extende CrudRepository
 * 
 * SORT EXAMPLE - http://localhost:5000/v1/artists?sort=firstName,asc
 * PAGE/SIZE EXAMPLE - http://localhost:5000/v1/artists?page=0&size=2
 * BOTH http://localhost:5000/v1/artists?page=0&size=2&sort=lastName,asc
 * SEARCH http://localhost:5000/v1/artists?search=Roberto
 */

public interface ArtistRepository extends PagingAndSortingRepository<ArtistEntity, Long> {}