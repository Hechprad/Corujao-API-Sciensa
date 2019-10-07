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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import io.swagger.Swagger2SpringBoot;
import io.swagger.entity.ArtistEntity;
import io.swagger.repository.ArtistRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Swagger2SpringBoot.class)
@AutoConfigureMockMvc
//@EnableAutoConfiguration
public class ArtistControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private ArtistRepository artistRepository;

	@Before
	public void setup() {
		ArtistEntity artist = new ArtistEntity(
				1L,
				"Sophie", 
				"Turner", 
				LocalDate.of(1996, 2, 21), 
				OffsetDateTime.now(),
				OffsetDateTime.now());
		BDDMockito.when(artistRepository.findOne(artist.getId())).thenReturn(artist);
	}

	@Test
	public void getMovieDeveRetornarStatusCode200() {
		ResponseEntity<ArtistEntity> response = restTemplate.getForEntity("/artists/{artistId}", ArtistEntity.class, 1L);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void getMovieDeveRetornarStatusCode404() {
		ResponseEntity<ArtistEntity> response = restTemplate.getForEntity("/artists/{artistId}", ArtistEntity.class, -1);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void listMrtistsDeveRetornarStatusCode200() {
		List<ArtistEntity> artists = asList(
				new ArtistEntity(2L, "Sophie", "Turner", LocalDate.of(1996, 2, 21), OffsetDateTime.now(),
						OffsetDateTime.now()),
				new ArtistEntity(3L, "Joaquin", "Phoenix", LocalDate.of(1974, 10, 28), OffsetDateTime.now(),
						OffsetDateTime.now()));

		// PageRequest(int page, int size, Sort sort) ***
		Pageable pageable = new PageRequest(0, 10, null);

		Page<ArtistEntity> page = new PageImpl<>(artists);

		BDDMockito.when(artistRepository.findAll(pageable)).thenReturn(page);
		ResponseEntity<ArtistEntity> response = restTemplate.getForEntity("/artists", ArtistEntity.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void listArtistsDeveRetornarStatusCode404() {
		List<ArtistEntity> artists = new ArrayList<ArtistEntity>();

		// PageRequest(int page, int size, Sort sort) ***
		Pageable pageable = new PageRequest(0, 10, null);

		Page<ArtistEntity> page = new PageImpl<>(artists);

		BDDMockito.when(artistRepository.findAll(pageable)).thenReturn(page);
		ResponseEntity<ArtistEntity> response = restTemplate.getForEntity("/artists", ArtistEntity.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

}
