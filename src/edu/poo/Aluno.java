package edu.poo;

import java.time.LocalDate;

public class Aluno {

	private int id;
	private String cpf = "";
	private String nome = "";
	private LocalDate nascimento = LocalDate.now();
	private String endereco = "";
	private String telefone = "";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", nascimento=" + nascimento + ", endereco="
				+ endereco + ", telefone=" + telefone + "]";
	}
	
	
	
}
