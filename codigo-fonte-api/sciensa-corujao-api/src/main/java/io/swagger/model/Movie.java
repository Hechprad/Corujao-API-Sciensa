package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Artist;
import io.swagger.model.Genre;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Movie
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-02T14:53:20.805Z")

public class Movie   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("releaseYear")
  private Integer releaseYear = null;

  @JsonProperty("genres")
  @Valid
  private List<Genre> genres = null;

  @JsonProperty("director")
  private Artist director = null;

  @JsonProperty("cast")
  @Valid
  private List<Artist> cast = null;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt = null;

  @JsonProperty("updatedAt")
  private OffsetDateTime updatedAt = null;

  public Movie id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Movie title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Movie releaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
    return this;
  }

  /**
   * Get releaseYear
   * @return releaseYear
  **/
  @ApiModelProperty(value = "")


  public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Movie genres(List<Genre> genres) {
    this.genres = genres;
    return this;
  }

  public Movie addGenresItem(Genre genresItem) {
    if (this.genres == null) {
      this.genres = new ArrayList<Genre>();
    }
    this.genres.add(genresItem);
    return this;
  }

  /**
   * Get genres
   * @return genres
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public Movie director(Artist director) {
    this.director = director;
    return this;
  }

  /**
   * Get director
   * @return director
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Artist getDirector() {
    return director;
  }

  public void setDirector(Artist director) {
    this.director = director;
  }

  public Movie cast(List<Artist> cast) {
    this.cast = cast;
    return this;
  }

  public Movie addCastItem(Artist castItem) {
    if (this.cast == null) {
      this.cast = new ArrayList<Artist>();
    }
    this.cast.add(castItem);
    return this;
  }

  /**
   * Get cast
   * @return cast
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Artist> getCast() {
    return cast;
  }

  public void setCast(List<Artist> cast) {
    this.cast = cast;
  }

  public Movie createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Movie updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movie movie = (Movie) o;
    return Objects.equals(this.id, movie.id) &&
        Objects.equals(this.title, movie.title) &&
        Objects.equals(this.releaseYear, movie.releaseYear) &&
        Objects.equals(this.genres, movie.genres) &&
        Objects.equals(this.director, movie.director) &&
        Objects.equals(this.cast, movie.cast) &&
        Objects.equals(this.createdAt, movie.createdAt) &&
        Objects.equals(this.updatedAt, movie.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, releaseYear, genres, director, cast, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Movie {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    releaseYear: ").append(toIndentedString(releaseYear)).append("\n");
    sb.append("    genres: ").append(toIndentedString(genres)).append("\n");
    sb.append("    director: ").append(toIndentedString(director)).append("\n");
    sb.append("    cast: ").append(toIndentedString(cast)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

