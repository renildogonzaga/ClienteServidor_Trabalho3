package model;

public class Movimento {
	
	int id_movimento;
	String localizacao, cliente, caminhao, cacamba;
	
	public int getId_movimento() {
		return id_movimento;
	}
	public void setId_movimento(int id_movimento) {
		this.id_movimento = id_movimento;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCaminhao() {
		return caminhao;
	}
	public void setCaminhao(String caminhao) {
		this.caminhao = caminhao;
	}
	public String getCacamba() {
		return cacamba;
	}
	public void setCacamba(String cacamba) {
		this.cacamba = cacamba;
	}
}
