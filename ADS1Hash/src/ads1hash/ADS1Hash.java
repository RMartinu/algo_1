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
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyCharacterCombination;
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
    
    public boolean importDayData(Stage s, int numberOfImports) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select CSV to import from");
        fc.getExtensionFilters().add(new ExtensionFilter("Stock Data Files", "*.csv"));
        File fileToOpen = fc.showOpenDialog(s);

        /**
         * Try to import some data. There must both be a source file selected as
         * well as a receiving object available
         */
        if (fileToOpen != null && recentStockData != null) {
            DataReader dr = new DataReader(fileToOpen);
            /*we want a specific number of datapoints*/
            if (numberOfImports > 0) {
                for (int i = 0; i < numberOfImports; i++) {
                    
                    DayData temp = dr.getDayData();
                    if (temp != null) {
                        recentStockData.insertDayData(temp);
                    } else {
                        pp.update();
                        return false;
                    }
                    
                }
            } else {
                /*get the whole file*/
                DayData temp;
                while (true) {
                    temp = dr.getDayData();
                    if (temp == null) {
                        pp.update();
                        return true;
                    }
                    recentStockData.insertDayData(temp);
                }
            }
            // pp.setStock(recentStockData);
            pp.update();
        }
        
        return true;
        
    }
    
    public void CreateStock() {
        Stage creationStage = new Stage();
        creationStage.setTitle("Create a new Stock Entry");
        GridPane grid = new GridPane();
        Scene sc = new Scene(grid, 350, 300);
        
        Button perf = new Button("Submit");
        Label head = new Label("Create a new Stock");
        Label lName = new Label("Name: ");
        TextField tfWkn = new TextField();
        tfWkn.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.E) {
                perf.setFocusTraversable(true);
            }
        });
        TextField tfAbbreviation = new TextField();
        tfAbbreviation.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                tfWkn.requestFocus();
            }
        });
        
        TextField tfName = new TextField();
        tfName.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                tfAbbreviation.requestFocus();
            }
        });
        Label lAbrreviation = new Label("Abbreviation");
        
        Label lWkn = new Label("WKN");
        
        perf.defaultButtonProperty().bind(perf.focusedProperty());
        perf.setOnAction(e -> {
            if (tfName.getText().isEmpty()) {
                tfName.requestFocus();
            } else if (tfAbbreviation.getText().isEmpty()) {
                tfAbbreviation.requestFocus();
            } else if (tfWkn.getText().isEmpty()) {
                tfWkn.requestFocus();
            } else {
                recentStockData = new StockData(tfName.getText(), tfAbbreviation.getText(), tfWkn.getText());
                this.dataTable.insert(recentStockData);
                pp.setStock(recentStockData);
                creationStage.hide();
            }
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
        Button btn = new Button("Search");
        TextField tf = new TextField("enter here...");
        tf.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                btn.fire();
            }
        });
        Label lNotFound = new Label();
        
        btn.defaultButtonProperty().bind(btn.focusedProperty());
        
        v.getChildren().addAll(l, tf, lNotFound, btn);
        btn.setOnAction(e -> {
            String s = (tf.getText().length() > 4) ? tf.getText().substring(0, 4) : tf.getText();
            StockData t = (whatFor.charAt(0) == 'N') ? dataTable.findByName(tf.getText()) : dataTable.findByAbbreviation(s);
            if (t != null) {
                
                recentStockData = t;
                pp.setStock(recentStockData);
                searchStage.hide();
                /*The StockPlotter gets the new Dataset and will update itself*/
            } else {
                lNotFound.setText(tf.getText() + " not Found");
            }
        });
        Pane p = new Pane();
        p.getChildren().add(v);
        Scene sc = new Scene(p, 350, 150);
        searchStage.setScene(sc);
        
        searchStage.show();
        
        return null;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Ask for a certain size, the constructor will choose an appropriate aproximation*/
        dataTable = new HashTable(2000);
        ExtensionFilter eFilter = new ExtensionFilter("ADS Hashtable File", "*.AHF");

        /**
         * Events are fired to early :/
         */
        primaryStage.widthProperty().addListener((observable, newVal, oldVal) -> {
            pp.update();
        });
        primaryStage.heightProperty().addListener((observable, newVal, oldVal) -> {
            pp.update();
        });
        primaryStage.maximizedProperty().addListener((observable, newVal, oldVal) -> {
            pp.update();
        });
        
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
                recentStockData = null;
                pp.setStock(recentStockData);
                
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
            Platform.exit();
        });
        
        MenuItem miNewStock = new MenuItem("New Stock");
        
        miNewStock.setOnAction(e -> {
            System.out.println("Creating a new Entry");
            this.CreateStock();
        });
        MenuItem miImport = new MenuItem("Import Data");
        miImport.setOnAction((ActionEvent e) -> {
            
            importDayData(primaryStage, 30);
            pp.updateStockData();
            
        }
        );
        MenuItem miImportAll = new MenuItem("Import all Data");
        miImportAll.setOnAction(e -> {
            /*could be refactored into an overloaded version with just one argument;
            or zero args & always relating to primaryStage*/
            this.importDayData(primaryStage, -1);
            pp.updateStockData();
        });
        
        Menu smSearch = new Menu("Search Stock");
        MenuItem smiSearchByName = new MenuItem("Search by Name");
        smiSearchByName.setAccelerator(new KeyCodeCombination(KeyCode.N));
        smiSearchByName.setOnAction(e -> {
            //System.out.println("Searching by Name");
            String findme = this.SearchPanel("Name");
            //System.out.println("found" + findme);
        });
        MenuItem smiSearchByAbbreviation = new MenuItem("Search by Abbreviation");
        smiSearchByAbbreviation.setAccelerator(new KeyCodeCombination(KeyCode.A));
        smiSearchByAbbreviation.setOnAction(e -> {
            String findme = SearchPanel("Abbreviation");
            //System.out.println("Searching by Abbreviaion");
            //System.out.println("find me some " + findme);
        });
        smSearch.getItems().addAll(smiSearchByName, smiSearchByAbbreviation);
        
        Menu smDelete = new Menu("Delete Stock");
        
        MenuItem smiDelCurrent = new MenuItem("Delete current Stock");
        smiDelCurrent.setOnAction(e -> {
            dataTable.delete(recentStockData);
            recentStockData = null;
            pp.setStock(recentStockData);
        });
        MenuItem smiDelByName = new MenuItem("Delete Stock by Name");
        smiDelByName.setDisable(true);
        MenuItem smiDelByAbbrev = new MenuItem("Delete Stock by Abbreviation");
        smiDelByAbbrev.setDisable(true);
        
        smDelete.getItems().addAll(smiDelCurrent, new SeparatorMenuItem(), smiDelByName, smiDelByAbbrev);

        //ToDo: Make those other menu items live
        CheckMenuItem cmiOpen = new CheckMenuItem("Open Course");
        cmiOpen.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT1));
        cmiOpen.setOnAction(e -> {
            pp.showOpen(cmiOpen.isSelected());
        });
        
        CheckMenuItem cmiHigh = new CheckMenuItem("Daily High");
        cmiHigh.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT2));
        cmiHigh.setOnAction(e -> {
            pp.showHigh(cmiHigh.isSelected());
        });
        
        CheckMenuItem cmiLow = new CheckMenuItem("Daily Low");
        cmiLow.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT3));
        cmiLow.setOnAction(e -> {
            pp.showLow(cmiLow.isSelected());
        });
        
        CheckMenuItem cmiCLose = new CheckMenuItem("Closing Course");
        cmiCLose.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT4));
        cmiCLose.setOnAction(e -> {
            pp.showClose(cmiCLose.isSelected());
        });
        
        CheckMenuItem cmiVolume = new CheckMenuItem("Trade Volume");
        cmiVolume.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT5));
        cmiVolume.setOnAction(e -> {
            pp.showVolume(cmiVolume.isSelected());
        });
        
        CheckMenuItem cmiAdjClose = new CheckMenuItem("Adjusted Closeing");
        cmiAdjClose.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT6));
        cmiAdjClose.setOnAction(e -> {
            pp.showAdjClose(cmiAdjClose.isSelected());
        });
        
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
        stockMenu.getItems().addAll(smSearch, new SeparatorMenuItem(), miNewStock, new SeparatorMenuItem(), miImport, miImportAll, new SeparatorMenuItem(), smDelete);
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
