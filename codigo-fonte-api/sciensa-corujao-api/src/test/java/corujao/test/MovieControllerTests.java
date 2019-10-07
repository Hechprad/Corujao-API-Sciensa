package corujao.test;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.threeten.bp.OffsetDateTime;

import io.swagger.Swagger2SpringBoot;
import io.swagger.entity.MovieEntity;
import io.swagger.repository.MovieRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Swagger2SpringBoot.class)
@AutoConfigureMockMvc
//@EnableAutoConfiguration
public class MovieControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private MovieRepository movieRepository;
	
	@Before
    public void setup() {
		MovieEntity movie = new MovieEntity(
				1L,
				"Procurando Nemo",
				2003,
				OffsetDateTime.now(), 
				OffsetDateTime.now());
        BDDMockito.when(movieRepository.findOne(movie.getId())).thenReturn(movie);
    }
	
	@Test
	public void getMovieDeveRetornarStatusCode200() {
		ResponseEntity<MovieEntity> response = restTemplate.getForEntity("/movies/{movieId}", MovieEntity.class, 1L);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void getMovieDeveRetornarStatusCode404() {
		ResponseEntity<MovieEntity> response = restTemplate.getForEntity("/movies/{movieId}", MovieEntity.class, -1);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void listMoviesDeveRetornarStatusCode200() {
		List<MovieEntity> movies = asList(new MovieEntity(1L, "Procurando Nemo", 2003, OffsetDateTime.now(), OffsetDateTime.now()),
				new MovieEntity(2L,"Procurando Dori", 2018, OffsetDateTime.now(), OffsetDateTime.now()));
		
		// PageRequest(int page, int size, Sort sort) ***
		Pageable pageable = new PageRequest(0, 10, null);
		
		Page<MovieEntity> page = new PageImpl<>(movies);
		
		BDDMockito.when(movieRepository.findAll(pageable)).thenReturn(page);
		ResponseEntity<MovieEntity> response = restTemplate.getForEntity("/movies", MovieEntity.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void listMoviesDeveRetornarStatusCode404() {
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		
		// PageRequest(int page, int size, Sort sort) ***
		Pageable pageable = new PageRequest(0, 10, null);
		
		Page<MovieEntity> page = new PageImpl<>(movies);
		
		BDDMockito.when(movieRepository.findAll(pageable)).thenReturn(page);
		ResponseEntity<MovieEntity> response = restTemplate.getForEntity("/movies", MovieEntity.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
    public void removeMovieDeveRetornarStatusCode204() {
        BDDMockito.doNothing().when(movieRepository).delete(1L);
        ResponseEntity<MovieEntity> exchange = restTemplate.exchange("/movies/{movieId}", HttpMethod.DELETE, null, MovieEntity.class, 1L);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(204);
    }
    
	@Test
    public void removeMovieDeveRetornarStatusCode404() throws Exception {
        BDDMockito.doNothing().when(movieRepository).delete(-1L);
        ResponseEntity<MovieEntity> exchange = restTemplate.exchange("/movies/{id}", HttpMethod.DELETE, null, MovieEntity.class, -1L);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
    }
	
	
}
