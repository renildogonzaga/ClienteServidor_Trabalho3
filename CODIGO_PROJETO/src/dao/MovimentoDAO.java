package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Cacamba;
import model.Movimento;

public class MovimentoDAO {
	
	public int incluir(Movimento m) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		conn = conexao.abreConexaoBD();
		String sql = "";
		int retorno = 0;
		try {
			st = (Statement) conn.createStatement();
				// incluir
				sql = "insert into movimento (localizacao,cliente,caminhao,cacamba) values (?,?,?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, m.getLocalizacao());
				ps.setString(2, m.getCliente());
				ps.setString(3, m.getCaminhao());
				ps.setString(4, m.getCacamba());
				ps.executeUpdate();
				retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
	
	public ArrayList<Movimento> consultarMovimentos(String parametro, String tipo) {

		ArrayList<Movimento> Movimentos = new ArrayList<Movimento>();
		Movimento m;
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "";
		conn = conexao.abreConexaoBD();
		
		if (parametro == null) {
			sql = "Select * from Movimento order by " + tipo + "";
		} else {
			sql = "select * from Movimento where " + tipo + " like '" + parametro + "%'";
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				m = new Movimento();
				m.setId_movimento(rs.getInt("id_movimento"));
				m.setLocalizacao(rs.getString("localizacao"));
				m.setCliente(rs.getString("cliente"));
				m.setCaminhao(rs.getString("caminhao"));
				m.setCacamba(rs.getString("cacamba"));
				Movimentos.add(m);
			}
		} catch (SQLException e) {
			Movimentos = null;
		}
		return Movimentos;
	}
	
	public Object localizarPorId(int id){
		Movimento m = new Movimento();
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		String sql = "SELECT Id_movimento, localizacao, cliente, caminhao, cacamba FROM movimento where id_movimento =" + id;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				m.setId_movimento(rs.getInt(1));
				m.setLocalizacao(rs.getString(2));
				m.setCliente(rs.getString(3));
				m.setCaminhao(rs.getString(4));
				m.setCacamba(rs.getString(5));
			}
		} catch (SQLException e) {

		}
		return m;
	}
	
	public int excluir(int id) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "";
		int retorno = 0;
		try {
			// excluir
			sql = "delete from movimento where Id_movimento=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
	
	public int alterar(Movimento m) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "";
		int retorno = 0;
		try {
			// alterar
			sql = "update movimento set localizacao=?, cliente=?, caminhao=?, cacamba=? where Id_movimento=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, m.getLocalizacao());
			ps.setString(2, m.getCliente());
			ps.setString(3, m.getCaminhao());
			ps.setString(4, m.getCacamba());
			ps.setInt(5, m.getId_movimento());
			ps.executeUpdate();
			retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
}
