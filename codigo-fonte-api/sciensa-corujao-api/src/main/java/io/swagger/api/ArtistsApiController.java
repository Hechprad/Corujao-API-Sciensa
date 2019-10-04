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
import io.swagger.service.GenreService;
import io.swagger.util.RespostasUtil;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-02T14:53:20.805Z")

@Controller
public class ArtistsApiController implements ArtistsApi {

	// Responsável pelas regras de negócio e comunicação com a camada de repository
	// Spring injeta a classe aqui
	@Autowired
	private ArtistService artistService;

	// respostas
	@Autowired
	private RespostasUtil respostasUtil;
	
	public ResponseEntity<ArtistEntity> addArtist(
			@ApiParam(value = "", required = true) @Valid @RequestBody ArtistEntity artistEntity) {
		try {
			// add artist
			return artistService.save(artistEntity);
		} catch (Exception e) {
			// error response
			return respostasUtil.getBadRequestArtist(GenreService.MENSAGEM_DADOS_INVALIDOS);
		}
	}

	public ResponseEntity<ArtistEntity> getArtist(
			@ApiParam(value = "", required = true) @PathVariable("artistId") Long artistId) {
		try {
			return artistService.getArtistById(artistId);
		} catch (Exception e) {
			return respostasUtil.getInternalErrorArtist(ArtistService.MENSAGEM_DADOS_INVALIDOS);
		}
	}

	public ResponseEntity<List<MovieEntity>> getArtistFilmography(
			@ApiParam(value = "", required = true) @PathVariable("artistId") Long artistId) {
		
		try {
			return artistService.getFilmography(artistId);
		} catch (Exception e) {
			return respostasUtil.getBadRequestMov(ArtistService.MENSAGEM_DADOS_INVALIDOS);
		}
	}

	public ResponseEntity<Page<ArtistEntity>> listArtists(
			@ApiParam(value = "Página da listagem a ser retornada", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@ApiParam(value = "Tamanho da paginação a ser utilizada no request", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@ApiParam(value = "Retorna itens cuja descrição se pareça com o valor informado") @Valid @RequestParam(value = "search", required = false) String search, Pageable pageable) {
		try {
			if(search != null) {
				return artistService.searchName(search,  pageable);
			}
			return artistService.findAll(pageable);
		} catch (Exception e) {
			return respostasUtil.getBadRequestArt(ArtistService.MENSAGEM_FAIL);
		}
	}

	public ResponseEntity<ArtistEntity> updateArtist(
			@ApiParam(value = "", required = true) @PathVariable("artistId") Long artistId,
			@ApiParam(value = "", required = true) @Valid @RequestBody ArtistEntity artistEntity) {
		try {
			return artistService.update(artistId, artistEntity);
		} catch (Exception e) {
			return respostasUtil.getInternalErrorArtist(ArtistService.MENSAGEM_DADOS_INVALIDOS);
		}
	}

}
