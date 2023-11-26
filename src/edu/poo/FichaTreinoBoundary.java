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


public class FichaTreinoBoundary extends Application {

	private TextField alunoTxt = new TextField();
	private TextField professorTxt = new TextField();
	private TextField dataTreinoTxt = new TextField();
	private TextField tipoTreinoTxt = new TextField();
	private TextField infoTxt = new TextField();

	private Button adicionarBt = new Button("Adicionar");
	private Button pesquisarBt = new Button("Pesquisar");
	private Button pesquisarAlunoBt = new Button("Pesquisar Aluno");
	private Button pesquisarProfessorBt = new Button("Pesquisar Professor");

	private FichaTreinoControl control = new FichaTreinoControl();

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Gestão Treinos - Academia Divas");

		BorderPane borderPane = new BorderPane();
		GridPane gridPane = new GridPane();
		borderPane.setTop(gridPane);

		gridPane.add(new Label("Professor"), 0, 0);
		gridPane.add(professorTxt, 1, 0);
		professorTxt.setPrefWidth(300d);
		Bindings.bindBidirectional(professorTxt.textProperty(), control.nomeProfessorProperty());
		
		gridPane.add(pesquisarProfessorBt, 2, 0);
		pesquisarProfessorBt.setOnAction((e) -> {
			control.pesquisarProfessor();
		});
		
		gridPane.add(new Label("Aluno"), 0, 1);
		gridPane.add(alunoTxt, 1, 1);
		alunoTxt.setPrefWidth(300d);
		Bindings.bindBidirectional(alunoTxt.textProperty(), control.nomeAlunoProperty());
		
		gridPane.add(pesquisarAlunoBt, 2, 1);
		pesquisarAlunoBt.setOnAction((e) -> {
			control.pesquisarAluno();
		});
		
		gridPane.add(new Label("Data Treino"), 0, 2);
		gridPane.add(dataTreinoTxt, 1, 2);
		dataTreinoTxt.setPrefWidth(300d);
		StringConverter<LocalDate> converterDate = new LocalDateStringConverter();
		Bindings.bindBidirectional(dataTreinoTxt.textProperty(), control.dataTreinoProperty(), converterDate);
		
		gridPane.add(new Label("Tipo de Treino"), 0, 3);
		gridPane.add(tipoTreinoTxt, 1, 3);
		tipoTreinoTxt.setPrefWidth(300d);
		Bindings.bindBidirectional(tipoTreinoTxt.textProperty(), control.tipoTreinoProperty());
		
		gridPane.add(new Label("Informações Adicionais"), 0, 4);
		gridPane.add(infoTxt, 1, 4);
		infoTxt.setPrefHeight(50d);
		infoTxt.setPrefWidth(300d);
		Bindings.bindBidirectional(infoTxt.textProperty(), control.infoAdicionalProperty());
		
		gridPane.add(adicionarBt, 0, 6);
		adicionarBt.setOnAction((e) -> {
			control.adicionar();
			StringBuffer buffer = new StringBuffer();
			buffer.append("Treino da aluna, ");
			buffer.append(control.nomeAlunoProperty().get());
			buffer.append(", adicionado com sucesso!");
            new Alert(Alert.AlertType.INFORMATION, buffer.toString()).show();
			professorTxt.setText("");
			alunoTxt.setText("");
			dataTreinoTxt.setText("");
			tipoTreinoTxt.setText("");
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
		Application.launch(FichaTreinoBoundary.class, args);
	}

}
