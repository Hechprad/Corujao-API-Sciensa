package io.swagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.threeten.bp.OffsetDateTime;

import io.swagger.entity.GenreEntity;
import io.swagger.repository.GenreRepository;
import io.swagger.util.RespostasUtil;

@Service
public class GenreService {

	// Mensagens para o header
	public static final String MENSAGEM_DADOS_INVALIDOS = "Gênero - Parâmetros invalidos - client side";

	public static final String MENSAGEM_FAIL = "Gêneros não encontrados";

	// camada para conversar com o DB
	@Autowired
	private GenreRepository repository;

	@Autowired
	private RespostasUtil respostasUtil;

	// Verifica se a Description está vazia
	public boolean isNotValidGenre(GenreEntity genreEntity) {

		return StringUtils.isEmpty(genreEntity.getDescription());

	}

	// Cadastra novo Genre e insere a data do momento que o gênero é criado
	private GenreEntity cadastraNovoGenre(GenreEntity genreEntity) {

		genreEntity.setCreatedAt(OffsetDateTime.now());
		genreEntity.setUpdatedAt(OffsetDateTime.now());
		genreEntity = repository.save(genreEntity);

		return genreEntity;

	}

	public ResponseEntity<GenreEntity> save(GenreEntity genreEntity) {
		if (isNotValidGenre(genreEntity)) {
			return respostasUtil.getBadRequestGenre(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<GenreEntity>(cadastraNovoGenre(genreEntity), HttpStatus.CREATED);
	}

	public ResponseEntity<GenreEntity> getGenreById(Long genreId) {

		GenreEntity genreEntity = repository.findGenreById(genreId);

		if (genreEntity == null) {
			return respostasUtil.getBadRequestGenre(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<GenreEntity>(genreEntity, HttpStatus.OK);
	}

	public ResponseEntity<List<GenreEntity>> findAll() {

		Iterable<GenreEntity> genreEntity = repository.findAll();

		if (genreEntity == null) {
			return respostasUtil.getBadRequestGenders(MENSAGEM_FAIL);
		}

		return new ResponseEntity<List<GenreEntity>>((List<GenreEntity>) genreEntity, HttpStatus.OK);

	}

	public ResponseEntity<GenreEntity> update(Long genreId, GenreEntity genreEntity) {
		if (isNotValidGenre(genreEntity)) {
			return respostasUtil.getBadRequestGenre(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<GenreEntity>(updateGenre(genreId, genreEntity), HttpStatus.CREATED);
	}

	private GenreEntity updateGenre(Long genreId, GenreEntity genre) {
		GenreEntity genreEntityWillUpdate = repository.findGenreById(genreId);
		genreEntityWillUpdate.setDescription(genre.getDescription());
		genreEntityWillUpdate.setUpdatedAt(OffsetDateTime.now());

		repository.save(genreEntityWillUpdate);

		return genreEntityWillUpdate;
	}

}
