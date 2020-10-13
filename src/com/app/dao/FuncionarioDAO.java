package src.com.app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import src.com.app.database.*;
import src.com.app.model.*;

public class FuncionarioDAO {
	private int Position;
	private Vector<Funcionario> funcionarioList;

	public FuncionarioDAO() {
		Position = 0;
		funcionarioList = new Vector<Funcionario>();
		create();
	}

	private void create() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			String query =
				"create table if not exists funcionario ( " +
				"idfuncionario serial, " +
				"nome varchar(50), " +
				"salario real, " +
				"primary key (idfuncionario))";
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void load(String filter) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();

			String query = "select * from funcionario";
			if (filter.length() > 0) {
				query += " where " + filter;
			}
			System.out.println("QUERY: " + query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Funcionario func = new Funcionario();
				func.setIdFuncionario(rs.getInt("idfuncionario"));
				func.setNome(rs.getString("nome"));
				func.setSalario(rs.getFloat("salario"));
				funcionarioList.add(func);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Funcionario func) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();

			String query = "insert into funcionario (idfuncionario, nome, salario) " + "values ("
					+ func.getIdFuncionario() + ",'" + func.getNome() + "', " + func.getSalario() + ")";
			System.out.println("QUERY: " + query);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.clear();
	}

	public void update(Funcionario func) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();

			String query = "update funcionario set " + "nome = '" + func.getNome() + "', " + "salario = "
					+ func.getSalario() + " " + "where idfuncionario = " + func.getIdFuncionario();
			System.out.println("QUERY: " + query);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.clear();
	}

	public void delete(int func) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			String query = "delete from dependente where idFuncionario = " + func;
			System.out.println("QUERY: " + query);
			stmt.executeUpdate(query);
			query = "delete from funcionario where idFuncionario = " + func;
			System.out.println("QUERY: " + query);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.clear();
	}

	public void clear() {
		Position = 0;
		funcionarioList.clear();
	}

	public void reset() {
		Position = 0;
	}

	public int size() {
		return funcionarioList.size();
	}

	public Funcionario next() {
		Funcionario func = null;
		if (Position < funcionarioList.size()) {
			func = funcionarioList.get(Position);
			Position++;
		}
		return func;
	}
}
