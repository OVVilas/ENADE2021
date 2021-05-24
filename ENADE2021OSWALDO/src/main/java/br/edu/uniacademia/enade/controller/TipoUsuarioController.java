/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.TipoUsuarioDAO;
import br.edu.uniacademia.enade.model.TipoUsuario;
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
public class TipoUsuarioController implements Serializable{

    TipoUsuario tipoUsuario = new TipoUsuario();
    List<TipoUsuario> tipoUsuarios = new ArrayList<>();

    public TipoUsuarioController() {
        tipoUsuarios = TipoUsuarioDAO.getInstance().buscarTodos();
        tipoUsuario = new TipoUsuario();
    }

    public void gravar() {
        TipoUsuarioDAO.getInstance().merge(tipoUsuario);
        tipoUsuarios = TipoUsuarioDAO.getInstance().buscarTodos();
        tipoUsuario = new TipoUsuario();
    }

    public void remover(ActionEvent actionEvent) {
        TipoUsuarioDAO.getInstance().remover(tipoUsuario.getIdTipoUsuario());
        tipoUsuarios = TipoUsuarioDAO.getInstance().buscarTodos();
        tipoUsuario = new TipoUsuario();
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<TipoUsuario> getTipoUsuarios() {
        return tipoUsuarios;
    }

    public void setTipoUsuarios(List<TipoUsuario> tipoUsuarios) {
        this.tipoUsuarios = tipoUsuarios;
    }

}