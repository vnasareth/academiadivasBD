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



public class BoundaryProfessor extends Application {

	private TextField txtNome = new TextField();
	private TextField txtCPF = new TextField();
	private TextField txtEndereco = new TextField();
	private TextField txtSalario = new TextField();
	private TextField txtDataNascimento = new TextField();
	private TextField txtTelefone = new TextField();

	private Button botaoAdicionar = new Button("Adicionar");
	private Button botaoPesquisar = new Button("Pesquisar");

	private ControlProfessor control = new ControlProfessor();

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Gestão dos Professores - Academia Divas ");

		BorderPane borderPane = new BorderPane();
		GridPane gridPane = new GridPane();
		GridPane pane = new GridPane();
		borderPane.setTop(gridPane);
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		gridPane.add(new Label("Nome"), 0, 0);
		gridPane.add(txtNome, 1, 0);
		gridPane.add(new Label("CPF"), 0, 1);
		gridPane.add(txtCPF, 1, 1);
		gridPane.add(new Label("Endereço"), 0, 2);
		gridPane.add(txtEndereco, 1, 2);
		gridPane.add(new Label("Salario"), 0, 3);
		gridPane.add(txtSalario, 1, 3);
		gridPane.add(new Label("Data de Nascimento"), 0, 4);
		gridPane.add(txtDataNascimento, 1, 4);
		gridPane.add(new Label("Telefone"), 0, 5);
		gridPane.add(txtTelefone, 1, 5);

		gridPane.add(botaoAdicionar, 0, 6);
		gridPane.add(botaoPesquisar, 1, 6);

		String estiloTextField = "-fx-background-color: lightgray; " + "-fx-border-color: gray;"
				+ "-fx-border-width: 0.7px;";

		txtNome.setStyle(estiloTextField);
		txtCPF.setStyle(estiloTextField);
		txtEndereco.setStyle(estiloTextField);
		txtSalario.setStyle(estiloTextField);
		txtTelefone.setStyle(estiloTextField);
		txtDataNascimento.setStyle(estiloTextField);

		botaoAdicionar.setStyle("-fx-background-color: lightgreen;");
		botaoPesquisar.setStyle("-fx-background-color: lightyellow;");
		pane.setStyle("-fx-background-color: darkgray");

		gridPane.setVgap(10);
		gridPane.setHgap(20);

		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtCPF.textProperty(), control.cpfProperty());
		Bindings.bindBidirectional(txtEndereco.textProperty(), control.enderecoProperty());
		StringConverter<Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtSalario.textProperty(), control.SalarioProperty(), converterNumber);
		Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
		StringConverter<LocalDate> converterDate = new LocalDateStringConverter();
		Bindings.bindBidirectional(txtDataNascimento.textProperty(), control.nascimentoProperty(), converterDate);

		botaoAdicionar.setOnAction((e) -> {
			control.adicionar();
			
			String mensagem = "Professora, " + control.nomeProperty().get() + ", adicionada com sucesso!";
            new Alert(Alert.AlertType.INFORMATION, mensagem).show();
			
			txtNome.setText("");
			txtCPF.setText("");
			txtEndereco.setText("");
			txtSalario.setText("");
			txtDataNascimento.setText("");
			txtTelefone.setText("");
		});

		botaoPesquisar.setOnAction((e) -> {
			control.pesquisar();
		});

		borderPane.setCenter(control.getTable());
		borderPane.setPadding(new Insets(10, 10, 10, 10));
		Scene scene = new Scene(borderPane, 600, 600);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(BoundaryProfessor.class, args);

	}

}
