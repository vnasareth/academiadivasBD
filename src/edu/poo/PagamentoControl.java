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


public class PagamentoControl {
	
	private IAcademiaDAO<Pagamento> dao = new PagamentoDAOImpl();
	private ObservableList<Pagamento> pagamentosList = FXCollections.observableArrayList();
	private TableView<Pagamento> table = new TableView<>();
	private Aluno aluno;
	
	private StringProperty nomeAluno = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataPagamento = new SimpleObjectProperty<>();
	private DoubleProperty valorPagamento = new SimpleDoubleProperty();
	private StringProperty tipoPagamento = new SimpleStringProperty("");
	private StringProperty infoAdicional = new SimpleStringProperty("");

	public StringProperty nomeAlunoProperty() {
		return nomeAluno;
	}
	
	public ObjectProperty<LocalDate> dataPagamentoProperty() {
		return dataPagamento;
	}

	public DoubleProperty valorPagamentoProperty() {
		return valorPagamento;
	}
	
	public StringProperty tipoPagamentoProperty() {
		return tipoPagamento;
	}

	public StringProperty infoAdicionalProperty() {
		return infoAdicional;
	}

	public void adicionar() {
		Pagamento pagamento = new Pagamento();
		pagamento.setAluno(aluno);
		pagamento.setDataPagamento(dataPagamento.get());
		pagamento.setValorPagamento(valorPagamento.get());
		pagamento.setTipoPagamento(tipoPagamento.get());
		pagamento.setInfoAdicional(infoAdicional.get());
		
		pagamentosList.add(pagamento);
		dao.inserir(pagamento);
	}

	public void pesquisar() {
		List<Pagamento> lista = dao.consultar(nomeAluno.get());
		pagamentosList.clear();
		pagamentosList.addAll(lista);
	}

	public void pesquisarAluno() {
		IAcademiaDAO<Aluno> dao = new AlunosDAOImpl();
		this.aluno = ((AlunosDAOImpl) dao).consultarAluno(nomeAluno.get());
		nomeAluno.set(aluno.getNome());
	}
	
	@SuppressWarnings("unchecked")
	public PagamentoControl() {
		TableColumn<Pagamento, String> alunoCol = new TableColumn<>("Aluno");
		alunoCol.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("nomeAluno"));
		
		TableColumn<Pagamento, String> dataPagCol = new TableColumn<>("Data Pagamento");
		dataPagCol.setCellValueFactory(data -> {
			LocalDate dataPag = data.getValue().getDataPagamento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dataPag.format(formatter));
		});
		
		TableColumn<Pagamento, String> valorPagCol = new TableColumn<>("Valor Pago");
		valorPagCol.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("valorPagamento"));
		
		TableColumn<Pagamento, String> tipoPagCol = new TableColumn<>("Forma de Pagamento");
		tipoPagCol.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("tipoPagamento"));
		
		TableColumn<Pagamento, String> infoCol = new TableColumn<>("Informações Adicionais");
		infoCol.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("infoAdicional"));
		
		table.getColumns().addAll(alunoCol, dataPagCol, valorPagCol, tipoPagCol, infoCol);
		table.setItems(pagamentosList);
	}

	public TableView<Pagamento> getTable() {
		return table;
	}

}
