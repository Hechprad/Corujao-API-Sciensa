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
import io.swagger.entity.GenreEntity;
import io.swagger.service.GenreService;
import io.swagger.util.RespostasUtil;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-02T14:53:20.805Z")

@Controller
public class GenresApiController implements GenresApi {

	// Responsável pelas regras de negócio e comunicação com a camada de repository
	// Spring injeta a classe aqui
	@Autowired
	private GenreService genreService;

	// respostas
	@Autowired
	private RespostasUtil respostasUtil;

	public ResponseEntity<GenreEntity> addGenre(
			@ApiParam(value = "", required = true) @Valid @RequestBody GenreEntity genreEntity) {
		try {
			// add genre
			return genreService.save(genreEntity);
		} catch (Exception e) {
			// error response
			return respostasUtil.getBadRequestGenre(GenreService.MENSAGEM_DADOS_INVALIDOS);
		}
	}

	public ResponseEntity<GenreEntity> getGenre(
			@ApiParam(value = "", required = true) @PathVariable("genreId") Long genreId) {
		try {
			return genreService.getGenreById(genreId);
		} catch (Exception e) {
			return respostasUtil.getInternalErrorGenre(GenreService.MENSAGEM_DADOS_INVALIDOS);
		}
	}

	public ResponseEntity<Page<GenreEntity>> listGenres(
			@ApiParam(value = "Página da listagem a ser retornada", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@ApiParam(value = "Tamanho da paginação a ser utilizada no request", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@ApiParam(value = "Retorna itens cuja descrição se pareça com o valor informado") @Valid @RequestParam(value = "search", required = false) String search, Pageable pageable) {
		try {
			if(search != null) {
				return genreService.searchDescription(search,  pageable);
			}
			return genreService.findAll(pageable);
		} catch (Exception e) {
			return respostasUtil.getBadRequestGen(GenreService.MENSAGEM_FAIL);
		}
	}

	public ResponseEntity<GenreEntity> updateGenre(
			@ApiParam(value = "", required = true) @PathVariable("genreId") Long genreId,
			@ApiParam(value = "", required = true) @Valid @RequestBody GenreEntity genreEntity) {
		try {
			return genreService.update(genreId, genreEntity);
		} catch (Exception e) {
			return respostasUtil.getInternalErrorGenre(GenreService.MENSAGEM_DADOS_INVALIDOS);
		}
	}

}
