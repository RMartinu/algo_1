/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Robert Martinu
 */
public class PlotterPanel extends Pane{

    
    HBox Layout=new HBox();
    
    GridPane gp=new GridPane();
    Pane Display=new Pane();
     
    Label lname=new Label("Stock Name");
    Label name=new Label();
    
    Label labbrev=new Label("Stock Abbreviation");
    Label abbrev=new Label();
    
    
    Label lwkn=new Label("WKN");
    Label wkn=new Label();
    
    
    Label ldate=new Label("Date");
    Label date=new Label();
    
    Label lopen=new Label("Open");
    Label open=new Label();
    
    Label lhigh=new Label("Daily High");
    Label high=new Label();
    
    Label llow=new Label("Daily Low");
    Label low=new Label();
    
    Label lclose=new Label("Close");
    Label close=new Label();
    
    
    Label lvolume=new Label("Trade Volume");
    Label volume=new Label();
    
    
    Label lAdjClose=new Label("Close");
    Label adjClose=new Label();
    
    StockData data;

    public PlotterPanel() {
        
        gp.add(lname, 0, 0); gp.add(name, 1, 0);
        gp.add(labbrev, 0, 1); gp.add(abbrev, 1, 1);
        gp.add(lwkn,0,2); gp.add(wkn, 1, 2);
        gp.add(ldate, 0, 3 ); gp.add(date, 1, 3);
        gp.add(lopen, 0, 4);gp.add(open, 1, 4);
        gp.add(lhigh, 0, 5);gp.add(high, 1, 5);
        gp.add(llow, 0, 6);gp.add(low, 1, 6);
        gp.add(lclose,0,7);gp.add(close, 1, 7);
        gp.add(lvolume, 0, 8);gp.add(volume,1,8);
        gp.add(lAdjClose, 0, 9);gp.add(adjClose, 1, 9);
        gp.setHgap(10); gp.setVgap(10);
        gp.setPadding(new Insets(5));
        gp.setPrefWidth(200);
        Layout.getChildren().addAll(gp,Display);
        Rectangle backgnd=new Rectangle(250, 150);
        Display.getChildren().add(backgnd);
        this.getChildren().addAll(Layout);
        
        
    }
    
    
    
    
    
    
    
}
