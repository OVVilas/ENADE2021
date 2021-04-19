/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Questao;

/**
 *
 * @author Oswaldo
 */
public class QuestaoDAO extends GenericDAO<Questao>{
    
public static QuestaoDAO QuestaoDAO;
    
    public static QuestaoDAO getInstance(){
        if(QuestaoDAO == null){
            QuestaoDAO = new QuestaoDAO();
        }
        return QuestaoDAO;
    }   
}
