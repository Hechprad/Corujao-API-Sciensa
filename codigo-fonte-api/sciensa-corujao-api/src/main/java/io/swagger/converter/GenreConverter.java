package io.swagger.converter;

import io.swagger.entity.GenreEntity;
import io.swagger.model.Genre;

public class GenreConverter {

	// Genre to GenreEntity
	public static GenreEntity toGenreEntity(Genre genre) {
		
		GenreEntity genreEntity = new GenreEntity();
		
		genreEntity.setDescription(genre.getDescription());
		genreEntity.setCreatedAt(genre.getCreatedAt());
		genreEntity.setUpdatedAt(genre.getUpdatedAt());
		
		return genreEntity;
		
	}
	
	//Entity to Genre
	public static Genre toGenre(GenreEntity genreEntity) {
		
		Genre genre = new Genre();
		
		genre.setId(genreEntity.getId());
		genre.setDescription(genreEntity.getDescription());
		genre.setCreatedAt(genreEntity.getCreatedAt());
		genre.setUpdatedAt(genreEntity.getUpdatedAt());
		
		return genre;
	}
	
}
