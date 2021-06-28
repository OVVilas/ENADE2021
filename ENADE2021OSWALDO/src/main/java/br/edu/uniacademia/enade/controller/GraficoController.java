/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.controller;

import br.edu.uniacademia.enade.dao.ResultadoDAO;
import br.edu.uniacademia.enade.dao.UsuarioDAO;
import br.edu.uniacademia.enade.model.Resultado;
import br.edu.uniacademia.enade.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Oswaldo
 */
@Named
@SessionScoped
public class GraficoController implements Serializable{
    List<Resultado> ultimosDezResultados = new ArrayList<>();
    List<Resultado> totalResultados = new ArrayList<>();
    List<Usuario> totalAlunos = new ArrayList<>();
    private PieChartModel pieModel;
    private BarChartModel barModel;
    
    public GraficoController() {
        ultimosDezResultados = ResultadoDAO.getInstance().buscarUltimosDezResultados();
        totalResultados = ResultadoDAO.getInstance().buscarTodos();
        totalAlunos = UsuarioDAO.getInstance().buscarTodosAlunos();
    }
    
    @PostConstruct
    public void init() {
        createBarModel();
        createPieModel();
    }

    private void createBarModel() {
        ChartSeries notas = new ChartSeries();
        notas.setLabel("Notas");

        ultimosDezResultados.stream().forEach(it -> notas.set(it.getUsuarioidUsuario().getNome(), it.getValorObtido()));

        BarChartModel model = new BarChartModel();
        model.addSeries(notas);
        barModel = model;

        barModel.setTitle("Notas dos últimos 10 alunos que fizeram a prova");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Alunos");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Notas");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    
    private void createPieModel() {
        pieModel = new PieChartModel();

        double qtdAlunosFizeram = (double) totalResultados.size() / (double) totalAlunos.size() * 100;
        pieModel.set("Fizeram", qtdAlunosFizeram);
        pieModel.set("Não fizeram", 100 - qtdAlunosFizeram);

        pieModel.setTitle("Alunos que fizeram e não fizeram a prova (%)");
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<Resultado> getUltimosDezResultados() {
        return ultimosDezResultados;
    }

    public void setUltimosDezResultados(List<Resultado> ultimosDezResultados) {
        this.ultimosDezResultados = ultimosDezResultados;
    }

    public List<Resultado> getTotalResultados() {
        return totalResultados;
    }

    public void setTotalResultados(List<Resultado> totalResultados) {
        this.totalResultados = totalResultados;
    }

    public List<Usuario> getTotalAlunos() {
        return totalAlunos;
    }

    public void setTotalAlunos(List<Usuario> totalAlunos) {
        this.totalAlunos = totalAlunos;
    }

}