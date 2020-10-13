package src.com.app.model;

public class Dependente {
	private int idFuncionario;
	private int idDependente;
	private String nome;
	private int idade;

	public int getIdFuncionario() {
		return idFuncionario;
	}
	
	public int getIdDependente() {
		return idDependente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdFuncionario(int id) {
		idFuncionario = id;
	}
	
	public void setIdDependente(int id) {
		idDependente = id;
	}
	
	public void setNome(String n) {
		nome = n;
	}
	
	public void setIdade(int i) {
		idade = i;
	}
}
