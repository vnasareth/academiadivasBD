package edu.poo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControlProfessor {

	private IAcademiaDAO<Professor> dao = new ProfessoresDAOImpl();

	private ObservableList<Professor> professoresLista = FXCollections.observableArrayList();
	private TableView<Professor> table = new TableView<>();

	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty endereco = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private DoubleProperty salario = new SimpleDoubleProperty();
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

	public DoubleProperty SalarioProperty() {
		return salario;
	}

	public ObjectProperty<LocalDate> nascimentoProperty() {
		return nascimento;
	}

	@SuppressWarnings("unchecked")
	public ControlProfessor() {

		TableColumn<Professor, String> colunaNome = new TableColumn<>("Nome");
		TableColumn<Professor, String> colunaCpf = new TableColumn<>("CPF");
		TableColumn<Professor, String> colunaEndereco = new TableColumn<>("Endereço");
		TableColumn<Professor, String> colunaNascimento = new TableColumn<>("Data de Nascimento");
		TableColumn<Professor, String> colunaTelefone = new TableColumn<>("Telefone");
		TableColumn<Professor, String> colunaSalario = new TableColumn<>("Salario");

		colunaNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<Professor, String>("cpf"));
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Professor, String>("telefone"));
		colunaSalario.setCellValueFactory(new PropertyValueFactory<Professor, String>("salario"));
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Professor, String>("endereco"));
		colunaNascimento.setCellValueFactory(data -> {
			LocalDate dataNasc = data.getValue().getNascimento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dataNasc.format(formatter));
		});

		table.getColumns().addAll(colunaNome, colunaCpf, colunaNascimento, colunaTelefone, colunaSalario, colunaEndereco);
		table.setItems(professoresLista);
	}

	public void adicionar() {
		Professor professor = new Professor();
		professor.setNome(nome.get());
		professor.setCpf(cpf.get());
		professor.setEndereco(endereco.get());
		professor.setTelefone(telefone.get());
		professor.setSalario(salario.get());
		professor.setNascimento(nascimento.get());

		professoresLista.add(professor);
		dao.inserir(professor);
	}

	public void pesquisar() {
		List<Professor> lista = dao.consultar(nome.get());
		professoresLista.clear();
		professoresLista.addAll(lista);
	}

	public TableView<Professor> getTable() {
		return table;
	}

}
