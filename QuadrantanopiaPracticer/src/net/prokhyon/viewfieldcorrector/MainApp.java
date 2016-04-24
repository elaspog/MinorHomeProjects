package net.prokhyon.viewfieldcorrector;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.prokhyon.viewfieldcorrector.model.ViewFieldSettings;
import net.prokhyon.viewfieldcorrector.view.SettingsOverviewController;
import net.prokhyon.viewfieldcorrector.view.ViewFieldController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private SettingsOverviewController settingsOverviewController;
	private ViewFieldController viewFieldController;
	private ViewFieldSettings viewFieldSettings;

	public MainApp() {
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("View Field Corrector");

		initRootLayout();
		showSettingsOverview();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ViewFieldSettings getViewFieldSettings() {

		if (viewFieldSettings == null)
			viewFieldSettings = new ViewFieldSettings();

		return viewFieldSettings;
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showSettingsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SettingsOverview.fxml"));
			AnchorPane settingsOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(settingsOverview);

			settingsOverviewController = loader.getController();
			settingsOverviewController.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showViewField() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ViewField.fxml"));
			StackPane pane = (StackPane) loader.load();

			Stage stage = new Stage();
			stage.setTitle("View Field");
			Scene scene = new Scene(pane);
			stage.setScene(scene);

			viewFieldController = loader.getController();
			viewFieldController.setMainApp(this);
			viewFieldController.setDialogStage(stage);

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// stage.initModality(Modality.APPLICATION_MODAL);
// stage.initOwner(primaryStage);
// controller.setPerson(person);
// stage.showAndWait();