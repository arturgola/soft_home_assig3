package org.example.soft_home_assig3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localization extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private ResourceBundle bundle;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label emailLabel;
    @FXML
    private Button saveButton;
    private TextField firstNameInput;
    private TextField lastNameInput;
    private TextField emailInput;

    private static final String TABLE_EN = "employee_en";
    private static final String TABLE_FA = "employee_fa";
    private static final String TABLE_JA = "employee_ja";

    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> languageSelector = new ComboBox<>();
        languageSelector.getItems().addAll("English", "Farsi", "Japanese");
        languageSelector.setValue("English");

        languageSelector.setOnAction(event -> {
            String selectedLanguage = languageSelector.getValue();
            switch (selectedLanguage) {
                case "Farsi":
                    bundle = ResourceBundle.getBundle("messages", new Locale("fa", "IR"));
                    break;
                case "Japanese":
                    bundle = ResourceBundle.getBundle("messages", Locale.JAPAN);
                    break;
                default:
                    bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
            }
            updateUserInterface(primaryStage); // Update UI components
        });

        primaryStage.setTitle("Localization Example");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        firstNameLabel = new Label();
        firstNameInput = new TextField();

        lastNameLabel = new Label();
        lastNameInput = new TextField();

        emailLabel = new Label();
        emailInput = new TextField();

        saveButton = new Button();

        grid.add(new Label("Select Language: "), 0, 0);
        grid.add(languageSelector, 1, 0);
        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameInput, 1, 1);
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameInput, 1, 2);
        grid.add(emailLabel, 0, 3);
        grid.add(emailInput, 1, 3);
        grid.add(saveButton, 1, 4);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        updateUserInterface(primaryStage);

        saveButton.setOnAction(e -> saveData(firstNameInput.getText(), lastNameInput.getText(), emailInput.getText(), languageSelector.getValue()));
    }

    private void updateUserInterface(Stage primaryStage) {
        primaryStage.setTitle(bundle.getString("app.title"));
        firstNameLabel.setText(bundle.getString("label.firstName"));
        lastNameLabel.setText(bundle.getString("label.lastName"));
        emailLabel.setText(bundle.getString("label.email"));
        saveButton.setText(bundle.getString("button.save"));

        firstNameInput.setPromptText(bundle.getString("label.firstName"));
        lastNameInput.setPromptText(bundle.getString("label.lastName"));
        emailInput.setPromptText(bundle.getString("label.email"));
    }

    private String getTableName(String selectedLanguage) {
        switch (selectedLanguage) {
            case "Farsi":
                return TABLE_FA;
            case "Japanese":
                return TABLE_JA;
            default:
                return TABLE_EN;
        }
    }

    private void saveData(String firstName, String lastName, String email, String selectedLanguage) {
        String tableName = getTableName(selectedLanguage);
        String sql = "INSERT INTO " + tableName + " (first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework", "Admin", "Admin");
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.executeUpdate();
            System.out.println(bundle.getString("message.saved"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}