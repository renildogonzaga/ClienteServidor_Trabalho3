package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import model.Pessoa;

public class PessoaDAO {
	
	public int incluir(Pessoa p) {

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "Select * from cliente where cpf='"+p.getCpf()+"' ";
		int retorno = 0;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			if (!rs.next()) {
				// incluir
				sql = "insert into cliente (cpf,nome,telefone,endereco) values (?,?,?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, p.getCpf());
				ps.setString(2, p.getNome());
				ps.setString(3, p.getTelefone());
				ps.setString(4, p.getEndereco());
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
	
	public int alterar(Pessoa p) {
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
			sql = "update cliente set cpf=?, nome=?, telefone=?, endereco=? where id_cliente=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, p.getCpf());
			ps.setString(2, p.getNome());
			ps.setString(3, p.getTelefone());
			ps.setString(4, p.getEndereco());
			ps.setInt(5, p.getId_cliente());
			ps.executeUpdate();
			retorno = 1;
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
			sql = "delete from cliente where id_cliente=?";
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
	
	public Object localizarPorCpf(String cpf){
		Pessoa p = new Pessoa();
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		String sql = "SELECT id_cliente, cpf, nome, telefone, endereco FROM cliente where cpf like'" + cpf + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				p.setId_cliente(rs.getInt(1));
				p.setCpf(rs.getString(2));
				p.setNome(rs.getString(3));
				p.setTelefone(rs.getString(4));
				p.setEndereco(rs.getString(5));
			}
		} catch (SQLException e) {
			p.setCpf("");
			p.setNome("");
		}
		return p;
	}
	
	public ArrayList<Pessoa> consultarPessoas(String parametro) {

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa p;

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "";
		conn = conexao.abreConexaoBD();

		if (parametro == null) {
			sql = "Select * from cliente order by nome";
		} else {
			sql = "select * from cliente where nome like '" + parametro + "%'";
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				p = new Pessoa();
				p.setNome(rs.getString("nome"));
				p.setCpf(rs.getString("cpf"));
				pessoas.add(p);
			}
		} catch (SQLException e) {
			pessoas = null;
		}
		return pessoas;
	}
}
