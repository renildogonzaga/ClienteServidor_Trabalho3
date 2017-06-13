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
import dao.ProfissaoDAO;
import dao.Conexao;
import model.Pessoa;
import model.Profissao;
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

public class MenuPessoaController implements Initializable {

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
	private TextField txtDataNasc;

	@FXML
	private TextField txtProfissao;
	
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
    private TableView<Pessoa> tableViewMenuPessoa;

    @FXML
    private TableColumn<Pessoa,String> colunaNome;

    @FXML
    private TableColumn<Pessoa,String> colunaCpf;
    
    @FXML
    private ComboBox<String> comboProfissao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
			limparPreencherComboBoxProfissao();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de profissões.");
		}
	}

	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException {

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		Util u = new Util();
		
		p.setNome(this.txtNome.getText());
		p.setCpf(this.txtCpf.getText());
		p.setDatanasc(this.txtDataNasc.getText());
		p.setId_profis(Integer.parseInt(this.txtProfissao.getText()));
		
		//	Verifica se algum campo está em branco
		if(p.getNome().trim().equals("") || p.getCpf().trim().equals("") || p.getDatanasc().trim().equals("") || Integer.toString((p.getId_profis())).trim().equals("")){
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
		
		p.setId_pessoa(Integer.parseInt(this.txtCodigo.getText()));
		p.setNome(this.txtNome.getText());
		p.setCpf(this.txtCpf.getText());
		p.setDatanasc(this.txtDataNasc.getText());
		p.setId_profis(Integer.parseInt(this.txtProfissao.getText()));
		
		//	Verifica se algum campo está em branco
		if(p.getNome().trim().equals("") || p.getCpf().trim().equals("") || p.getDatanasc().trim().equals("") || Integer.toString((p.getId_profis())).trim().equals("") || Integer.toString((p.getId_pessoa())).trim().equals("")){
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
		
		p.setId_pessoa(Integer.parseInt(this.txtCodigo.getText()));
		
		//	Verifica se algum campo está em branco
		if(Integer.toString((p.getId_pessoa())).trim().equals("")){
			u.mensagemErro("Preencha o código da pessoa!");	
		}
		else
		{
			pDAO.excluir(p.getId_pessoa());
			u.mensagemInformacao("Exclusão Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnPesquisar(ActionEvent event) throws SQLException {

		//String pesquisaCpf = txtCpfPesquisa.getText();
		String pesquisaNome = txtNomePesquisa.getText();

		if (!pesquisaNome.trim().equals("")) {
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
		
		/*else {
			if (!pesquisaCpf.trim().equals("")) {
				Pessoa p = new Pessoa();
				PessoaDAO pDAO = new PessoaDAO();
				Util u = new Util();
				p.setCpf(this.txtCpfPesquisa.getText());
				// Verifica se algum campo está em branco
				if (p.getCpf().trim().equals("")) {
					u.mensagemErro("Preencha o CPF da pessoa!");
				} else {
					Pessoa p2 = new Pessoa();
					p2 = (Pessoa) pDAO.localizarPorCpf(p.getCpf());
					if (p2.getNome().trim().equals("") || p2.getCpf().trim().equals("")) {
						u.mensagemErro("Pessoa não localizada!");
						this.limpaTela();
					} else {
						String profissao = "";
						profissao = pDAO.retornaProfissao(p2.getId_profis());
						u.mensagemInformacao("Nome: " + p2.getNome() + "\nCPF: " + p2.getCpf() + "\nData Nascimento: "
								+ p2.getDatanasc() + "\nProfissão: " + profissao);
						this.limpaTela();
					}
				}
			}
		}*/
	}
	

	 @FXML
	 void onActionComboProfissao(ActionEvent event) throws SQLException 
	 {
		 
		 String sigla = this.comboProfissao.getSelectionModel().getSelectedItem();
		 Profissao pr = new Profissao();
		 ProfissaoDAO prDAO = new ProfissaoDAO();

		 pr.setId(prDAO.getIdProfissao(sigla));
		 if (pr.getId() != 0)
		 {
			 this.txtProfissao.setText(Integer.toString(pr.getId()));
			 //selectionModel.select(0); //select by object
		 }
	 }
	 
		public void limparPreencherComboBoxProfissao() throws SQLException
		{
			this.comboProfissao.getItems().clear();
			Profissao pr = new Profissao();
			ProfissaoDAO prDAO = new ProfissaoDAO();
			
			ArrayList<Profissao> profis = new ArrayList<Profissao>();
			profis = prDAO.getProfissoes();
			ObservableList<String> obl = FXCollections.observableArrayList();
			 
			for(int i=0;i<profis.size(); i++)
			{
				String s = profis.get(i).getDescricao();
			    obl.add(s);
			}
			this.comboProfissao.setItems(obl);
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
		this.txtDataNasc.setText("");
		this.txtProfissao.setText("");
		
		try{
			limparPreencherComboBoxProfissao();
		}
		catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar o combo de profissões.");
		}
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
}
