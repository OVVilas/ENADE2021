/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.ProvaDAO;
import br.edu.uniacademia.enade.model.Prova;
import br.edu.uniacademia.enade.model.Questao;
import br.edu.uniacademia.enade.model.Resultado;
import br.edu.uniacademia.enade.model.Usuario;
import java.io.Serializable;
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
public class FazerProvaController implements Serializable{
    private ResultadoController resultadoController;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    Prova prova = new Prova();
    
    public FazerProvaController(){
        resultadoController = new ResultadoController();
        prova = ProvaDAO.getInstance().buscarUltimaProvaAtiva(getUsuarioLogado().getIdUsuario());
    }
    
    public String finalizarProva() {
        double valorObtido = 0;
        double valorPorQuestao = 10.0 / prova.getQuestaoList().size();
        for (Questao questao : prova.getQuestaoList()) {
            String tipOQuestao = questao.getTipoQuestaoidTipoQuestao().getNomeTipoQuestao();
            if (tipOQuestao.equals("Objetiva Aberta") && !questao.getResposta().trim().equals("")) {
                valorObtido += valorPorQuestao;
            } else if (tipOQuestao.equals("Objetiva Fechada") && questao.getQuestaoCorreta().toString().equals(questao.getResposta())) {
                valorObtido += valorPorQuestao;
            }
        }

        Resultado resultado = new Resultado();
        resultado.setProvaidProva(prova);
        resultado.setUsuarioidUsuario(getUsuarioLogado());
        resultado.setValorObtido(Math.round(valorObtido * 10.0) / 10.0);
        resultadoController.setResultado(resultado);
        resultadoController.gravar();
        return "/resultados?faces-redirect=true";
    }
    
    private Usuario getUsuarioLogado() {
        return (Usuario) session.getAttribute("usuario");
    }
    
    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }
}

