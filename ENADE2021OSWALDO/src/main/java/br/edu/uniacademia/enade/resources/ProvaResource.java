/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.resources;

import br.edu.uniacademia.enade.dao.ProvaDAO;
import br.edu.uniacademia.enade.model.Prova;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oswaldo
 */
@Path("prova")
public class ProvaResource {

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todosProva")
    public List<Prova> TodasProvas() {
        List<Prova> provas = ProvaDAO.getInstance().buscarTodos(Prova.class);
        return provas;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getProva/{idProva}")
    public Prova GetProva(@PathParam("idProva") Integer idProva) {
        return (Prova) ProvaDAO.getInstance().buscar(Prova.class, idProva);
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluir/{idProva}")
    public String Excluir(@PathParam("idProva") Integer idProva) {
        try {
            ProvaDAO.getInstance().remover(Prova.class, idProva);
            return "Registro excluído com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluirTodos")
    public String ExcluirTodas() {
        try {
            ProvaDAO.getInstance().removeAll(Prova.class);
            return "Todos os registros excluídos com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String Cadastrar(Prova prova) {
        Prova prov = new Prova();
        try {
            prov.setIdProva(prova.getIdProva());
            prov.setDataProva(prova.getDataProva());
            prov.setQuestaoList(prova.getQuestaoList());
            prov.setResultadoList(prova.getResultadoList());
            ProvaDAO.getInstance().persistir(prov);
            return "Registro cadastrado com sucesso";
        } catch (Exception e) {
            return "Erro ao cadastrar um registro: " + e.getMessage();
        }
    }

    @PUT
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/alterar")
    public String Alterar(Prova prova) {
        Prova prov = new Prova();
        try {
            prov.setIdProva(prova.getIdProva());
            prov.setDataProva(prova.getDataProva());
            prov.setQuestaoList(prova.getQuestaoList());
            prov.setResultadoList(prova.getResultadoList());
            return ProvaDAO.getInstance().persistir(prov).toString();
        } catch (Exception e) {
            return "Erro ao atualizar um registro: " + e.getMessage();
        }
    }

    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
}
