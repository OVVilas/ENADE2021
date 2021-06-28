/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Usuario;
import java.util.List;

/**
 *
 * @author Oswaldo
 */
public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

    public static UsuarioDAO UsuarioDAO;

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public static UsuarioDAO getInstance() {
        if (UsuarioDAO == null) {
            UsuarioDAO = new UsuarioDAO();
        }
        return UsuarioDAO;
    }
    
    public Usuario logIn(Usuario usuario) {
        return (Usuario) buscarResultadoUnico(entityManager.createNamedQuery("Usuario.findByEmailAndSenha")
                .setParameter("email", usuario.getEmail())
                .setParameter("senha", usuario.getSenha()));
    }
    
    public Usuario buscarPorEmail(Usuario usuario) {
        return (Usuario) buscarResultadoUnico(entityManager.createNamedQuery("Usuario.findByEmail")
                .setParameter("email", usuario.getEmail()));
    }
    
    public List<Usuario> buscarTodosAlunos() {
        return entityManager.createNamedQuery("Usuario.findAllAlunos").getResultList();
    }
}