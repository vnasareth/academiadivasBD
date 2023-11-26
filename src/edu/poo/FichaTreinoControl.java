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


public class FichaTreinoControl {
	
	private IAcademiaDAO<FichaTreino> dao = new FichaTreinoDAOImpl();
	private ObservableList<FichaTreino> treinosList = FXCollections.observableArrayList();
	private TableView<FichaTreino> table = new TableView<>();
	private Aluno aluno;
	private Professor professor;
	
	private StringProperty nomeAluno = new SimpleStringProperty("");
	private StringProperty nomeProfessor = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataTreino = new SimpleObjectProperty<>();
	private StringProperty tipoTreino = new SimpleStringProperty("");
	private StringProperty infoAdicional = new SimpleStringProperty("");

	public StringProperty nomeAlunoProperty() {
		return nomeAluno;
	}
	
	public StringProperty nomeProfessorProperty() {
		return nomeProfessor;
	}
	
	public ObjectProperty<LocalDate> dataTreinoProperty() {
		return dataTreino;
	}

	public StringProperty tipoTreinoProperty() {
		return tipoTreino;
	}

	public StringProperty infoAdicionalProperty() {
		return infoAdicional;
	}

	public void adicionar() {
		FichaTreino treino = new FichaTreino();
		treino.setAluno(aluno);
		treino.setProfessor(professor);
		treino.setDataTreino(dataTreino.get());
		treino.setTipoTreino(tipoTreino.get());
		treino.setInfoAdicional(infoAdicional.get());
		
		treinosList.add(treino);
		dao.inserir(treino);
	}

	public void pesquisar() {
		List<FichaTreino> lista = dao.consultar(nomeAluno.get());
		treinosList.clear();
		treinosList.addAll(lista);
	}

	public void pesquisarAluno() {
		IAcademiaDAO<Aluno> dao = new AlunosDAOImpl();
		this.aluno = ((AlunosDAOImpl) dao).consultarAluno(nomeAluno.get());
		nomeAluno.set(aluno.getNome());
	}
	
	public void pesquisarProfessor() {
		IAcademiaDAO<Professor> dao = new ProfessoresDAOImpl();
		this.professor = ((ProfessoresDAOImpl) dao).consultarProfessor(nomeProfessor.get());
		nomeProfessor.set(professor.getNome());
	}
	
	@SuppressWarnings("unchecked")
	public FichaTreinoControl() {
		TableColumn<FichaTreino, String> alunoCol = new TableColumn<>("Aluno");
		alunoCol.setCellValueFactory(new PropertyValueFactory<FichaTreino, String>("nomeAluno"));

		TableColumn<FichaTreino, String> professorCol = new TableColumn<>("Professor");
		professorCol.setCellValueFactory(new PropertyValueFactory<FichaTreino, String>("nomeProfessor"));
		
		TableColumn<FichaTreino, String> dataTreinoCol = new TableColumn<>("Data Treino");
		dataTreinoCol.setCellValueFactory(data -> {
			LocalDate dataT = data.getValue().getDataTreino();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dataT.format(formatter));
		});
		
		TableColumn<FichaTreino, String> tipoTreinoCol = new TableColumn<>("Tipo de Treino");
		tipoTreinoCol.setCellValueFactory(new PropertyValueFactory<FichaTreino, String>("tipoTreino"));
		
		TableColumn<FichaTreino, String> infoCol = new TableColumn<>("Informações Adicionais");
		infoCol.setCellValueFactory(new PropertyValueFactory<FichaTreino, String>("infoAdicional"));
		
		table.getColumns().addAll(alunoCol, professorCol, dataTreinoCol, tipoTreinoCol, infoCol);
		table.setItems(treinosList);
	}

	public TableView<FichaTreino> getTable() {
		return table;
	}

}
