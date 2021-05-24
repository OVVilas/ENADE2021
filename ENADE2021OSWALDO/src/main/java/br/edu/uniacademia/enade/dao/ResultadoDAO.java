/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Resultado;

/**
 *
 * @author Oswaldo
 */
public class ResultadoDAO extends GenericDAO<Resultado, Integer> {

    public static ResultadoDAO resultadoDAO;

    public ResultadoDAO() {
        super(Resultado.class);
    }

    public static ResultadoDAO getInstance() {
        if (resultadoDAO == null) {
            resultadoDAO = new ResultadoDAO();
        }
        return resultadoDAO;
    }

}