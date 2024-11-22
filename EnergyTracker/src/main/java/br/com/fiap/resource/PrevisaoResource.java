package br.com.fiap.resource;

import br.com.fiap.beans.Previsao;
import br.com.fiap.bo.PrevisaoBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/previsoes")
public class PrevisaoResource {

    private PrevisaoBO previsaoBO;

    public PrevisaoResource() {
        try {
            previsaoBO = new PrevisaoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPrevisoes() {
        try {
            List<Previsao> previsoes = previsaoBO.listarPrevisoes();
            return Response.ok(previsoes).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar previsões").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPrevisao(@PathParam("id") int id) {
        try {
            Previsao previsao = previsaoBO.buscarPrevisao(id);
            if (previsao == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Previsão não encontrada").build();
            }
            return Response.ok(previsao).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar previsão").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirPrevisao(Previsao previsao, @Context UriInfo uriInfo) {
        try {
            String resultado = previsaoBO.inserirPrevisao(previsao);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(previsao.getIdPrevisao()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir previsão").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPrevisao(Previsao previsao, @PathParam("id") int id) {
        try {
            previsao.setIdPrevisao(id);
            String resultado = previsaoBO.atualizarPrevisao(previsao);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar previsão").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarPrevisao(@PathParam("id") int id) {
        try {
            String resultado = previsaoBO.deletarPrevisao(id);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar previsão").build();
        }
    }
}
