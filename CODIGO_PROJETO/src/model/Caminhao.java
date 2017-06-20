package model;

public class Caminhao {
	
	int id_caminhao;
	String fabricante, modelo, registro, ano;
	
	public int getId_caminhao() {
		return id_caminhao;
	}
	public void setId_caminhao(int id_caminhao) {
		this.id_caminhao = id_caminhao;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
}
