package io.swagger.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import io.swagger.entity.MovieEntity;
import io.swagger.service.MovieService;
import io.swagger.util.RespostasUtil;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-02T14:53:20.805Z")

@Controller
public class MoviesApiController implements MoviesApi {

	//Responsável pelas regras de negócio e comunicação com a camada de repository
	@Autowired
	private MovieService movieService;
	
	//respostas
	@Autowired
	private RespostasUtil respostasUtil;

    public ResponseEntity<MovieEntity> addMovie(@ApiParam(value = "" ,required=true )  @Valid @RequestBody MovieEntity movieEntity) {
        try {
			//add movie
        	return movieService.save(movieEntity);
		} catch (Exception e) {
			//error response
			return respostasUtil.getBadRequestMovie(MovieService.MENSAGEM_DADOS_INVALIDOS);
		}
    }

    public ResponseEntity<MovieEntity> getMovie(@ApiParam(value = "",required=true) @PathVariable("movieId") Long movieId) {
    	try {
			return movieService.getMovieById(movieId);
		} catch (Exception e) {
			return respostasUtil.getInternalErrorMovie(MovieService.MENSAGEM_DADOS_INVALIDOS);
		}
    }

    public ResponseEntity<List<MovieEntity>> listMovies(@ApiParam(value = "Página da listagem a ser retornada", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,@ApiParam(value = "Tamanho da paginação a ser utilizada no request", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size,@ApiParam(value = "Retorna itens cuja descrição se pareça com o valor informado") @Valid @RequestParam(value = "search", required = false) String search) {
//            try {
//                new ResponseEntity<Movie>(objectMapper.readValue("\"\"", Object.class), HttpStatus.BAD_REQUEST);
//            } catch (IOException e) {
//            }
    	return null;
    }

    public ResponseEntity<MovieEntity> removeMovie(@ApiParam(value = "",required=true) @PathVariable("movieId") Long movieId) {
    	return null;
    }

    public ResponseEntity<MovieEntity> updateMovie(@ApiParam(value = "",required=true) @PathVariable("movieId") Long movieId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody MovieEntity movieEntity) {
    	return null;
    }

}
