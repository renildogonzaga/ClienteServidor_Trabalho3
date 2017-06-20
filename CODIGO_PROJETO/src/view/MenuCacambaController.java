package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.CacambaDAO;
import dao.Conexao;
import model.Cacamba;
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

public class MenuCacambaController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabPaneCadastro;

	@FXML
	private Tab tabPanePesquisa;

	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtFabricacao;

	@FXML
	private TextField txtRegistro;

	@FXML
	private TextField txtFabricacaoPesquisa;

	@FXML
	private TextField txtRegistroPesquisa;

	@FXML
	private TextField txtDescricao;
	
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
    private TableView<Cacamba> tableViewMenuCacamba;

    @FXML
    private TableColumn<Cacamba,String> colunaFabricacao;

    @FXML
    private TableColumn<Cacamba,String> colunaRegistro;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException {

		Cacamba c = new Cacamba();
		CacambaDAO cDAO = new CacambaDAO();
		Util u = new Util();
		
		c.setFabricacao(this.txtFabricacao.getText());
		c.setRegistro(this.txtRegistro.getText());
		c.setDescricao(this.txtDescricao.getText());
		
		//	Verifica se algum campo está em branco
		if(c.getFabricacao().trim().equals("") || c.getRegistro().trim().equals("") || c.getDescricao().trim().equals("")){
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

		Cacamba c = new Cacamba();
		CacambaDAO cDAO = new CacambaDAO();
		Util u = new Util();
		
		c.setId_cacamba(Integer.parseInt(this.txtCodigo.getText()));
		
		//	Verifica se algum campo está em branco
		if(Integer.toString((c.getId_cacamba())).trim().equals("")){
			//u.mensagemErro("Preencha o código da Caçamba!");	
		}
		else
		{
			cDAO.excluir(c.getId_cacamba());
			u.mensagemInformacao("Exclusão Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnAlterar(ActionEvent event) throws SQLException {

		Cacamba c = new Cacamba();
		CacambaDAO cDAO = new CacambaDAO();
		Util u = new Util();
		c.setId_cacamba(Integer.parseInt(this.txtCodigo.getText()));
		c.setFabricacao(this.txtFabricacao.getText());
		c.setRegistro(this.txtRegistro.getText());
		c.setDescricao(this.txtDescricao.getText());
		//	Verifica se algum campo está em branco
		if(c.getFabricacao().trim().equals("") || c.getRegistro().trim().equals("") || c.getDescricao().trim().equals("")){
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
		String pesquisaFabricacao = txtFabricacaoPesquisa.getText();
		ArrayList<Cacamba> cVazia = new ArrayList<Cacamba>();
		ObservableList obl = FXCollections.observableArrayList(cVazia);
		this.tableViewMenuCacamba.setItems(obl);
		Cacamba c = new Cacamba();
		CacambaDAO cDAO = new CacambaDAO();
		ArrayList<Cacamba> getCacambas = new ArrayList<Cacamba>();
		getCacambas = cDAO.consultarCacambas(pesquisaFabricacao);
		obl = FXCollections.observableArrayList(getCacambas);
		this.tableViewMenuCacamba.setItems(obl);
		this.colunaFabricacao.setCellValueFactory(new PropertyValueFactory<Cacamba, String>("Fabricacao"));
		this.colunaRegistro.setCellValueFactory(new PropertyValueFactory<Cacamba, String>("Registro"));	
	}
		
	@FXML
	void onActionBtnLimpar(ActionEvent event) {
		this.limpaTela();
	}
	
	public void limpaTela()
	{
		this.txtFabricacao.setText("");
		this.txtRegistro.setText("");
		this.txtCodigo.setText("");
		this.txtDescricao.setText("");
	}
	
	@FXML
	void onActionBtnLimparPesquisa(ActionEvent event) {
		this.limpaTelaPesquisa();
	}

	public void limpaTelaPesquisa()
	{
		this.txtFabricacaoPesquisa.setText("");
		this.tableViewMenuCacamba.getItems().clear();
	}
	
	@FXML
	void onActionBtnEditar(ActionEvent event) throws SQLException {
		if(tableViewMenuCacamba.getSelectionModel().getSelectedItem() != null){
			Object Cacamba = new Cacamba();
			CacambaDAO CacambaDAO = new CacambaDAO();
			Cacamba = CacambaDAO.localizarPorRegistro(tableViewMenuCacamba.getSelectionModel().getSelectedItem().getRegistro());
			txtCodigo.setText(Integer.toString(((Cacamba) Cacamba).getId_cacamba()));
			txtFabricacao.setText(((Cacamba) Cacamba).getFabricacao());
			txtRegistro.setText(((Cacamba) Cacamba).getRegistro());
			txtDescricao.setText(((Cacamba) Cacamba).getDescricao());
			tabPane.getSelectionModel().select(tabPaneCadastro);
			limpaTelaPesquisa();
		}
	}
}
