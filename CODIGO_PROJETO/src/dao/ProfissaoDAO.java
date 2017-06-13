package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Profissao;

public class ProfissaoDAO {
	
	public ArrayList<Profissao> getProfissoes() {

		ArrayList<Profissao> pr = new ArrayList<Profissao>();
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "Select * from profissao order by descricao";
		conn = conexao.abreConexaoBD();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Profissao profis = new Profissao();
				profis.setDescricao(rs.getString("descricao"));
				pr.add(profis);
			}
		} catch (SQLException e) {
			pr = null;
		}
		return pr;
	}
	
	public int getIdProfissao(String descr) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		int id = 0;
		String sql = "SELECT id_profis FROM profissao where descricao like '"+descr+"'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			return 0;
		}
		return id;
	}
}
