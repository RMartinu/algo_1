/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Robert Martinu
 */
public class ADS1Hash extends Application {

    PlotterPanel pp=new PlotterPanel();
    StockData recentStockData;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuBar menuBar=new MenuBar();
        
        Menu fileMenu=new Menu("File");
        Menu stockMenu=new Menu("Stock");
        Menu plotMenu=new Menu("Plot");
        
        MenuItem miLoad=new MenuItem("Load");
        miLoad.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
        miLoad.setOnAction(e->{System.out.println("Loading map from File");});
        
        MenuItem miSave=new MenuItem("Save");
        miSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        miSave.setOnAction(e->{System.out.println("Saving Hashmap to File");});
        MenuItem miExit=new MenuItem("Quit");
        miExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        miExit.setOnAction(e->{System.out.println("Bye");});
        
        
        MenuItem miNewStock=new MenuItem("New Stock");
  
        miNewStock.setOnAction(e->{System.out.println("Creating a new Entry");});
        MenuItem miImport=new MenuItem("Import Data");
        
        Menu smSearch=new Menu("Search Stock");
        MenuItem smiSearchByName=new MenuItem("Search by Name");
        smiSearchByName.setAccelerator(new KeyCodeCombination(KeyCode.N));
        smiSearchByName.setOnAction(e->{System.out.println("Searching by Name");});
        MenuItem smiSearchByAbbreviation=new MenuItem("Search by Abbreviation");
        smiSearchByAbbreviation.setAccelerator(new KeyCodeCombination(KeyCode.A));
        smiSearchByAbbreviation.setOnAction(e->{System.out.println("Searching by Abbreviaion");});
        smSearch.getItems().addAll(smiSearchByName, smiSearchByAbbreviation);
        
        Menu smDelete= new Menu("Delete Stock");
       
        MenuItem smiDelCurrent=new MenuItem("Delete current Stock");
        MenuItem smiDelByName=new MenuItem("Delete Stock by Name");
        MenuItem smiDelByAbbrev=new MenuItem("Delete Stock by Abbreviation");
        
        smDelete.getItems().addAll(smiDelCurrent,new SeparatorMenuItem(),smiDelByName,smiDelByAbbrev);
        
        
        CheckMenuItem cmiOpen=new CheckMenuItem("Open Course");
        cmiOpen.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT1));
        cmiOpen.setOnAction(e->{pp.showOpen(cmiOpen.isSelected());});
        
        CheckMenuItem cmiHigh=new CheckMenuItem("Daily High");
        CheckMenuItem cmiLow=new CheckMenuItem("Daily Low");
        CheckMenuItem cmiCLose=new CheckMenuItem("Closing Course");
        CheckMenuItem cmiVolume=new CheckMenuItem("Trade Volume");
        CheckMenuItem cmiAdjClose=new CheckMenuItem("Adjusted Closeing");
        
        RadioMenuItem rmiPlotAbsolute=new RadioMenuItem("Absolute Values");
        RadioMenuItem rmiPlotRelative=new RadioMenuItem("Relative Values");
        rmiPlotAbsolute.setSelected(true);
        
        ToggleGroup plotStyle=new ToggleGroup();
        rmiPlotAbsolute.setToggleGroup(plotStyle);
        rmiPlotRelative.setToggleGroup(plotStyle);
        
        fileMenu.getItems().addAll(miLoad,miSave,new SeparatorMenuItem() ,miExit);
        stockMenu.getItems().addAll(smSearch, new SeparatorMenuItem(), miNewStock,miImport, smDelete);
        plotMenu.getItems().addAll(cmiOpen,cmiHigh,cmiLow,cmiCLose,cmiVolume,cmiAdjClose,new SeparatorMenuItem(), rmiPlotAbsolute, rmiPlotRelative);
        
        menuBar.getMenus().addAll(fileMenu, stockMenu, plotMenu);
        
        
        
        
        
        BorderPane root = new BorderPane();
        
        root.setTop(menuBar);
        root.setCenter(pp);
        Scene scene = new Scene(root, 900,450);
        primaryStage.setTitle("ADS1 - HashTable");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
