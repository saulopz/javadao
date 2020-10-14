package src.com.app.main;

import java.util.Scanner;

import src.com.app.database.DatabaseConnection;
import src.com.app.model.*;
import src.com.app.dao.*;

public class Main {
	private FuncionarioDAO funcionarios;
	private DependenteDAO dependentes;
	Scanner entrada;
	
	public Main() {
		// Mysql
		//DatabaseConnection.setConfig("jdbc:mysql://localhost/dao" , "root", "", "com.mysql.jdbc.Driver");
		// Access
		// DatabaseConnection.setConfig("jdbc:odbc:dao", "", "", "sun.jdbc.odbc.JdbcOdbcDriver");
		// SQLITE
		DatabaseConnection.setConfig("jdbc:sqlite:dao.db", "", "", "org.sqlite.JDBC");
		funcionarios = new FuncionarioDAO();
		dependentes = new DependenteDAO();
		entrada = new Scanner(System.in);
	}
	
	public void showMenu() {
		System.out.println("Comandos Funcionarios:");
		System.out.println("\t lf : listar funcionario");
		System.out.println("\t if : inserir novo funcionario");
		System.out.println("\t df : deletar funcionario");
		System.out.println("\t af : alterar dados de um funcionario");
		System.out.println("Comandos Dependentes:");
		System.out.println("\t ld : listar dependentes de um funcionario");
		System.out.println("\t id : inserir dependente para um funcionario");
		System.out.println("\t dd : deletar dependente");
		System.out.println("\t ad : alterar dados de um dependente");
		System.out.println("sair : Sai do programa");
		System.out.println("");
		System.out.print("Comando: ");
	}
	
	public void menu() {
		String choice = "";
		while (!choice.contains("sair")) {
			showMenu();
			choice = entrada.nextLine();
			if      (choice.contains("lf")) listarFuncionarios();
			else if (choice.contains("if")) inserirFuncionario();
			else if (choice.contains("df")) deletarFuncionario();
			else if (choice.contains("af")) alterarFuncionario();
			else if (choice.contains("ld")) listarDependentes();
			else if (choice.contains("id")) inserirDependente();
			else if (choice.contains("dd")) deletarDependente();
			else if (choice.contains("ad")) alterarDependente();
		}
	}
	
	public void listarFuncionarios() {
		System.out.print("Filtro: ");
		funcionarios.load(entrada.nextLine());
		Funcionario f;
		while ((f = funcionarios.next()) != null) {
			System.out.println("" + f.getIdFuncionario() + " - " + f.getNome() + " - " + f.getSalario());
		}
	}
	
	public void listarDependentes() {
		System.out.print("Codigo do Funcionario : ");
		dependentes.load(Integer.valueOf(entrada.nextLine()));
		Dependente d;
		while ((d = dependentes.next()) != null) {
			System.out.println("" + d.getIdDependente() + " - " + d.getNome() + " - " + d.getIdade());
		}
	}
	
	public void deletarFuncionario() {
		System.out.print("Codigo do Funcionario a deletar : ");
		funcionarios.delete(Integer.valueOf(entrada.nextLine()));
	}
	
	public void deletarDependente() {
		System.out.print("Codigo do Dependente a deletar : ");
		dependentes.delete(Integer.valueOf(entrada.nextLine()));
	}
	
	public Funcionario lerFuncionario() {
		Funcionario f = new Funcionario();
		System.out.print("idFuncionario: ");
		f.setIdFuncionario(Integer.valueOf(entrada.nextLine()));
		System.out.print("Nome: ");
		f.setNome(entrada.nextLine());
		System.out.print("Salario: ");				
		f.setSalario(Float.valueOf(entrada.nextLine()));
		return f;
	}
	
	public void inserirFuncionario() {
		System.out.println("Inserindo novo Funcionario");
		funcionarios.insert(this.lerFuncionario());
	}
	
	public void alterarFuncionario() {
		System.out.println("Alterando Funcionario");
		funcionarios.update(this.lerFuncionario());
	}
	
	public Dependente lerDependente() {
		Dependente d = new Dependente();
		System.out.print("idFuncionario: ");
		d.setIdFuncionario(Integer.valueOf(entrada.nextLine()));
		System.out.print("Nome: ");
		d.setNome(entrada.nextLine());
		System.out.print("Idade: ");
		d.setIdade(Integer.valueOf(entrada.nextLine()));
		return d;
	}
	
	public void inserirDependente() {
		System.out.println("Inserindo novo Dependente");
		dependentes.insert(this.lerDependente());
	}
	
	public void alterarDependente() {
		System.out.println("Alterando Dependente");
		System.out.print("idDependente: ");
		int id = Integer.valueOf(entrada.nextLine());
		Dependente d = this.lerDependente();
		d.setIdDependente(id);
		dependentes.update(d);	
	}
		
	public static void main(String[] arguments) {
		Main m = new Main();
		m.menu();
	}
}
