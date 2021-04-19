/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.TipoQuestao;

/**
 *
 * @author Oswaldo
 */
public class TipoQuestaoDAO extends GenericDAO<TipoQuestao>{
    
   public static TipoQuestaoDAO tipoQuestaoDAO;
    
    public static TipoQuestaoDAO getInstance(){
        if(tipoQuestaoDAO == null){
            tipoQuestaoDAO = new TipoQuestaoDAO();
        }
        return tipoQuestaoDAO;
    }
}

    