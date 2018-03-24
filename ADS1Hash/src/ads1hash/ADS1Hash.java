/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert Martinu
 */
public class ADS1Hash extends Application {

    PlotterPanel pp = new PlotterPanel();
    StockData recentStockData;
    HashTable dataTable;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Application.launch(args);
    }

    public void importDayData(Stage s, int numberOfImports) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select CSV to import from");
        fc.getExtensionFilters().add(new ExtensionFilter("Stock Data Files", "*.csv"));
        File fileToOpen = fc.showOpenDialog(s);
        if (fileToOpen != null && recentStockData != null) {
            DataReader dr = new DataReader(fileToOpen);
            for (int i = 0; i < numberOfImports; i++) {
                recentStockData.insertDayData(dr.getDayData());

            }
            pp.setStock(recentStockData);
        }

    }

    public void CreateStock() {
        Stage creationStage = new Stage();
        creationStage.setTitle("Create a new Stock Entry");
        GridPane grid = new GridPane();
        Scene sc = new Scene(grid, 350, 300);

        Label head = new Label("Create a new Stock");
        Label lName = new Label("Name: ");
        TextField tfName = new TextField();
        Label lAbrreviation = new Label("Abbreviation");
        TextField tfAbbreviation = new TextField();
        Label lWkn = new Label("WKN");
        TextField tfWkn = new TextField();
        Button perf = new Button("Submit");
        perf.defaultButtonProperty().bind(perf.focusedProperty());
        perf.setOnAction(e -> {
            recentStockData = new StockData(tfName.getText(), tfAbbreviation.getText(), tfWkn.getText());
            this.dataTable.insert(recentStockData);
            pp.setStock(recentStockData);
            creationStage.hide();
        });

        grid.add(head, 0, 0);
        grid.add(lName, 0, 1);
        grid.add(tfName, 1, 1);

        grid.add(lAbrreviation, 0, 2);
        grid.add(tfAbbreviation, 1, 2);

        grid.add(lWkn, 0, 3);
        grid.add(tfWkn, 1, 3);

        grid.add(perf, 0, 4);

        creationStage.setScene(sc);
        creationStage.show();
    }

    public String SearchPanel(String whatFor) {

        Stage searchStage = new Stage();

        searchStage.setTitle("Searching for: " + whatFor);
        VBox v = new VBox();
        Label l = new Label("Search for " + whatFor);
        TextField tf = new TextField("enter here...");
        Button btn = new Button("Search");
        v.getChildren().addAll(l, tf, btn);
        btn.setOnAction(e -> {
            String s = tf.getText();
            recentStockData = (whatFor.charAt(0) == 'N') ? dataTable.findByName(s) : dataTable.findByAbbreviation(s);
            pp.setStock(recentStockData);
            /*The StockPlotter gets the new Dataset and will update itself*/
        });
        Pane p = new Pane();
        p.getChildren().add(v);
        Scene sc = new Scene(p, 350, 150);
        searchStage.setScene(sc);

        searchStage.show();

        return null;
    }

    public void insertPanel() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dataTable = new HashTable(2000);
        ExtensionFilter eFilter = new ExtensionFilter("ADS Hashtable File", "*.AHF");
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu stockMenu = new Menu("Stock");
        Menu plotMenu = new Menu("Plot");

        MenuItem miLoad = new MenuItem("Load");
        miLoad.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
        miLoad.setOnAction(e -> {
            System.out.println("Loading map from File");
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(eFilter);
            File loadFrom = fc.showOpenDialog(primaryStage);
            if (loadFrom != null) {
                System.out.println("laoding from: " + loadFrom.toURI());
                try {
                    dataTable.readFromFile(loadFrom);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ADS1Hash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        MenuItem miSave = new MenuItem("Save");
        miSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        miSave.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(eFilter);
            File saveTo = fc.showSaveDialog(primaryStage);
            if (saveTo != null) {
                System.out.println("Saving Hashmap to File:" + saveTo.toString()
                );
                try {
                    dataTable.saveToFile(saveTo);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ADS1Hash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        MenuItem miExit = new MenuItem("Quit");
        miExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        miExit.setOnAction(e -> {
            System.out.println("Bye");
        });

        MenuItem miNewStock = new MenuItem("New Stock");

        miNewStock.setOnAction(e -> {
            System.out.println("Creating a new Entry");
            this.CreateStock();
        });
        MenuItem miImport = new MenuItem("Import Data");
        miImport.setOnAction(e -> this.importDayData(primaryStage, 30));

        Menu smSearch = new Menu("Search Stock");
        MenuItem smiSearchByName = new MenuItem("Search by Name");
        smiSearchByName.setAccelerator(new KeyCodeCombination(KeyCode.N));
        smiSearchByName.setOnAction(e -> {
            System.out.println("Searching by Name");
            String findme = this.SearchPanel("Name");
            System.out.println("found" + findme);
        });
        MenuItem smiSearchByAbbreviation = new MenuItem("Search by Abbreviation");
        smiSearchByAbbreviation.setAccelerator(new KeyCodeCombination(KeyCode.A));
        smiSearchByAbbreviation.setOnAction(e -> {
            String findme = SearchPanel("Abbreviation");
            System.out.println("Searching by Abbreviaion");
            System.out.println("find me some " + findme);
        });
        smSearch.getItems().addAll(smiSearchByName, smiSearchByAbbreviation);

        Menu smDelete = new Menu("Delete Stock");

        MenuItem smiDelCurrent = new MenuItem("Delete current Stock");
        MenuItem smiDelByName = new MenuItem("Delete Stock by Name");
        MenuItem smiDelByAbbrev = new MenuItem("Delete Stock by Abbreviation");

        smDelete.getItems().addAll(smiDelCurrent, new SeparatorMenuItem(), smiDelByName, smiDelByAbbrev);

        CheckMenuItem cmiOpen = new CheckMenuItem("Open Course");
        cmiOpen.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT1));
        cmiOpen.setOnAction(e -> {
            pp.showOpen(cmiOpen.isSelected());
        });

        CheckMenuItem cmiHigh = new CheckMenuItem("Daily High");
        CheckMenuItem cmiLow = new CheckMenuItem("Daily Low");
        CheckMenuItem cmiCLose = new CheckMenuItem("Closing Course");
        CheckMenuItem cmiVolume = new CheckMenuItem("Trade Volume");
        CheckMenuItem cmiAdjClose = new CheckMenuItem("Adjusted Closeing");

        RadioMenuItem rmiPlotAbsolute = new RadioMenuItem("Absolute Values");
        RadioMenuItem rmiPlotRelative = new RadioMenuItem("Relative Values");
        rmiPlotAbsolute.setSelected(true);
        rmiPlotAbsolute.setOnAction(e -> {
            this.pp.renderAbsolute = true;
            pp.update();
        });
        rmiPlotRelative.setOnAction(e -> {
            this.pp.renderAbsolute = false;
            pp.update();
        });

        ToggleGroup plotStyle = new ToggleGroup();
        rmiPlotAbsolute.setToggleGroup(plotStyle);
        rmiPlotRelative.setToggleGroup(plotStyle);

        fileMenu.getItems().addAll(miLoad, miSave, new SeparatorMenuItem(), miExit);
        stockMenu.getItems().addAll(smSearch, new SeparatorMenuItem(), miNewStock, miImport, smDelete);
        plotMenu.getItems().addAll(cmiOpen, cmiHigh, cmiLow, cmiCLose, cmiVolume, cmiAdjClose, new SeparatorMenuItem(), rmiPlotAbsolute, rmiPlotRelative);

        menuBar.getMenus().addAll(fileMenu, stockMenu, plotMenu);

        BorderPane root = new BorderPane();

        root.setTop(menuBar);
        root.setCenter(pp);
        Scene scene = new Scene(root, 900, 450);
        primaryStage.setTitle("ADS1 - HashTable");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
