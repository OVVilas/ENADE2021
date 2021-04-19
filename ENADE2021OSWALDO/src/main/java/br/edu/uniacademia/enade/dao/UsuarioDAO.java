/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Usuario;

/**
 *
 * @author Oswaldo
 */
public class UsuarioDAO extends GenericDAO<Usuario> {
    
    public static UsuarioDAO usuarioDAO;
    
    public static UsuarioDAO getInstance(){
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }   
}
