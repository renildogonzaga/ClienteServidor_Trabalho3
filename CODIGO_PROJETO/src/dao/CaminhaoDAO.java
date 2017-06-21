package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Caminhao;
import model.Pessoa;

public class CaminhaoDAO {
	
	public int incluir(Caminhao c) {

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "Select * from caminhao where num_registro ='"+c.getRegistro()+"' ";
		int retorno = 0;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			if (!rs.next()) {
				// incluir
				sql = "insert into caminhao (num_registro,ano,fabricante, modelo) values (?,?,?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, c.getRegistro());
				ps.setString(2, c.getAno());
				ps.setString(3, c.getFabricante());
				ps.setString(4, c.getModelo());
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
	
	
	public ArrayList<Caminhao> getCaminhoes() {
		ArrayList<Caminhao> c = new ArrayList<Caminhao>();
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "Select * from caminhao order by modelo";
		conn = conexao.abreConexaoBD();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Caminhao caminhoes = new Caminhao();
				caminhoes.setModelo(rs.getString("modelo"));
				c.add(caminhoes);
			}
		} catch (SQLException e) {
			c = null;
		}
		return c;
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
			sql = "delete from caminhao where id_caminhao=?";
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
	
	public int alterar(Caminhao c) {
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
			sql = "update caminhao set num_registro=?, ano=?, fabricante=?, modelo=? where id_caminhao=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, c.getRegistro());
			ps.setString(2, c.getAno());
			ps.setString(3, c.getFabricante());
			ps.setString(4, c.getModelo());
			ps.setInt(5, c.getId_caminhao());
			ps.executeUpdate();
			retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
	
	public ArrayList<Caminhao> consultarCaminhoes(String parametro) {

		ArrayList<Caminhao> Caminhoes = new ArrayList<Caminhao>();
		Caminhao c;
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "";
		conn = conexao.abreConexaoBD();
		
		if (parametro == null) {
			sql = "Select * from caminhao order by fabricante";
		} else {
			sql = "select * from caminhao where fabricante like '" + parametro + "%'";
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				c = new Caminhao();
				c.setRegistro(rs.getString("num_registro"));
				c.setFabricante(rs.getString("fabricante"));
				Caminhoes.add(c);
			}
		} catch (SQLException e) {
			Caminhoes = null;
		}
		return Caminhoes;
	}
	
	public Object localizarPorRegistro(String registro){
		Caminhao c = new Caminhao();
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		String sql = "SELECT id_caminhao, num_registro, ano, fabricante, modelo FROM caminhao where num_registro like'" + registro + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				c.setId_caminhao(rs.getInt(1));
				c.setRegistro(rs.getString(2));
				c.setAno(rs.getString(3));
				c.setFabricante(rs.getString(4));
				c.setModelo(rs.getString(5));
			}
		} catch (SQLException e) {

		}
		return c;
	}
}
