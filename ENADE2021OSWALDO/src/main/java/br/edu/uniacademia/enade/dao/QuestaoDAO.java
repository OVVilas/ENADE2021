/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Questao;
import java.util.List;

/**
 *
 * @author Oswaldo
 */
public class QuestaoDAO extends GenericDAO<Questao, Integer> {

    public static QuestaoDAO questaoDAO;

    public QuestaoDAO() {
        super(Questao.class);
    }

    public static QuestaoDAO getInstance() {
        if (questaoDAO == null) {
            questaoDAO = new QuestaoDAO();
        }
        return questaoDAO;
    }

    public List<Questao> buscarQuestoesAtivas() {
        return entityManager.createNamedQuery("Questao.findByEstadoQuestao", Questao.class).setParameter("estadoQuestao", true).getResultList();
    }
}