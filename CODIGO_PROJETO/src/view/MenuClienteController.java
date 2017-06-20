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
import dao.Conexao;
import model.Pessoa;
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

public class MenuClienteController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabPaneCadastro;

	@FXML
	private Tab tabPanePesquisa;

	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtNomePesquisa;

	@FXML
	private TextField txtCpfPesquisa;
	
	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtEndereco;
	
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
    private TableView<Pessoa> tableViewMenuPessoa;

    @FXML
    private TableColumn<Pessoa,String> colunaNome;

    @FXML
    private TableColumn<Pessoa,String> colunaCpf;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException {

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		Util u = new Util();
		
		p.setNome(this.txtNome.getText());
		p.setCpf(this.txtCpf.getText());
		p.setTelefone(this.txtTelefone.getText());
		p.setEndereco(this.txtEndereco.getText());
		
		//	Verifica se algum campo está em branco
		if(p.getNome().trim().equals("") || p.getCpf().trim().equals("") || p.getTelefone().trim().equals("") || p.getEndereco().trim().equals("")){
			u.mensagemErro("Preencha todos os campos!");	
		}
		else
		{
			pDAO.incluir(p);
			u.mensagemInformacao("Cadastro Realizado com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnAlterar(ActionEvent event) throws SQLException {

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		Util u = new Util();
		
		p.setId_cliente(Integer.parseInt(this.txtCodigo.getText()));
		p.setNome(this.txtNome.getText());
		p.setCpf(this.txtCpf.getText());
		p.setTelefone(this.txtTelefone.getText());
		p.setEndereco(this.txtEndereco.getText());
		
		//	Verifica se algum campo está em branco
		if(p.getNome().trim().equals("") || p.getCpf().trim().equals("") || p.getTelefone().trim().equals("") || p.getEndereco().trim().equals("") || Integer.toString((p.getId_cliente())).trim().equals("")){
			u.mensagemErro("Preencha todos os campos!");	
		}
		else
		{
			pDAO.alterar(p);
			u.mensagemInformacao("Alteração Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnExcluir(ActionEvent event) throws SQLException {

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		Util u = new Util();
		
		p.setId_cliente(Integer.parseInt(this.txtCodigo.getText()));
		
		//	Verifica se algum campo está em branco
		if(Integer.toString((p.getId_cliente())).trim().equals("")){
			u.mensagemErro("Preencha o código da pessoa!");	
		}
		else
		{
			pDAO.excluir(p.getId_cliente());
			u.mensagemInformacao("Exclusão Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnPesquisar(ActionEvent event) throws SQLException {
		// String pesquisaCpf = txtCpfPesquisa.getText();
		String pesquisaNome = txtNomePesquisa.getText();
		ArrayList<Pessoa> pVazia = new ArrayList<Pessoa>();
		ObservableList obl = FXCollections.observableArrayList(pVazia);
		this.tableViewMenuPessoa.setItems(obl);
		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		ArrayList<Pessoa> getPessoas = new ArrayList<Pessoa>();
		getPessoas = pDAO.consultarPessoas(pesquisaNome);
		obl = FXCollections.observableArrayList(getPessoas);
		this.tableViewMenuPessoa.setItems(obl);
		this.colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		this.colunaCpf.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpf"));
	}
		
	@FXML
	void onActionBtnLimpar(ActionEvent event) {
		this.limpaTela();
	}
	
	public void limpaTela()
	{
		this.txtNome.setText("");
		this.txtCpf.setText("");
		this.txtCodigo.setText("");
		this.txtTelefone.setText("");
		this.txtEndereco.setText("");
	}
	
	@FXML
	void onActionBtnLimparPesquisa(ActionEvent event) {
		this.limpaTelaPesquisa();
	}

	public void limpaTelaPesquisa()
	{
		this.txtNomePesquisa.setText("");
		this.tableViewMenuPessoa.getItems().clear();
	}
	
	@FXML
	void onActionBtnEditar(ActionEvent event) throws SQLException {
		if(tableViewMenuPessoa.getSelectionModel().getSelectedItem() != null){
			Object pessoa = new Pessoa();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoa = pessoaDAO.localizarPorCpf(tableViewMenuPessoa.getSelectionModel().getSelectedItem().getCpf());
			txtCodigo.setText(Integer.toString(((Pessoa) pessoa).getId_cliente()));
			txtNome.setText(((Pessoa) pessoa).getNome());
			txtCpf.setText(((Pessoa) pessoa).getCpf());
			txtTelefone.setText(((Pessoa) pessoa).getTelefone());
			txtEndereco.setText(((Pessoa) pessoa).getEndereco());
			tabPane.getSelectionModel().select(tabPaneCadastro);
			limpaTelaPesquisa();
		}
	}
}
