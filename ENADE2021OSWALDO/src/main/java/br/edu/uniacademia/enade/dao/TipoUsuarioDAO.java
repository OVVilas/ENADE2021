/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.TipoUsuario;

/**
 *
 * @author Oswaldo
 */
public class TipoUsuarioDAO extends GenericDAO<TipoUsuario> {
    
public static TipoUsuarioDAO tipoUsuarioDAO;
    
    public static TipoUsuarioDAO getInstance(){
        if(tipoUsuarioDAO == null){
            tipoUsuarioDAO = new TipoUsuarioDAO();
        }
        return tipoUsuarioDAO;
    }
}
