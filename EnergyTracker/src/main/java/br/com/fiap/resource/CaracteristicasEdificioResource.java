package br.com.fiap.resource;

import br.com.fiap.beans.CaracteristicasEdificio;
import br.com.fiap.bo.CaracteristicasEdificioBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/caracteristicas")
public class CaracteristicasEdificioResource {

    private CaracteristicasEdificioBO caracteristicasEdificioBO;

    public CaracteristicasEdificioResource() {
        try {
            caracteristicasEdificioBO = new CaracteristicasEdificioBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCaracteristicas() {
        try {
            List<CaracteristicasEdificio> caracteristicas = caracteristicasEdificioBO.listarCaracteristicas();
            return Response.ok(caracteristicas).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar características").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCaracteristica(@PathParam("id") int id) {
        try {
            CaracteristicasEdificio caracteristica = caracteristicasEdificioBO.buscarCaracteristica(id);
            if (caracteristica == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Característica não encontrada").build();
            }
            return Response.ok(caracteristica).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar característica").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCaracteristica(CaracteristicasEdificio caracteristica, @Context UriInfo uriInfo) {
        try {
            String resultado = caracteristicasEdificioBO.inserirCaracteristica(caracteristica);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(caracteristica.getIdCaracteristica()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir característica").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCaracteristica(CaracteristicasEdificio caracteristica, @PathParam("id") int id) {
        try {
            caracteristica.setIdCaracteristica(id);
            String resultado = caracteristicasEdificioBO.atualizarCaracteristica(caracteristica);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar característica").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarCaracteristica(@PathParam("id") int id) {
        try {
            String resultado = caracteristicasEdificioBO.deletarCaracteristica(id);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar característica").build();
        }
    }
}
