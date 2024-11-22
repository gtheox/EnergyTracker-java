package br.com.fiap.resource;

import br.com.fiap.beans.ConsumoEnergetico;
import br.com.fiap.bo.ConsumoEnergeticoBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/consumos")
public class ConsumoEnergeticoResource {

    private ConsumoEnergeticoBO consumoEnergeticoBO;

    public ConsumoEnergeticoResource() {
        try {
            consumoEnergeticoBO = new ConsumoEnergeticoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarConsumos() {
        try {
            List<ConsumoEnergetico> consumos = consumoEnergeticoBO.listarConsumos();
            return Response.ok(consumos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar consumos").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarConsumo(@PathParam("id") int id) {
        try {
            ConsumoEnergetico consumo = consumoEnergeticoBO.buscarConsumo(id);
            if (consumo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Consumo não encontrado").build();
            }
            return Response.ok(consumo).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar consumo").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirConsumo(ConsumoEnergetico consumo, @Context UriInfo uriInfo) {
        try {
            String resultado = consumoEnergeticoBO.inserirConsumo(consumo);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(consumo.getIdConsumo()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir consumo").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarConsumo(ConsumoEnergetico consumo, @PathParam("id") int id) {
        try {
            consumo.setIdConsumo(id);
            String resultado = consumoEnergeticoBO.atualizarConsumo(consumo);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar consumo").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarConsumo(@PathParam("id") int id) {
        try {
            String resultado = consumoEnergeticoBO.deletarConsumo(id);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar consumo").build();
        }
    }
}
