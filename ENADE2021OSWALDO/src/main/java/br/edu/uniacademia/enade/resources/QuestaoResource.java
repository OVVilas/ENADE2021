/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.resources;

import br.edu.uniacademia.enade.dao.QuestaoDAO;
import br.edu.uniacademia.enade.model.Questao;
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
@Path("questao")
public class QuestaoResource {

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todosQuestao")
    public List<Questao> TodasQuestoes() {
        List<Questao> questoes = QuestaoDAO.getInstance().buscarTodos(Questao.class);
        return questoes;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getQuestao/{idQuestao}")
    public Questao GetQuestao(@PathParam("idQuestao") Integer idQuestao) {
        return QuestaoDAO.getInstance().buscar(Questao.class, idQuestao);
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluir/{idQuestao}")
    public String Excluir(@PathParam("idQuestao") Integer idQuestao) {
        try {
            Questao questao = new Questao(idQuestao, null);
            QuestaoDAO.getInstance().remover(Questao.class, questao.getIdQuestao());
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
            QuestaoDAO.getInstance().removeAll(Questao.class);
            return "Todos os registros excluídos com sucesso:";
        } catch (Exception e) {
            return "Erro ao excluir o registro " + e.getMessage();
        }
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrarQuestao")
    public String Cadastrar(Questao questao){
        Questao quest = new Questao();
        try{
            quest.setIdQuestao(questao.getIdQuestao());
            quest.setAlternativaA(questao.getAlternativaA());
            quest.setAlternativaB(questao.getAlternativaB());
            quest.setAlternativaC(questao.getAlternativaC());
            quest.setAlternativaD(questao.getAlternativaD());
            quest.setAlternativaE(questao.getAlternativaE());
            quest.setDescricaoQuestao(questao.getDescricaoQuestao());
            quest.setQuestaoCorreta(questao.getQuestaoCorreta());
            quest.setEstadoQuestao(questao.getEstadoQuestao());
            quest.setTipoQuestaoidTipoQuestao(questao.getTipoQuestaoidTipoQuestao());
            quest.setProvaList(questao.getProvaList());
            QuestaoDAO.getInstance().persistir(quest);
            return "Registro cadastrado com sucesso";
        }catch (Exception e){
            return "Erro ao atualizar um registro: " + e.getMessage();
        }
    }
    
    @PUT
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/alterarQuestao")
    public String Alterar(Questao questao){
        Questao quest = new Questao();
        try{
            quest.setIdQuestao(questao.getIdQuestao());
            quest.setAlternativaA(questao.getAlternativaA());
            quest.setAlternativaB(questao.getAlternativaB());
            quest.setAlternativaC(questao.getAlternativaC());
            quest.setAlternativaD(questao.getAlternativaD());
            quest.setAlternativaE(questao.getAlternativaE());
            quest.setDescricaoQuestao(questao.getDescricaoQuestao());
            quest.setQuestaoCorreta(questao.getQuestaoCorreta());
            quest.setEstadoQuestao(questao.getEstadoQuestao());
            quest.setTipoQuestaoidTipoQuestao(questao.getTipoQuestaoidTipoQuestao());
            quest.setProvaList(questao.getProvaList());
            QuestaoDAO.getInstance().persistir(quest);
            return "Registro alterado com sucesso";
        }catch (Exception e){
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
