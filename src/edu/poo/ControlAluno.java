package edu.poo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class ControlAluno {
	private IAcademiaDAO<Aluno> dao = new AlunosDAOImpl();

	private ObservableList<Aluno> alunosLista = FXCollections.observableArrayList();
	private TableView<Aluno> table = new TableView<>();

	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty endereco = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>();


	public StringProperty nomeProperty() {
		return nome;
	}

	public StringProperty enderecoProperty() {
		return endereco;
	}

	public StringProperty cpfProperty() {
		return cpf;
	}

	public StringProperty telefoneProperty() {
		return telefone;
	}

	public ObjectProperty<LocalDate> nascimentoProperty() {
		return nascimento;
	}

	@SuppressWarnings("unchecked")
	public ControlAluno() {
		TableColumn<Aluno, String> colunaNome = new TableColumn<>("Nome");
		TableColumn<Aluno, String> colunaCpf = new TableColumn<>("CPF");
		TableColumn<Aluno, String> colunaEndereco = new TableColumn<>("Endereço");
		TableColumn<Aluno, String> colunaNascimento = new TableColumn<>("Data de Nascimento");
		TableColumn<Aluno, String> colunaTelefone = new TableColumn<>("Telefone");

		colunaNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<Aluno, String>("cpf"));
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Aluno, String>("telefone"));
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Aluno, String>("endereco"));
		colunaNascimento.setCellValueFactory(data -> {
			LocalDate dataNasc = data.getValue().getNascimento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dataNasc.format(formatter));
		});

		table.getColumns().addAll(colunaNome, colunaCpf, colunaNascimento, colunaTelefone, colunaEndereco);
		table.setItems(alunosLista);
	}

	public void adicionar() {
		Aluno aluno = new Aluno();
		aluno.setNome(nome.get());
		aluno.setCpf(cpf.get());
		aluno.setEndereco(endereco.get());
		aluno.setTelefone(telefone.get());
		aluno.setNascimento(nascimento.get());

		alunosLista.add(aluno);
		dao.inserir(aluno);
	}

	public void pesquisar() {
		List<Aluno> lista = dao.consultar(nome.get());
		alunosLista.clear();
		alunosLista.addAll(lista);
	}

	public TableView<Aluno> getTable() {
		return table;
	}

}
