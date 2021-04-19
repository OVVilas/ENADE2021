/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.UsuarioDAO;
import br.edu.uniacademia.enade.model.Usuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
        usuarios = UsuarioDAO.getInstance().buscarTodos(Usuario.class);
        usuario = new Usuario();
    }

    public void gravar(ActionEvent actionEvent) {
        UsuarioDAO.getInstance().persistir(usuario);
        usuarios = UsuarioDAO.getInstance().buscarTodos(Usuario.class);
        usuario = new Usuario();
    }

    public void remover(Usuario usuario){
        UsuarioDAO.getInstance().remover(Usuario.class, usuario.getIdUsuario());
        usuarios = UsuarioDAO.getInstance().buscarTodos(Usuario.class);
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
