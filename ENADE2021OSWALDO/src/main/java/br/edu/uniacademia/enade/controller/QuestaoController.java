/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.QuestaoDAO;
import br.edu.uniacademia.enade.model.Questao;
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
public class QuestaoController implements Serializable{

    Questao questao = new Questao();
    List<Questao> questoes = new ArrayList<>();
    List<Questao> questoesAtivas = new ArrayList<>();

    public QuestaoController() {
        questoes = QuestaoDAO.getInstance().buscarTodos();
        questoesAtivas = QuestaoDAO.getInstance().buscarQuestoesAtivas();
        questao = new Questao();
    }

    public void gravar() {
        QuestaoDAO.getInstance().merge(questao);
        questoes = QuestaoDAO.getInstance().buscarTodos();
        questoesAtivas = QuestaoDAO.getInstance().buscarQuestoesAtivas();
        questao = new Questao();
    }

    public void remover(ActionEvent actionEvent) {
        QuestaoDAO.getInstance().remover(questao.getIdQuestao());
        questoes = QuestaoDAO.getInstance().buscarTodos();
        questoesAtivas = QuestaoDAO.getInstance().buscarQuestoesAtivas();
        questao = new Questao();
    }
    
    public void onRowEdit(RowEditEvent event) {
        Questao obj = (Questao) event.getObject();
        setQuestao(obj);
        gravar();
        FacesMessage msg = new FacesMessage("Gravado", obj.getIdQuestao().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Questao> event) {
        FacesMessage msg = new FacesMessage("Cancelado", event.getObject().getIdQuestao().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
    
    public List<Questao> getQuestoesAtivas() {
        return questoesAtivas;
    }

    public void setQuestoesAtivas(List<Questao> questoesAtivas) {
        this.questoesAtivas = questoesAtivas;
    }
}