package src.com.app.model;

public class Funcionario {
	private int idFuncionario;
	private String nome;
	private float salario;

	public int getIdFuncionario() {
		return idFuncionario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public void setIdFuncionario(int id) {
		idFuncionario = id;
	}
	
	public void setNome(String n) {
		nome = n;
	}
	
	public void setSalario(float s) {
		salario = s;
	}
}