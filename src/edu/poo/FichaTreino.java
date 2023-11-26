package edu.poo;

import java.time.LocalDate;

public class FichaTreino {

	private int id;
	private LocalDate dataTreino = LocalDate.now();
	private Professor professor;
	private String nomeProfessor = "";
	private Aluno aluno;
	private String nomeAluno = "";
	private String tipoTreino = "";
	private String infoAdicional = "";
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDataTreino() {
		return dataTreino;
	}
	
	public void setDataTreino(LocalDate dataTreino) {
		this.dataTreino = dataTreino;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
		this.nomeProfessor = professor.getNome();
	}
	
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
		this.nomeAluno = aluno.getNome();
	}
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	
	public String getTipoTreino() {
		return tipoTreino;
	}
	
	public void setTipoTreino(String tipoTreino) {
		this.tipoTreino = tipoTreino;
	}
	
	public String getInfoAdicional() {
		return infoAdicional;
	}
	
	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Ficha de Treino");
		buffer.append("\nId: " + id);
		buffer.append("\nProfessor: " + nomeProfessor);
		buffer.append("\nAluno: " + nomeAluno);
		buffer.append("\nData Treino: " + dataTreino);
		buffer.append("\nTipo de Treino: " + tipoTreino);
		buffer.append("\nInformações Adicionais: " + infoAdicional);
		return buffer.toString();
	}
	
}
