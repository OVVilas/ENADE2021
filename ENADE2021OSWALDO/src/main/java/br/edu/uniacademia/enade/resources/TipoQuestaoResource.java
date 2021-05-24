/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.resources;

import br.edu.uniacademia.enade.dao.TipoQuestaoDAO;
import br.edu.uniacademia.enade.model.TipoQuestao;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Oswaldo
 */
@Path("tipoquestao")
public class TipoQuestaoResource {
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todosTipoQuestao")
    public List<TipoQuestao> TodosTipoQuestao() {
        List<TipoQuestao> tipoQuestao = TipoQuestaoDAO.getInstance().buscarTodos();
        return tipoQuestao;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getTipoQuestao/{idTipoQuestao}")
    public TipoQuestao GetTipoQuestao(@PathParam("idTipoQuestao") Integer idTipoQuestao) {
        return TipoQuestaoDAO.getInstance().buscar(idTipoQuestao);
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluir/{codigo}")
    public String Excluir(@PathParam("codigo") Integer codigo) {
        try {
            TipoQuestao tipoQuestao = new TipoQuestao(codigo);
            TipoQuestaoDAO.getInstance().remover(tipoQuestao.getIdTipoQuestao());
            return "Registro excluído com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }
    
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluirTodos")
    public String ExcluirTodos() {
        try {
            TipoQuestaoDAO.getInstance().removerAll();
            return "Todos os registros excluídos com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String Cadastrar(TipoQuestao tipoQuestao) {
        TipoQuestao tq = new TipoQuestao();
        try {
            tq.setIdTipoQuestao(tipoQuestao.getIdTipoQuestao());
            tq.setNomeTipoQuestao(tipoQuestao.getNomeTipoQuestao());
            TipoQuestaoDAO.getInstance().merge(tq);
            return "Registro cadastrado com sucesso";
        } catch (Exception e) {
            return "Erro ao cadastrar um registro: " + e.getMessage();
        }
    }

    @PUT
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/alterar")
    public String Alterar(TipoQuestao tipoQuestao) {
        TipoQuestao tq = new TipoQuestao();
        try {
            tq.setIdTipoQuestao(tipoQuestao.getIdTipoQuestao());
            tq.setNomeTipoQuestao(tipoQuestao.getNomeTipoQuestao());
            return TipoQuestaoDAO.getInstance().merge(tq).toString();
        } catch (Exception e) {
            return "Erro ao atualizar um registro: " + e.getMessage();
        }
    }
}