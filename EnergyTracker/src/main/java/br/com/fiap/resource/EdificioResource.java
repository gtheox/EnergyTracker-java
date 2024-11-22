package br.com.fiap.resource;

import br.com.fiap.beans.Edificio;
import br.com.fiap.bo.EdificioBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/edificios")
public class EdificioResource {

    private EdificioBO edificioBO;

    public EdificioResource() {
        try {
            edificioBO = new EdificioBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEdificios() {
        try {
            List<Edificio> edificios = edificioBO.listarEdificios();
            return Response.ok(edificios).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar edifícios").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEdificio(@PathParam("id") int id) {
        try {
            Edificio edificio = edificioBO.buscarEdificio(id);
            if (edificio == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Edifício não encontrado").build();
            }
            return Response.ok(edificio).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar edifício").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirEdificio(Edificio edificio, @Context UriInfo uriInfo) {
        try {
            String resultado = edificioBO.inserirEdificio(edificio);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(edificio.getIdEdificio()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir edifício").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEdificio(Edificio edificio, @PathParam("id") int id) {
        try {
            edificio.setIdEdificio(id);
            String resultado = edificioBO.atualizarEdificio(edificio);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar edifício").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarEdificio(@PathParam("id") int id) {
        try {
            String resultado = edificioBO.deletarEdificio(id);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar edifício").build();
        }
    }
}
