package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.CaminhaoDAO;
import dao.Conexao;
import model.Caminhao;
import util.Util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuCaminhaoController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabPaneCadastro;

	@FXML
	private Tab tabPanePesquisa;

	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtFabricante;

	@FXML
	private TextField txtRegistro;

	@FXML
	private TextField txtFabricantePesquisa;

	@FXML
	private TextField txtRegistroPesquisa;
	
	@FXML
	private TextField txtAno;

	@FXML
	private TextField txtModelo;
	
	@FXML
	private Button btnIncluir;
	
	@FXML
	private Button btnEditar;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnPesquisar;
	
	@FXML
	private Button btnLimparPesquisa;
	
    @FXML
    private TableView<Caminhao> tableViewMenuCaminhao;

    @FXML
    private TableColumn<Caminhao,String> colunaFabricante;

    @FXML
    private TableColumn<Caminhao,String> colunaRegistro;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException {

		Caminhao c = new Caminhao();
		CaminhaoDAO cDAO = new CaminhaoDAO();
		Util u = new Util();
		
		c.setFabricante(this.txtFabricante.getText());
		c.setRegistro(this.txtRegistro.getText());
		c.setAno(this.txtAno.getText());
		c.setModelo(this.txtModelo.getText());
		
		//	Verifica se algum campo está em branco
		if(c.getFabricante().trim().equals("") || c.getRegistro().trim().equals("") || c.getAno().trim().equals("") || c.getModelo().trim().equals("")){
			u.mensagemErro("Preencha todos os campos!");	
		}
		else
		{
			cDAO.incluir(c);
			u.mensagemInformacao("Cadastro Realizado com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnExcluir(ActionEvent event) throws SQLException {

		Caminhao c = new Caminhao();
		CaminhaoDAO cDAO = new CaminhaoDAO();
		Util u = new Util();
		
		c.setId_caminhao(Integer.parseInt(this.txtCodigo.getText()));
		
		//	Verifica se algum campo está em branco
		if(Integer.toString((c.getId_caminhao())).trim().equals("")){
			//u.mensagemErro("Preencha o código do Caminhão!");	
		}
		else
		{
			cDAO.excluir(c.getId_caminhao());
			u.mensagemInformacao("Exclusão Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnAlterar(ActionEvent event) throws SQLException {

		Caminhao c = new Caminhao();
		CaminhaoDAO cDAO = new CaminhaoDAO();
		Util u = new Util();
		c.setId_caminhao(Integer.parseInt(this.txtCodigo.getText()));
		c.setFabricante(this.txtFabricante.getText());
		c.setRegistro(this.txtRegistro.getText());
		c.setAno(this.txtAno.getText());
		c.setModelo(this.txtModelo.getText());
		//	Verifica se algum campo está em branco
		if(c.getFabricante().trim().equals("") || c.getRegistro().trim().equals("") || c.getAno().trim().equals("") || c.getModelo().trim().equals("")){
			//u.mensagemErro("Preencha todos os campos!");	
		}
		else
		{
			cDAO.alterar(c);
			u.mensagemInformacao("Alteração Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnPesquisar(ActionEvent event) throws SQLException {
		// String pesquisaRegistro = txtRegistroPesquisa.getText();
		String pesquisaFabricante = txtFabricantePesquisa.getText();
		ArrayList<Caminhao> cVazia = new ArrayList<Caminhao>();
		ObservableList obl = FXCollections.observableArrayList(cVazia);
		this.tableViewMenuCaminhao.setItems(obl);
		Caminhao c = new Caminhao();
		CaminhaoDAO cDAO = new CaminhaoDAO();
		ArrayList<Caminhao> getCaminhoes = new ArrayList<Caminhao>();
		getCaminhoes = cDAO.consultarCaminhoes(pesquisaFabricante);
		obl = FXCollections.observableArrayList(getCaminhoes);
		this.tableViewMenuCaminhao.setItems(obl);
		this.colunaFabricante.setCellValueFactory(new PropertyValueFactory<Caminhao, String>("Fabricante"));
		this.colunaRegistro.setCellValueFactory(new PropertyValueFactory<Caminhao, String>("Registro"));	
	}
		
	@FXML
	void onActionBtnLimpar(ActionEvent event) {
		this.limpaTela();
	}
	
	public void limpaTela()
	{
		this.txtFabricante.setText("");
		this.txtRegistro.setText("");
		this.txtCodigo.setText("");
		this.txtAno.setText("");
		this.txtModelo.setText("");
	}
	
	@FXML
	void onActionBtnLimparPesquisa(ActionEvent event) {
		this.limpaTelaPesquisa();
	}

	public void limpaTelaPesquisa()
	{
		this.txtFabricantePesquisa.setText("");
		this.tableViewMenuCaminhao.getItems().clear();
	}
	
	@FXML
	void onActionBtnEditar(ActionEvent event) throws SQLException {
		if(tableViewMenuCaminhao.getSelectionModel().getSelectedItem() != null){
			Object Caminhao = new Caminhao();
			CaminhaoDAO CaminhaoDAO = new CaminhaoDAO();
			Caminhao = CaminhaoDAO.localizarPorRegistro(tableViewMenuCaminhao.getSelectionModel().getSelectedItem().getRegistro());
			txtCodigo.setText(Integer.toString(((Caminhao) Caminhao).getId_caminhao()));
			txtFabricante.setText(((Caminhao) Caminhao).getFabricante());
			txtRegistro.setText(((Caminhao) Caminhao).getRegistro());
			txtAno.setText(((Caminhao) Caminhao).getAno());
			txtModelo.setText(((Caminhao) Caminhao).getModelo());
			tabPane.getSelectionModel().select(tabPaneCadastro);
			limpaTelaPesquisa();
		}
	}
}
