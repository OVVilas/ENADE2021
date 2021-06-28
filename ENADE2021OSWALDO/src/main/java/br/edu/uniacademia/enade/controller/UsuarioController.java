/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.UsuarioDAO;
import br.edu.uniacademia.enade.model.TipoUsuario;
import br.edu.uniacademia.enade.model.Usuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oswaldo
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable{
    

    Usuario usuario = new Usuario();
    List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios = UsuarioDAO.getInstance().buscarTodos();
        usuario = new Usuario();
    }

    public void gravar() {
        //UsuarioDAO.getInstance().merge(usuario);
        //usuarios = UsuarioDAO.getInstance().buscarTodos();
        //usuario = new Usuario();
        salvarUsuario(usuario, true);
    }

    public void remover(ActionEvent actionEvent) {
        UsuarioDAO.getInstance().remover(usuario.getIdUsuario());
        usuarios = UsuarioDAO.getInstance().buscarTodos();
        usuario = new Usuario();
    }
    
    public Usuario salvarUsuario(Usuario u, boolean buscarUsuarios) {
        //u.setSenha(EncryptUtil.encrypt(u.getSenha()));
        u.setSenha(u.getSenha());
        if (u.getTipoUsuarioidTipoUsuario() == null) {
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setIdTipoUsuario(1);
            tipoUsuario.setNomeTipoUsuario("Aluno");
            u.setTipoUsuarioidTipoUsuario(tipoUsuario);
        }
        Usuario usuarioPersisted = UsuarioDAO.getInstance().merge(u);
        if (buscarUsuarios) {
            usuarios = UsuarioDAO.getInstance().buscarTodos();
            usuario = new Usuario();
        }
        return usuarioPersisted;
    }

    public void onRowEdit(RowEditEvent event) {
        Usuario obj = (Usuario) event.getObject();
        setUsuario(obj);
        gravar();
        FacesMessage msg = new FacesMessage("Gravado", obj.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Usuario> event) {
        FacesMessage msg = new FacesMessage("Cancelado", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}