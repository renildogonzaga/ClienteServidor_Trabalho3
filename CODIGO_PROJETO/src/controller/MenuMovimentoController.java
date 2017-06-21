package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.PessoaDAO;
import dao.CacambaDAO;
import dao.CaminhaoDAO;
import dao.MovimentoDAO;
import dao.Conexao;
import model.Pessoa;
import model.Caminhao;
import model.Cacamba;
import model.Movimento;
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

public class MenuMovimentoController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabPaneCadastro;

	@FXML
	private Tab tabPanePesquisa;

	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtLocalizacao;
	
	@FXML
	private TextField txtNomePesquisa;
	
	@FXML
	private Button btnIncluir;

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
    private TableView<Movimento> tableViewMenuMovimento;

    @FXML
    private TableColumn<Movimento,String> colunaCliente;

    @FXML
    private TableColumn<Movimento,String> colunaCaminhao;
    
    @FXML
    private TableColumn<Movimento,String> colunaCacamba;
    
    @FXML
    private TableColumn<Movimento,String> colunaLocalizacao;
    
    @FXML
    private TableColumn<Movimento,String> colunaId;
    
    @FXML
    private ComboBox<String> comboCliente;
    
    @FXML
    private ComboBox<String> comboCacamba;
    
    @FXML
    private ComboBox<String> comboCaminhao;
    
    @FXML
    private ComboBox<String> comboTipoConsulta;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
			limparPreencherComboBoxCliente();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de clientes.");
		}
		
		try{
			limparPreencherComboBoxCaminhao();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de caminhões.");
		}
		
		try{
			limparPreencherComboBoxCacamba();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de caçambas.");
		}
		
		try{
			PreencherComboBoxTipoConsulta();
		}
		catch (SQLException e1) {

		}
	}
	
	public void limparPreencherComboBoxCliente() throws SQLException
	{
		this.comboCliente.getItems().clear();
		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas = pDAO.getPessoas();
		ObservableList<String> obl = FXCollections.observableArrayList();
		 
		for(int i=0;i<pessoas.size(); i++)
		{
			String s = pessoas.get(i).getNome();
		    obl.add(s);
		}
		this.comboCliente.setItems(obl);
	}
	
	public void PreencherComboBoxTipoConsulta() throws SQLException
	{
		this.comboTipoConsulta.getItems().clear();
		ObservableList<String> obl = FXCollections.observableArrayList();
		obl.add("Cliente");
		obl.add("Caminhão");
		obl.add("Caçamba");
		this.comboTipoConsulta.setItems(obl);	
	}
	
	public void limparPreencherComboBoxCaminhao() throws SQLException{
		this.comboCaminhao.getItems().clear();
		Caminhao c = new Caminhao();
		CaminhaoDAO cDAO = new CaminhaoDAO();
		
		ArrayList<Caminhao> caminhoes = new ArrayList<Caminhao>();
		caminhoes = cDAO.getCaminhoes();
		ObservableList<String> obl = FXCollections.observableArrayList();
		 
		for(int i=0;i<caminhoes.size(); i++)
		{
			String s = caminhoes.get(i).getModelo();
		    obl.add(s);
		}
		this.comboCaminhao.setItems(obl);
	}
	
	public void limparPreencherComboBoxCacamba() throws SQLException{
		this.comboCacamba.getItems().clear();
		Cacamba ca = new Cacamba();
		CacambaDAO caDAO = new CacambaDAO();
		
		ArrayList<Cacamba> cacambas = new ArrayList<Cacamba>();
		cacambas = caDAO.getCacambas();
		ObservableList<String> obl = FXCollections.observableArrayList();
		 
		for(int i=0;i<cacambas.size(); i++)
		{
			String s = cacambas.get(i).getDescricao();
		    obl.add(s);
		}
		this.comboCacamba.setItems(obl);
	}
	
	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException {
		
		Movimento m = new Movimento();
		MovimentoDAO mDAO = new MovimentoDAO();
		Util u = new Util();
		
		m.setLocalizacao(this.txtLocalizacao.getText());
		m.setCliente(this.comboCliente.getSelectionModel().getSelectedItem());
		m.setCaminhao(this.comboCaminhao.getSelectionModel().getSelectedItem());
		m.setCacamba(this.comboCacamba.getSelectionModel().getSelectedItem());
		mDAO.incluir(m);
		
		u.mensagemInformacao("Cadastro Realizado com Sucesso.");
		this.limpaTela();
	}
	
	@FXML
	void onActionBtnExcluir(ActionEvent event) throws SQLException {
		
		Movimento m = new Movimento();
		MovimentoDAO mDAO = new MovimentoDAO();
		Util u = new Util();
		
		m.setId_movimento(Integer.parseInt(this.txtCodigo.getText()));
		
		//	Verifica se algum campo está em branco
		if(Integer.toString((m.getId_movimento())).trim().equals("")){
			//u.mensagemErro("Preencha o código da Caçamba!");	
		}
		else
		{
			mDAO.excluir(m.getId_movimento());
			u.mensagemInformacao("Exclusão Realizada com Sucesso.");
			this.limpaTela();
		}
		
	}
	
	@FXML
	void onActionBtnAlterar(ActionEvent event) throws SQLException {
		
		Movimento m = new Movimento();
		MovimentoDAO mDAO = new MovimentoDAO();
		Util u = new Util();
		m.setId_movimento(Integer.parseInt(this.txtCodigo.getText()));
		m.setLocalizacao(this.txtLocalizacao.getText());
		m.setCliente(this.comboCliente.getSelectionModel().getSelectedItem());
		m.setCaminhao(this.comboCaminhao.getSelectionModel().getSelectedItem());
		m.setCacamba(this.comboCacamba.getSelectionModel().getSelectedItem());
		mDAO.alterar(m);
		u.mensagemInformacao("Alteração Realizada com Sucesso.");
		this.limpaTela();
	
	}
	
	@FXML
	void onActionBtnPesquisar(ActionEvent event) throws SQLException {

		String s = "";
		s = this.comboTipoConsulta.getSelectionModel().getSelectedItem();

		String pesquisaMovimento = txtNomePesquisa.getText();
		ArrayList<Movimento> mVazia = new ArrayList<Movimento>();
		ObservableList obl = FXCollections.observableArrayList(mVazia);
		this.tableViewMenuMovimento.setItems(obl);
		Movimento m = new Movimento();
		MovimentoDAO mDAO = new MovimentoDAO();
		ArrayList<Movimento> getMovimentos = new ArrayList<Movimento>();
		String tipo = "";
		switch (s) {
		case "Cliente":
			tipo = "cliente";
			break;
			
		case "Caminhão":
			tipo = "caminhao";
			break;

		case "Caçamba":
			tipo = "cacamba";
			break;

		default:
			break;
		}
		
		getMovimentos = mDAO.consultarMovimentos(pesquisaMovimento,tipo);
		obl = FXCollections.observableArrayList(getMovimentos);
		this.tableViewMenuMovimento.setItems(obl);
		this.colunaLocalizacao.setCellValueFactory(new PropertyValueFactory<Movimento, String>("localizacao"));
		this.colunaCliente.setCellValueFactory(new PropertyValueFactory<Movimento, String>("Cliente"));
		this.colunaCaminhao.setCellValueFactory(new PropertyValueFactory<Movimento, String>("caminhao"));
		this.colunaCacamba.setCellValueFactory(new PropertyValueFactory<Movimento, String>("cacamba"));
		this.colunaId.setCellValueFactory(new PropertyValueFactory<Movimento, String>("Id_movimento"));
	}
	
	@FXML
	void onActionBtnEditar(ActionEvent event) throws SQLException {
		if(tableViewMenuMovimento.getSelectionModel().getSelectedItem() != null){
			Object Movimento = new Movimento();
			MovimentoDAO mDAO = new MovimentoDAO();
			Movimento = mDAO.localizarPorId(tableViewMenuMovimento.getSelectionModel().getSelectedItem().getId_movimento());
			txtCodigo.setText(Integer.toString(((Movimento) Movimento).getId_movimento()));
			txtLocalizacao.setText(((Movimento) Movimento).getLocalizacao());
			this.comboCliente.setValue(((Movimento) Movimento).getCliente());
			this.comboCaminhao.setValue(((Movimento) Movimento).getCaminhao());
			this.comboCacamba.setValue(((Movimento) Movimento).getCacamba());
			tabPane.getSelectionModel().select(tabPaneCadastro);
			limpaTelaPesquisa();
		}
	}
	
	@FXML
	void onActionBtnLimpar(ActionEvent event) {
		this.limpaTela();
	}
	
	public void limpaTela()
	{
		try{
			limparPreencherComboBoxCliente();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de clientes.");
		}
		
		try{
			limparPreencherComboBoxCaminhao();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de caminhões.");
		}
		
		try{
			limparPreencherComboBoxCacamba();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de caçambas.");
		}
		
		this.txtLocalizacao.setText("");
	}
	
	@FXML
	void onActionBtnLimparPesquisa(ActionEvent event) {
		this.limpaTelaPesquisa();
	}
	
	public void limpaTelaPesquisa()
	{
		this.tableViewMenuMovimento.getItems().clear();
		this.txtNomePesquisa.clear();
		
		try{
			PreencherComboBoxTipoConsulta();
		}
		catch (SQLException e1) {

		}
	}
}