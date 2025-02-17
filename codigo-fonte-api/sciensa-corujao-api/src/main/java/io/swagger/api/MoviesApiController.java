package io.swagger.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import io.swagger.entity.MovieEntity;
import io.swagger.service.MovieService;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-02T14:53:20.805Z")

@Controller
public class MoviesApiController implements MoviesApi {

	// Responsável pelas regras de negócio e comunicação com a camada de repository
	@Autowired
	private MovieService movieService;

	public ResponseEntity<MovieEntity> addMovie(
			@ApiParam(value = "", required = true) @Valid @RequestBody MovieEntity movieEntity) {
		return movieService.save(movieEntity);
	}

	public ResponseEntity<MovieEntity> getMovie(
			@ApiParam(value = "", required = true) @PathVariable("movieId") Long movieId) {
		return movieService.getMovieById(movieId);
	}
	
	public ResponseEntity<MovieEntity> removeMovie(
			@ApiParam(value = "", required = true) @PathVariable("movieId") Long movieId) {
		return movieService.delete(movieId);
	}
	
	public ResponseEntity<Page<MovieEntity>> listMovies(
			@ApiParam(value = "Página da listagem a ser retornada", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@ApiParam(value = "Tamanho da paginação a ser utilizada no request", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@ApiParam(value = "Retorna itens cuja descrição se pareça com o valor informado") @Valid @RequestParam(value = "search", required = false) String search, 
			Pageable pageable) {
		if(search != null) { return movieService.searchTitle(search, pageable); }
		return movieService.findAll(pageable);				
	}

	public ResponseEntity<MovieEntity> updateMovie(
			@ApiParam(value = "", required = true) @PathVariable("movieId") Long movieId,
			@ApiParam(value = "", required = true) @Valid @RequestBody MovieEntity movieEntity) {
		return movieService.updateMovie(movieId, movieEntity);
	}

}
