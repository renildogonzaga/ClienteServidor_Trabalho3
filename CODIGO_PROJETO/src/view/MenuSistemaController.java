package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import util.Util;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuSistemaController implements Initializable {
	public Stage stagex = new Stage();

	@FXML
	private MenuItem menuCliente;

	@FXML
	private MenuItem menuCaminhao;

	@FXML
	private MenuItem menuCacamba;

	@FXML
	private MenuItem menuUsuario;
	
	@FXML
	private MenuItem menuSair;

	
	@FXML
	void onActionMenuCliente(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	void onActionMenuCaminhao(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	void onActionMenuCacamba(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	void onActionMenuPessoa(ActionEvent event) {
		try {
			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/view/MenuPessoa.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Menu | Pessoas");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}
	}

	@FXML
	void onActionMenuProfissao(ActionEvent event) {
		try {

			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/view/MenuPessoa.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Menu | Cadastro de Pessoas");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}

	}

	@FXML
	void onActionMenuUsuario(ActionEvent event) {
		try {

			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/view/MenuUsuario.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Menu | Cadastro de Pessoas");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}

	}

	@FXML
	void onActionMenuSair(ActionEvent event) {
		Platform.exit();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
