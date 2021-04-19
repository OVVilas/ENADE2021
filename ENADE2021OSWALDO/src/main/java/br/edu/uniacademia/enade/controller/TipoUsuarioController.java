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
    List<TipoUsuario> tipoUsuarios = new ArrayList<TipoUsuario>();
    
    public TipoUsuarioController(){
        tipoUsuarios = TipoUsuarioDAO.getInstance().buscarTodos(TipoUsuario.class);
        tipoUsuario = new TipoUsuario();
    }
    
    public void gravar(TipoUsuario tipoUsuario){
        TipoUsuarioDAO.getInstance().persistir(tipoUsuario);
        tipoUsuarios = TipoUsuarioDAO.getInstance().buscarTodos(TipoUsuario.class);
    }
    
    public void remover(TipoUsuario tipoUsuario){
        TipoUsuarioDAO.getInstance().remover(TipoUsuario.class, tipoUsuario.getIdTipoUsuario());
        tipoUsuarios = TipoUsuarioDAO.getInstance().buscarTodos(TipoUsuario.class);
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
