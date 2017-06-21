package controller;

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
	private MenuItem menuMovimento;

	@FXML
	private MenuItem menuUsuario;
	
	@FXML
	private MenuItem menuSair;

	
	@FXML
	void onActionMenuCliente(ActionEvent event) {
		try {
			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/view/MenuCliente.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Menu | Clientes");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}
	}
	
	@FXML
	void onActionMenuCaminhao(ActionEvent event) {
		try {
			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/view/MenuCaminhao.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Menu | Caminhões");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}
	}
	
	@FXML
	void onActionMenuCacamba(ActionEvent event) {
		try {
			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/view/MenuCacamba.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Menu | Caçambas");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}
	}
	
	@FXML
	void onActionMenuMovimento(ActionEvent event) {
		try {
			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/view/MenuMovimento.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Menu | Movimentação");
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
			stagex.setTitle("Menu | Usuários");
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
