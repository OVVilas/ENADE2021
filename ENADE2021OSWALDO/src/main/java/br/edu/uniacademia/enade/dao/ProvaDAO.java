/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Prova;
import java.util.Date;

/**
 *
 * @author Oswaldo
 */
public class ProvaDAO extends GenericDAO<Prova, Integer> {
    public static ProvaDAO ProvaDAO;

    public static ProvaDAO getInstance() {
        if (ProvaDAO == null) {
            ProvaDAO = new ProvaDAO();
        }
        return ProvaDAO;
    }
    
    public Prova buscarUltimaProvaAtiva(Integer idAluno) {
    final String jpql = " SELECT p FROM Prova p "
            + " LEFT JOIN p.resultadoList r "
            + " WHERE p.dataProva >= :dataProva AND (r.usuarioidUsuario IS NULL OR r.usuarioidUsuario.idUsuario <> :idAluno)";
    return (Prova) buscarResultadoUnico(entityManager.createQuery(jpql)
            .setParameter("dataProva", new Date())
            .setParameter("idAluno", idAluno));
    }

    public ProvaDAO() {
        super(Prova.class);
    }

}