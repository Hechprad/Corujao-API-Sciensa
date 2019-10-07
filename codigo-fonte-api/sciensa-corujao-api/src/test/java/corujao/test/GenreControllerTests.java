package corujao.test;

import static java.util.Arrays.asList;

import java.util.List;

import org.assertj.core.api.Assertions;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.threeten.bp.OffsetDateTime;

import io.swagger.Swagger2SpringBoot;
import io.swagger.entity.GenreEntity;
import io.swagger.repository.GenreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Swagger2SpringBoot.class)
@AutoConfigureMockMvc
//@EnableAutoConfiguration
public class GenreControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private GenreRepository genreRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void listGenresDeveRetornarStatus200() {
		
		List<GenreEntity> genres = asList(
				new GenreEntity(1L, "Aventura", OffsetDateTime.now(), OffsetDateTime.now()),
				new GenreEntity(2L, "Comédia", OffsetDateTime.now(), OffsetDateTime.now()));
		// PageRequest(int page, int size, Sort sort) ***
		Pageable pageable = new PageRequest(0, 10, null);
				
		Page<GenreEntity> page = new PageImpl<>(genres);
		
		BDDMockito.when(genreRepository.findAll(pageable)).thenReturn(page);
		ResponseEntity<String> response = restTemplate.getForEntity("/genres", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

}
