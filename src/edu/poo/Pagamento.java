package edu.poo;

import java.time.LocalDate;

public class Pagamento {

	private int id;
	private Aluno aluno;
	private String nomeAluno = "";
	private LocalDate dataPagamento = LocalDate.now();
	private double valorPagamento = 0d;
	private String tipoPagamento = "";
	private String infoAdicional = "";
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public double getValorPagamento() {
		return valorPagamento;
	}
	
	public void setValorPagamento(double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
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
		buffer.append("Pagamento");
		buffer.append("\nId: " + id);
		buffer.append("\nAluno: " + nomeAluno);
		buffer.append("\nData Pagamento: " + dataPagamento);
		buffer.append("\nValor: R$ " + valorPagamento);
		buffer.append("\nForma de Pagamento: " + tipoPagamento);
		buffer.append("\nInformações Adicionais: " + infoAdicional);
		return buffer.toString();
	}
	
}
