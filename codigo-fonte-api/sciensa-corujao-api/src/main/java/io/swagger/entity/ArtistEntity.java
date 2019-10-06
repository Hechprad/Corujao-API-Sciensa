package io.swagger.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

@Entity
@Table(name = "artist_tb")
public class ArtistEntity implements Serializable {
	
	private static final long serialVersionUID = 6130169371117039414L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@NotEmpty(message = "Primeiro nome é obrigatório.")
	@Column(nullable = false)
	private String firstName = null;

	@NotEmpty(message = "Sobrenome é obrigatório.")
	@Column(nullable = false)
	private String lastName = null;

	@Column
	private LocalDate dateOfBirth = null;

	@Column
	private OffsetDateTime createdAt = null;

	@Column
	private OffsetDateTime updatedAt = null;
	
	// construtores para os testes
	public ArtistEntity() {}
	
	public ArtistEntity(String firstName, String lastName, LocalDate dateOfBirth, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
