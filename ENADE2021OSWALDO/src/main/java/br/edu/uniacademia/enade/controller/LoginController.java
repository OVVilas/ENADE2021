/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.UsuarioDAO;
import br.edu.uniacademia.enade.model.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oswaldo
 */
@Named
@ViewScoped
public class LoginController implements Serializable{
    private UsuarioController usuarioController;
    private Usuario usuarioLogin;
    private Usuario usuarioCadastrar;
    
    public LoginController(){
        usuarioController = new UsuarioController();
        usuarioLogin = new Usuario();
        usuarioCadastrar = new Usuario();
    }
    
    public String cadastrar() {
        Usuario usuarioFind = UsuarioDAO.getInstance().buscarPorEmail(usuarioCadastrar);
        if (usuarioFind == null) {
            Usuario usuarioPersisted = usuarioController.salvarUsuario(usuarioCadastrar, false);
            setUsuarioLogin(usuarioPersisted);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("usuario", usuarioLogin);
            session.setAttribute("isProf", true);
            return "/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um usuário com esse E-mail. Faça o login.", null));
            return null;
        }
    }
    
    public String login() {
        usuarioLogin.setSenha(usuarioLogin.getSenha());
        Usuario usuarioFind = UsuarioDAO.getInstance().logIn(usuarioLogin);
        if (usuarioFind == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha inválidos!", null));
            return null;
        } else {
            setUsuarioLogin(usuarioFind);            
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("usuario", usuarioLogin);
            if(usuarioLogin.getTipoUsuarioidTipoUsuario().getNomeTipoUsuario().equals("Professor")){
               session.setAttribute("isProf", true); 
            }else{
                session.setAttribute("isProf", false);
            }
            return "/index?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    public String goHome() {
        return "/index?faces-redirect=true";
    }

    public boolean isProfessor() {  
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return session.getAttribute("isProf").toString().equals("true");
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Usuario getUsuarioCadastrar() {
        return usuarioCadastrar;
    }

    public void setUsuarioCadastrar(Usuario usuarioCadastrar) {
        this.usuarioCadastrar = usuarioCadastrar;
    }
    
}
