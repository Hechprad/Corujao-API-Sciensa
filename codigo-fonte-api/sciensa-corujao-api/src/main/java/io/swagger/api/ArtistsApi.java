/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Artist;
import io.swagger.model.ResponseError;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-02T14:53:20.805Z")

@Api(value = "artists", description = "the artists API")
public interface ArtistsApi {

    @ApiOperation(value = "Cadastra um novo artista", nickname = "addArtist", notes = "", response = Object.class, tags={ "artists", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Artista criado com sucesso", response = Object.class),
        @ApiResponse(code = 400, message = "Parâmetros invalidos - client side", response = ResponseError.class),
        @ApiResponse(code = 500, message = "Erro durante a criação - server side", response = ResponseError.class) })
    @RequestMapping(value = "/artists",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Object> addArtist(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Artist artist);


    @ApiOperation(value = "Detalhe de artista", nickname = "getArtist", notes = "", response = Object.class, tags={ "artists", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Artista consultado com sucesso", response = Object.class),
        @ApiResponse(code = 400, message = "Parâmetros invalidos - client side", response = ResponseError.class),
        @ApiResponse(code = 500, message = "Erro durante a criação - server side", response = ResponseError.class) })
    @RequestMapping(value = "/artists/{artistId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> getArtist(@ApiParam(value = "",required=true) @PathVariable("artistId") Long artistId);


    @ApiOperation(value = "Filmografia", nickname = "getArtistFilmography", notes = "", response = Object.class, tags={ "artists", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Filmografia consultada com sucesso", response = Object.class),
        @ApiResponse(code = 400, message = "Parâmetros invalidos - client side", response = ResponseError.class),
        @ApiResponse(code = 500, message = "Erro durante a consulta - server side", response = ResponseError.class) })
    @RequestMapping(value = "/artists/{artistId}/filmography",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> getArtistFilmography(@ApiParam(value = "",required=true) @PathVariable("artistId") Long artistId);


    @ApiOperation(value = "Lista os artistas", nickname = "listArtists", notes = "", response = Object.class, tags={ "artists", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Artistas consultados com sucesso", response = Object.class),
        @ApiResponse(code = 400, message = "Parâmetros invalidos - client side", response = ResponseError.class),
        @ApiResponse(code = 500, message = "Erro durante a criação - server side", response = ResponseError.class) })
    @RequestMapping(value = "/artists",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> listArtists(@ApiParam(value = "Página da listagem a ser retornada", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,@ApiParam(value = "Tamanho da paginação a ser utilizada no request", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size,@ApiParam(value = "Retorna itens cuja descrição se pareça com o valor informado") @Valid @RequestParam(value = "search", required = false) String search);


    @ApiOperation(value = "Atualização de artista", nickname = "updateArtist", notes = "", response = Object.class, tags={ "artists", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Artista atualizado com sucesso", response = Object.class),
        @ApiResponse(code = 400, message = "Parâmetros invalidos - client side", response = ResponseError.class),
        @ApiResponse(code = 500, message = "Erro durante a criação - server side", response = ResponseError.class) })
    @RequestMapping(value = "/artists/{artistId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Object> updateArtist(@ApiParam(value = "",required=true) @PathVariable("artistId") Long artistId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Artist artist);

}
