package corujao.test;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import io.swagger.Swagger2SpringBoot;
import io.swagger.entity.ArtistEntity;
import io.swagger.repository.ArtistRepository;

//Swagger2SpringBoot = Application.class
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class ArtistRepositoryTests {

	@Autowired
	private ArtistRepository artistRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private ArtistEntity artistEntity = new ArtistEntity(
			"Sophie",
			"Turner",
			LocalDate.of(1996, 2, 21),
			OffsetDateTime.now(), 
			OffsetDateTime.now());
	
	@Test
	public void saveDevePersistirDadosNoBanco() {
		
		this.artistRepository.save(artistEntity);
		
		Assertions.assertThat(artistEntity.getId()).isNotNull();
		Assertions.assertThat(artistEntity.getFirstName()).isEqualTo("Sophie");
		Assertions.assertThat(artistEntity.getLastName()).isEqualTo("Turner");
		artistRepository.delete(artistEntity.getId());
	}
	
	@Test
	public void deleteDeveRemoverDadosDoBanco() {
		
		this.artistRepository.save(artistEntity);
		
		artistRepository.delete(artistEntity);
		
		Assertions.assertThat(artistRepository.findOne(artistEntity.getId())).isNull();
	}
	
	@Test
	public void updateDeveAtualizarDadosDoBanco() {
		
		this.artistRepository.save(artistEntity);
		
		artistEntity.setFirstName("Roberta");
		artistEntity.setLastName("Almeida");
		artistEntity.setDateOfBirth(LocalDate.of(1970, 10, 30));
		
		this.artistRepository.save(artistEntity);
		
		artistEntity = this.artistRepository.findOne(artistEntity.getId());
		
		Assertions.assertThat(artistEntity.getFirstName()).isEqualTo("Roberta");
		Assertions.assertThat(artistEntity.getLastName()).isEqualTo("Almeida");
		Assertions.assertThat(artistEntity.getDateOfBirth()).isEqualTo(LocalDate.of(1970, 10, 30));
		
		artistRepository.delete(artistEntity.getId());
	}
	
	@Test
	public void naoDeveCriarArtistaSePrimeiroNomeForVazio() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Primeiro nome é obrigatório.");
		
		ArtistEntity artistEntity = new ArtistEntity();
		artistEntity.setLastName("Silva");
		artistEntity.setDateOfBirth(LocalDate.of(1970, 10, 30));
		
		this.artistRepository.save(new ArtistEntity());
	}
	
	@Test
	public void naoDeveCriarArtistaSeUltimoNomeForVazio() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Sobrenome é obrigatório.");
		
		ArtistEntity artistEntity = new ArtistEntity();
		artistEntity.setFirstName("Antonio");
		artistEntity.setDateOfBirth(LocalDate.of(1970, 10, 30));
		
		this.artistRepository.save(new ArtistEntity());
	}
	
}
