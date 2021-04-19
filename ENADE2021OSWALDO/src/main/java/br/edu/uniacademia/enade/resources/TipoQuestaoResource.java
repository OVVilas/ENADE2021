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
import javax.ws.rs.core.Response;

/**
 *
 * @author Oswaldo
 */
@Path("tipoquestao")
public class TipoQuestaoResource {
    
     @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todosTipoQuestao")
    public List<TipoQuestao> TodosTipoQuestao(){
         return TipoQuestaoDAO.getInstance().buscarTodos(TipoQuestao.class);
    }
    
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todosTipoQuestao/(codigo)")
    public TipoQuestao GetTipoQuestao(@PathParam("idTipoQuestao") int idTipoQuestao){
        return TipoQuestaoDAO.getInstance().buscar(TipoQuestao.class, idTipoQuestao);
    }
    
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluirTipoQuestao/(codigo)")
    public String Excluir(@PathParam("codigo") Integer codigo){
        
        try{
            TipoQuestaoDAO.getInstance().remover(TipoQuestao.class, codigo);
            return "Registro excluído com sucesso";
        }catch(Exception e){
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }
    
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluirTodosTipoQuestao")
    public String ExcluirTodos(){
        try{
            TipoQuestaoDAO.getInstance().removeAll(TipoQuestao.class);
            return "Todos registros excluídos com sucesso";
        }catch(Exception e){
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }
    
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrarTipoQuestao")
    public String Cadastrar(TipoQuestao tipoQuestao){
        TipoQuestao tp = new TipoQuestao();
        try{
            tp.setIdTipoQuestao(tipoQuestao.getIdTipoQuestao());
        tp.setNomeTipoQuestao(tipoQuestao.getNomeTipoQuestao());
            TipoQuestaoDAO.getInstance().persistir(tipoQuestao);
            return "Registro atualizado com sucesso";
        }catch (Exception e){
            return "Erro ao atualizar um registro: " + e.getMessage();
        }
    }
    
    @PUT
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/alterarTipoQuestao")
    public String Alterar(TipoQuestao tipoQuestao){
        TipoQuestao tp = new TipoQuestao();
        try{
            tp.setIdTipoQuestao(tipoQuestao.getIdTipoQuestao());
            tp.setNomeTipoQuestao(tipoQuestao.getNomeTipoQuestao());
            TipoQuestaoDAO.getInstance().persistir(tipoQuestao);
            return "Registro atualizado com sucesso";
        }catch (Exception e){
            return "Erro ao atualizar um registro: " + e.getMessage();
        }
    }
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
