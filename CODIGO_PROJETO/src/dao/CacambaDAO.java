package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Cacamba;

public class CacambaDAO {
	
	public int incluir(Cacamba c) {

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "Select * from Cacamba where num_registro ='"+c.getRegistro()+"' ";
		int retorno = 0;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			if (!rs.next()) {
				// incluir
				sql = "insert into Cacamba (num_registro,Descricao,Fabricacao) values (?,?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, c.getRegistro());
				ps.setString(2, c.getDescricao());
				ps.setString(3, c.getFabricacao());
				ps.executeUpdate();
				retorno = 1;
			} else {
				// cadastro existente
				retorno = 3;
			}
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
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
			sql = "delete from Cacamba where Id_cacamba=?";
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
	
	public int alterar(Cacamba c) {
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
			sql = "update Cacamba set num_registro=?, Descricao=?, Fabricacao=? where Id_cacamba=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, c.getRegistro());
			ps.setString(2, c.getDescricao());
			ps.setString(3, c.getFabricacao());
			ps.setInt(4, c.getId_cacamba());
			ps.executeUpdate();
			retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
	
	public ArrayList<Cacamba> consultarCacambas(String parametro) {

		ArrayList<Cacamba> Cacambas = new ArrayList<Cacamba>();
		Cacamba c;
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "";
		conn = conexao.abreConexaoBD();
		
		if (parametro == null) {
			sql = "Select * from Cacamba order by fabricacao";
		} else {
			sql = "select * from Cacamba where fabricacao like '" + parametro + "%'";
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				c = new Cacamba();
				c.setFabricacao(rs.getString("fabricacao"));
				c.setRegistro(rs.getString("num_registro"));
				Cacambas.add(c);
			}
		} catch (SQLException e) {
			Cacambas = null;
		}
		return Cacambas;
	}
	
	public Object localizarPorRegistro(String registro){
		Cacamba c = new Cacamba();
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		String sql = "SELECT Id_cacamba, num_registro, Descricao, Fabricacao FROM Cacamba where num_registro like'" + registro + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				c.setId_cacamba(rs.getInt(1));
				c.setRegistro(rs.getString(2));
				c.setDescricao(rs.getString(3));
				c.setFabricacao(rs.getString(4));
			}
		} catch (SQLException e) {

		}
		return c;
	}
}
