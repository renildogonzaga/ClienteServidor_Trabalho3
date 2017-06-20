package view;

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
	}
	
	public void limparPreencherComboBoxCliente() throws SQLException
	{
		this.comboCliente.getItems().clear();
		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		
		ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>();
		pessoa = pDAO.consultarPessoas(null);
		ObservableList<String> obl = FXCollections.observableArrayList();
		 
		for(int i=0;i<pessoa.size(); i++)
		{
			String s = pessoa.get(i).getNome();
		    obl.add(s);
		}
		this.comboCliente.setItems(obl);
	}
	
	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException {
		
	}
	
	@FXML
	void onActionBtnExcluir(ActionEvent event) throws SQLException {
		
	}
	
	@FXML
	void onActionBtnAlterar(ActionEvent event) throws SQLException {
		
	}
	
	@FXML
	void onActionBtnPesquisar(ActionEvent event) throws SQLException {
		
	}
	
	@FXML
	void onActionBtnLimpar(ActionEvent event) {
		this.limpaTela();
	}
	
	public void limpaTela()
	{
		this.comboCliente.getItems().clear();
		this.comboCaminhao.getItems().clear();
		this.comboCacamba.getItems().clear();
		this.txtLocalizacao.setText("");
	}
	
	@FXML
	void onActionBtnLimparPesquisa(ActionEvent event) {
		this.limpaTelaPesquisa();
	}
	
	public void limpaTelaPesquisa()
	{
		this.comboTipoConsulta.getItems().clear();
		this.tableViewMenuMovimento.getItems().clear();
	}
	
	@FXML
	void onActionBtnEditar(ActionEvent event) throws SQLException {
		
	}
}