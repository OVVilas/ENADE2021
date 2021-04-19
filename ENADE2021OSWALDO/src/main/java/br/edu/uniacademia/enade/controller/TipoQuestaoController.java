/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.TipoQuestaoDAO;
import br.edu.uniacademia.enade.model.TipoQuestao;
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
public class TipoQuestaoController implements Serializable{
     TipoQuestao tipoQuestao = new TipoQuestao();
    List<TipoQuestao> tipoQuestoes = new ArrayList<TipoQuestao>();
    
    public TipoQuestaoController(){
        tipoQuestoes = TipoQuestaoDAO.getInstance().buscarTodos(TipoQuestao.class);
        tipoQuestao = new TipoQuestao();
    }
    
    public void gravar(TipoQuestao tipoQuestao){
        TipoQuestaoDAO.getInstance().persistir(tipoQuestao);
        tipoQuestoes = TipoQuestaoDAO.getInstance().buscarTodos(TipoQuestao.class);
    }
    
    public void remover(TipoQuestao tipoQuestao){
        TipoQuestaoDAO.getInstance().remover(TipoQuestao.class, tipoQuestao.getIdTipoQuestao());
        tipoQuestoes = TipoQuestaoDAO.getInstance().buscarTodos(TipoQuestao.class);
    }

    public TipoQuestao getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(TipoQuestao tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

    public List<TipoQuestao> getTipoQuestoes() {
        return tipoQuestoes;
    }

    public void setTipoQuestoes(List<TipoQuestao> tipoQuestoes) {
        this.tipoQuestoes = tipoQuestoes;
    }

    
}
