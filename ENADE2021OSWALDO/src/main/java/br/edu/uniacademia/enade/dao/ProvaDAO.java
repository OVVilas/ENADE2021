/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Prova;

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

    public ProvaDAO() {
        super(Prova.class);
    }

}