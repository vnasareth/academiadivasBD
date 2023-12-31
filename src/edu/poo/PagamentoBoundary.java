package edu.poo;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;


public class PagamentoBoundary extends Application {

	private TextField alunoTxt = new TextField();
	private TextField dataPagTxt = new TextField();
	private TextField valorPagTxt = new TextField();
	private TextField tipoPagTxt = new TextField();
	private TextField infoTxt = new TextField();

	private Button adicionarBt = new Button("Adicionar");
	private Button pesquisarBt = new Button("Pesquisar");
	private Button pesquisarAlunoBt = new Button("Pesquisar Aluno");

	private PagamentoControl control = new PagamentoControl();

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Gest�o Pagamentos - Academia Divas");

		BorderPane borderPane = new BorderPane();
		GridPane gridPane = new GridPane();
		borderPane.setTop(gridPane);

		gridPane.add(new Label("Aluno"), 0, 0);
		gridPane.add(alunoTxt, 1, 0);
		alunoTxt.setPrefWidth(300d);
		Bindings.bindBidirectional(alunoTxt.textProperty(), control.nomeAlunoProperty());
		
		gridPane.add(pesquisarAlunoBt, 2, 0);
		pesquisarAlunoBt.setOnAction((e) -> {
			control.pesquisarAluno();
		});
		
		gridPane.add(new Label("Data Pagamento"), 0, 1);
		gridPane.add(dataPagTxt, 1, 1);
		dataPagTxt.setPrefWidth(300d);
		StringConverter<LocalDate> converterDate = new LocalDateStringConverter();
		Bindings.bindBidirectional(dataPagTxt.textProperty(), control.dataPagamentoProperty(), converterDate);
		
		gridPane.add(new Label("Valor Pago (R$)"), 0, 2);
		gridPane.add(valorPagTxt, 1, 2);
		valorPagTxt.setPrefWidth(300d);
		StringConverter<Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(valorPagTxt.textProperty(), control.valorPagamentoProperty(), converterNumber);
		
		gridPane.add(new Label("Forma de Pagamento"), 0, 3);
		gridPane.add(tipoPagTxt, 1, 3);
		tipoPagTxt.setPrefWidth(300d);
		Bindings.bindBidirectional(tipoPagTxt.textProperty(), control.tipoPagamentoProperty());
		
		gridPane.add(new Label("Informa��es Adicionais"), 0, 4);
		gridPane.add(infoTxt, 1, 4);
		infoTxt.setPrefHeight(50d);
		infoTxt.setPrefWidth(300d);
		Bindings.bindBidirectional(infoTxt.textProperty(), control.infoAdicionalProperty());
		
		gridPane.add(adicionarBt, 0, 6);
		adicionarBt.setOnAction((e) -> {
			control.adicionar();
			StringBuffer buffer = new StringBuffer();
			buffer.append("Pagamento da aluna, ");
			buffer.append(control.nomeAlunoProperty().get());
			buffer.append(", adicionado com sucesso!");
            new Alert(Alert.AlertType.INFORMATION, buffer.toString()).show();
			alunoTxt.setText("");
			dataPagTxt.setText("");
			valorPagTxt.setText("");
			tipoPagTxt.setText("");
			infoTxt.setText("");
		});
		
		gridPane.add(pesquisarBt, 1, 6);
		pesquisarBt.setOnAction((e) -> {
			control.pesquisar();
		});

		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(20);

		borderPane.setCenter(control.getTable());
		borderPane.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(borderPane, 800, 600);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(PagamentoBoundary.class, args);
	}

}
