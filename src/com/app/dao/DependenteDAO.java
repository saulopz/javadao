package src.com.app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import src.com.app.database.*;
import src.com.app.model.*;

public class DependenteDAO {
	private int Position;
	private Vector<Dependente> dependenteList;

	public DependenteDAO() {
		Position = 0;
		dependenteList = new Vector<Dependente>();
		create();
	}

	private void create() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			String query =
				"create table if not exists dependente ( " +
				"idfuncionario integer references funcionario, " +
				"iddependente serial, " +
				"nome varchar(50), " +
				"idade integer, " +
				"primary key (iddependente));";
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void load(int func) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();

			String query = "select * from dependente where idFuncionario = " + func;
			System.out.println("QUERY: " + query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Dependente dep = new Dependente();
				dep.setIdFuncionario(func);
				dep.setIdDependente(rs.getInt("iddependente"));
				dep.setNome(rs.getString("nome"));
				dep.setIdade(rs.getInt("idade"));
				dependenteList.add(dep);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Dependente dep) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();

			String query = "insert into dependente (idfuncionario, nome, idade) " + "values (" + dep.getIdFuncionario()
					+ ", '" + dep.getNome() + "', " + dep.getIdade() + ")";
			System.out.println("QUERY: " + query);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.clear();
	}

	public void update(Dependente dep) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();

			String query = "update dependente set " + "nome = '" + dep.getNome() + "', " + "idade = " + dep.getIdade()
					+ " " + "where idfuncionario = " + dep.getIdFuncionario() + " " + "and iddependente = "
					+ dep.getIdDependente();
			System.out.println("QUERY: " + query);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.clear();
	}

	public void delete(int dep) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			String query = "delete from dependente where idDependente = " + dep;
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
		dependenteList.clear();
	}

	public void reset() {
		Position = 0;
	}

	public int size() {
		return dependenteList.size();
	}

	public Dependente next() {
		Dependente dep = null;
		if (Position < dependenteList.size()) {
			dep = dependenteList.get(Position);
			Position++;
		}
		return dep;
	}
}
