/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.resources;

import br.edu.uniacademia.enade.dao.UsuarioDAO;
import br.edu.uniacademia.enade.model.Usuario;
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
@Path("usuario")
public class UsuarioResource {
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todosUsuario")
    public List<Usuario> TodosUsuarios() {
        List<Usuario> usuarios = UsuarioDAO.getInstance().buscarTodos(Usuario.class);
        return usuarios;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getUsuario/{id}")
    public Usuario GetUsuario(@PathParam("id") Integer id) {
        return UsuarioDAO.getInstance().buscar(Usuario.class, id);
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluir/{idUsuario}")
    public String Excluir(@PathParam("idUsuario") Integer idUsuario) {
        try {
            UsuarioDAO.getInstance().remover(Usuario.class, idUsuario);
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
            UsuarioDAO.getInstance().removeAll(Usuario.class);
            return "Todos os registros excluídos com sucesso";
        } catch (Exception e) {
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrarUsuario")
    public String Cadastrar(Usuario usuario){
        Usuario us = new Usuario();
        try{
            us.setIdUsuario((Integer) usuario.getId());
            us.setNome(usuario.getNome());
            us.setEmail(usuario.getNome());
            us.setSenha(usuario.getSenha());
            us.setTipoUsuarioidTipoUsuario(usuario.getTipoUsuarioidTipoUsuario());
            UsuarioDAO.getInstance().persistir(usuario);
            return "Cadastro salvo";
        }catch (Exception e){
            return "Erro ao realizar o cadastro: " + e.getMessage();
        }
    }
    
    @PUT
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/alterarUsuario")
    public String Alterar(Usuario usuario){
        Usuario us = new Usuario();
        try{
            us.setIdUsuario((Integer) usuario.getId());
            us.setNome(usuario.getNome());
            us.setEmail(usuario.getNome());
            us.setSenha(usuario.getSenha());
            us.setTipoUsuarioidTipoUsuario(usuario.getTipoUsuarioidTipoUsuario());
            UsuarioDAO.getInstance().persistir(usuario);
            return "Registro salvo";
        }catch (Exception e){
            return "Erro ao realizar o cadastro: " + e.getMessage();
        }
    }

    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
}
