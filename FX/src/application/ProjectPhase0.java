package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

public class ProjectPhase0 extends Application {
	// Creating the needed components for the application
	private List<Internet> myList = new List(500);
	private TextField name = new TextField();// for the user to be able to enter the name of the country to add
	private TextField name1 = new TextField();// for the user to be able to enter the name of the country to delete
	private TextField name2 = new TextField();// for the user to be able to enter the name of the country to search
	private TextField perc = new TextField();// for the user to be able to enter the percentage of the country to add
	private BorderPane bpBase = new BorderPane();
	private MenuBar menuBar = new MenuBar();
	private Menu menu = new Menu("File");
	private MenuItem file = new MenuItem("Open File");
	private Button add = new Button("Add");
	private Button delete = new Button("Delete");
	private Button search = new Button("Search");
	private Button display = new Button("Display");
	private HBox hbox1 = new HBox(10);// adding the name and perc for the add button
	private HBox hboxName = new HBox(10);// adding the name for the delete button
	private HBox hbox2 = new HBox(10);// adding the name for the search button
	private VBox vbox = new VBox(10);// for all the buttons
	private VBox vbox1 = new VBox(10);// for the table
	private TableView<Internet> table = new TableView<>();
	private TableColumn<Internet, String> countryColumn = new TableColumn<>("Country");
	private TableColumn<Internet, Double> percentColumn = new TableColumn<>("Percentage");
	private ObservableList<Internet> observableList = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Project Phase 0");
		
		// Adding components to the UI
		menu.getItems().add(file);
		menuBar.getMenus().add(menu);
		bpBase.setTop(menuBar);

		name.setPromptText("enter new name to add");
		name1.setPromptText("enter a name to delete");
		name2.setPromptText("enter name to search");
		perc.setPromptText("enter new percentage to add");

		hbox1.getChildren().addAll(name, perc);
		hboxName.getChildren().add(name1);
		hbox2.getChildren().add(name2);

		vbox.getChildren().addAll(add, hbox1, delete, hboxName, search, hbox2, display);
		bpBase.setCenter(vbox);

		countryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		percentColumn.setCellValueFactory(new PropertyValueFactory<>("percent"));
		table.getColumns().addAll(countryColumn, percentColumn);
		vbox1.getChildren().add(table);

		// disabling the compenents until the user select/load the file
		name.setDisable(true);
		name1.setDisable(true);
		name2.setDisable(true);
		perc.setDisable(true);
		add.setDisable(true);
		delete.setDisable(true);
		search.setDisable(true);
		display.setDisable(true);

		// Creating the needed actions for each button

		file.setOnAction(e -> {
			loadFile();
			// Enable the components after loading the file
			name.setDisable(false);
			name1.setDisable(false);
			name2.setDisable(false);
			perc.setDisable(false);
			add.setDisable(false);
			delete.setDisable(false);
			search.setDisable(false);
			display.setDisable(false);
		});

		add.setOnAction(e -> add());
		delete.setOnAction(e -> delete());
		search.setOnAction(e -> search());
		display.setOnAction(e -> {
			display();
			bpBase.setRight(vbox1);
		});

		Scene scene = new Scene(bpBase, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// all the methods that are used to add/delete/search/display the data inside
	// the file
	private void loadFile() {
		try {
			observableList.clear();
			Scanner scanner = new Scanner(new File("internet_2020.txt"));
			while (scanner.hasNextLine()) {
				String s[] = scanner.nextLine().split(",");
				if (s.length == 2) {
					Internet net = new Internet(s[0], Double.parseDouble(s[1].trim()));
					myList.add(net);
					observableList.add(net);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void add() {
		String countryName = name.getText();
		String percentText = perc.getText();
		try {
			double percent = Double.parseDouble(percentText);
			Internet net = new Internet(countryName, percent);
			myList.add(net);
			appendToFile("internet_2020.txt", countryName + "," + percent);
			name.clear();
			perc.clear();
		} catch (NumberFormatException e) {
			System.err.println("Invalid percentage input");
		}
	}

	private void delete() {
		String nameToDelete = name1.getText();
		Internet net = new Internet(nameToDelete, 0);
		int exists = myList.find(net);
		if (exists != -1) {
			myList.delete(net);
			updateFile("internet_2020.txt");
			System.out.println("The country " + nameToDelete + " has been deleted");
		} else
			System.out.println("The country " + nameToDelete + " does not exist");
		name1.clear();
	}

	private void search() {
		String nameToSearch = name2.getText();
		Internet net = new Internet(nameToSearch, 0);
		int found = myList.find(net);
		if (found != -1) {
			System.out.println("The country " + nameToSearch + " exists.");
		} else {
			System.out.println("The country " + nameToSearch + " does not exist.");

		}
		name2.clear();
	}

	private void display() {
		observableList.clear();
		for (int i = 0; i < myList.count; i++) {
			Internet net = myList.set(i);
			observableList.add(net);
		}
		table.setItems(observableList);
	}

	private void appendToFile(String filePath, String data) {
		try (FileWriter fileWriter = new FileWriter(filePath, true);
				PrintWriter printWriter = new PrintWriter(fileWriter)) {
			printWriter.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateFile(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
				PrintWriter printWriter = new PrintWriter(writer)) {
			for (int i = 0; i < myList.count; i++) {
				Internet net = myList.set(i);
				printWriter.println(net.getName() + "," + net.getPercent());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
