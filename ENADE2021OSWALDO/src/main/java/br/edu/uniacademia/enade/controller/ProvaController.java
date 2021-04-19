/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.model.Prova;
import br.edu.uniacademia.enade.dao.ProvaDAO;
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
public class ProvaController implements Serializable{

    Prova prova = new Prova();
    List<Prova> provas = new ArrayList<>();

    public ProvaController() {
        provas = ProvaDAO.getInstance().buscarTodos(Prova.class);
        prova = new Prova();
    }

    public void gravar(ActionEvent actionEvent) {
        ProvaDAO.getInstance().persistir(prova);
        provas = ProvaDAO.getInstance().buscarTodos(Prova.class);
    }

    public void remover(ActionEvent actionEvent) {
        ProvaDAO.getInstance().remover(Prova.class, prova.getIdProva());
        provas = ProvaDAO.getInstance().buscarTodos(Prova.class);
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public List<Prova> getProvas() {
        return provas;
    }

    public void setProvas(List<Prova> provas) {
        this.provas = provas;
    }

}
