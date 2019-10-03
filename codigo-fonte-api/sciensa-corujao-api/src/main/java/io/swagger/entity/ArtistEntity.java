package io.swagger.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

@Entity
@Table(name = "artist_tb")
public class ArtistEntity implements Serializable {
	
	private static final long serialVersionUID = -3579556170775580134L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@Column(nullable = false)
	private String firstName = null;

	@Column(nullable = false)
	private String lastName = null;

	@Column
	private LocalDate dateOfBirth = null;

	@Column
	private OffsetDateTime createdAt = null;

	@Column
	private OffsetDateTime updatedAt = null;
	
	@ManyToMany(mappedBy = "cast")
	private List<MovieEntity> directedMovies;
	
	@ManyToMany(mappedBy = "directors")
	private List<MovieEntity> filmography;

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

	public List<MovieEntity> getDirectedMovies() {
		return directedMovies;
	}

	public void setDirectedMovies(List<MovieEntity> directedMovies) {
		this.directedMovies = directedMovies;
	}

	public List<MovieEntity> getFilmography() {
		return filmography;
	}

	public void setFilmography(List<MovieEntity> filmography) {
		this.filmography = filmography;
	}

}
