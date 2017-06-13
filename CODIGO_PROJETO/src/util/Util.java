package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Util {
	public static void mensagemInformacao(String msg) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION, msg, ButtonType.OK);
		alert.setTitle("CRUD");
		alert.setHeaderText("Informação");
		alert.show();
	}

	public static void mensagemErro(String msg) {
		Alert alert;
		alert = new Alert(AlertType.ERROR, msg, ButtonType.OK);
		alert.setTitle("CRUD");
		alert.setHeaderText("Erro");
		alert.show();
	}

	public static boolean mensagemCustomizada(String msg) {
		boolean retorno = false;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Doutorado");
		alert.setHeaderText(msg);
		alert.setContentText("Escolha sua opção");
		ButtonType buttonTypeConfirmar = new ButtonType("Confirmar", ButtonData.OK_DONE);
		ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... escolheu "Confirmar"
			retorno = true;
		} else {
			// ... escolheu Cancelar ou closed the dialog
			retorno = false;
		}
		return retorno;
	}

	public static String mensagemComBotoesMaiorMenor() {
		String retorno = "";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Seleção do Maior ou Menor Caminho");
		alert.setHeaderText("Selecione Maior para o maior caminho e Menor para o menor Caminho");
		alert.setContentText("Escolha sua opção. O padrão é igual a Menor");

		ButtonType buttonTypeOne = new ButtonType("Menor");
		ButtonType buttonTypeTwo = new ButtonType("Maior");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			retorno = "MENOR";
			// ... user chose "One"
		} else if (result.get() == buttonTypeTwo) {
			retorno = "MAIOR";
		} else {
			retorno = "MENOR";
		}

		return retorno;
	}

	public static boolean validaConteudoNumerico(String s) {
		int a = 0;
		boolean retorno = true;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}

	public static boolean stringVaziaOuNula(String s) {
		// retorna true se a string for nula ou vazia
		boolean retorno = false;
		if (s.equals("") || s.isEmpty() || s.length() == 0)
			retorno = true;

		return retorno;
	}

}
