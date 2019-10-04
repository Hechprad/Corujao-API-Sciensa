package io.swagger.api;

import java.util.List;

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
import io.swagger.entity.ArtistEntity;
import io.swagger.entity.MovieEntity;
import io.swagger.service.ArtistService;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-02T14:53:20.805Z")

@Controller
public class ArtistsApiController implements ArtistsApi {

	// Responsável pelas regras de negócio e comunicação com a camada de repository
	// Spring injeta a classe aqui
	@Autowired
	private ArtistService artistService;

	public ResponseEntity<ArtistEntity> addArtist(
			@ApiParam(value = "", required = true) @Valid @RequestBody ArtistEntity artistEntity) {
		return artistService.save(artistEntity);
	}

	public ResponseEntity<ArtistEntity> getArtist(
			@ApiParam(value = "", required = true) @PathVariable("artistId") Long artistId) {
		return artistService.getArtistById(artistId);
	}

	public ResponseEntity<Page<ArtistEntity>> listArtists(
			@ApiParam(value = "Página da listagem a ser retornada", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@ApiParam(value = "Tamanho da paginação a ser utilizada no request", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@ApiParam(value = "Retorna itens cuja descrição se pareça com o valor informado") @Valid @RequestParam(value = "search", required = false) String search,
			Pageable pageable) {
		if (search != null) { return artistService.searchName(search, pageable); }
		return artistService.findAll(pageable);
	}

	public ResponseEntity<ArtistEntity> updateArtist(
			@ApiParam(value = "", required = true) @PathVariable("artistId") Long artistId,
			@ApiParam(value = "", required = true) @Valid @RequestBody ArtistEntity artistEntity) {
		return artistService.updateArtist(artistId, artistEntity);
	}

	public ResponseEntity<List<MovieEntity>> getArtistFilmography(
			@ApiParam(value = "", required = true) @PathVariable("artistId") Long artistId) {
		return artistService.getFilmography(artistId);
	}
	
}
