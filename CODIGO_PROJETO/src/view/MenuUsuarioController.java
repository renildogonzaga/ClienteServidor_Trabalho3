package view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.PessoaDAO;
import model.Pessoa;

import dao.PessoaDAO;
import dao.UsuarioDAO;
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
import javafx.scene.control.TextField;

public class MenuUsuarioController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabPaneCadastro;

	@FXML
	private Tab tabPanePesquisa;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtSigla;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimpar;

	@FXML
	private ComboBox<String> comboPesquisa;

	@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

	}
}