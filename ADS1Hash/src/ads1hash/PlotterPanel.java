/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.util.Arrays;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Robert Martinu
 */
public class PlotterPanel extends Pane {

    StockData workSet;
    HBox Layout = new HBox();

    GridPane gp = new GridPane();
    Pane Display = new Pane();

    Label lname = new Label("Stock Name");
    Label name = new Label("TestDAta");

    Label labbrev = new Label("Stock Abbreviation");
    Label abbrev = new Label();

    Label lwkn = new Label("WKN");
    Label wkn = new Label();

    Label ldate = new Label("Date");
    Label date = new Label();

    boolean showOpen;
    Label lopen = new Label("Open");
    Label open = new Label();

    boolean showHigh;
    Label lhigh = new Label("Daily High");
    Label high = new Label();

    boolean showLow;
    Label llow = new Label("Daily Low");
    Label low = new Label();

    boolean showClose;
    Label lclose = new Label("Close");
    Label close = new Label();

    boolean showVolume;
    Label lvolume = new Label("Trade Volume");
    Label volume = new Label();

    boolean showAdjClose;
    Label lAdjClose = new Label("Adjusted Close");
    Label adjClose = new Label();

    boolean renderAbsolute = true;
    StockData data;

    public PlotterPanel() {

        name.minWidth(100);
        name.setContentDisplay(ContentDisplay.RIGHT);
        gp.add(lname, 0, 0);
        gp.add(name, 1, 0);
        gp.add(labbrev, 0, 1);
        gp.add(abbrev, 1, 1);
        gp.add(lwkn, 0, 2);
        gp.add(wkn, 1, 2);
        gp.add(ldate, 0, 3);
        gp.add(date, 1, 3);
        gp.add(lopen, 0, 4);
        gp.add(open, 1, 4);
        gp.add(lhigh, 0, 5);
        gp.add(high, 1, 5);
        gp.add(llow, 0, 6);
        gp.add(low, 1, 6);
        gp.add(lclose, 0, 7);
        gp.add(close, 1, 7);
        gp.add(lvolume, 0, 8);
        gp.add(volume, 1, 8);
        gp.add(lAdjClose, 0, 9);
        gp.add(adjClose, 1, 9);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(5));
        gp.setPrefWidth(200);
        gp.setMinWidth(200);
        GridPane.setHalignment(name, HPos.RIGHT);
        GridPane.setHgrow(name, Priority.ALWAYS);
        Layout.getChildren().addAll(gp, Display);
        Rectangle backgnd = new Rectangle(650, 400);

        Display.getChildren().add(backgnd);
        this.getChildren().addAll(Layout);

    }

    //ToDo: implement all toggle functions
    public void showOpen(boolean show) {
        this.showOpen = show;
        this.update();
    }

    public void showHigh(boolean show) {
    }

    public void showLow(boolean show) {
    }

    public void showClose(boolean show) {
    }

    public void showVolume(boolean show) {
    }

    public void showAdjClose(boolean show) {
    }

    /**
     * Fills the pane with active plots based on recent StockData
     */
    public void update() {

        Display.getChildren().clear();

        Rectangle bg = new Rectangle(650, 400);
        bg.setFill(Color.BISQUE);
        Display.getChildren().add(bg);
        if (workSet == null) {
            this.name.setText("");

            this.abbrev.setText("");
            this.wkn.setText("");
            this.open.setText("");
            //ToDo: all the other labels need to be reset

            return;
        }
        /*Plotting the opening course*/
        //ToDo: the same for the others
        System.out.println(Display.getWidth());
        System.err.println(this.workSet.getName());

        if (this.showOpen) {

            double dPointsY[] = this.workSet.getOpeningCourse(30);

            plotData(dPointsY);
        }
        System.out.println("Updatingthe plot");
        this.name.setText(workSet.getName());
        this.abbrev.setText(workSet.getAbbreviation());
        this.wkn.setText(workSet.getWKN());

        if (workSet.containsDayData()) {
            this.open.setText(Double.toString(workSet.getOpeningCourse(1)[0]));
            //ToDo: The other fields need values too
        }

    }

    /**
     * DRY: reusable Code for every plotline
     */
    private void plotData(double dPoints[]) {

        double dPointsY[] = new double[dPoints.length];
        //ToDo: normalize Y coords to display size and mode
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for (double c : dPoints) {
            if (c < min) {
                min = c;
            }
            if (c > max) {
                max = c;
            }
        }
        //ToDO: Transform Coords according to render flag

        double s = 350 / max;
        System.err.println(min + " " + max + " " + s);

        if (renderAbsolute) {
            for (int i = 0; i < dPoints.length; i++) {
                dPointsY[i] = dPoints[i] * s;
            }
        } else {
            double biasedY[] = new double[dPoints.length];
            s = 350 / (max - min);
            for (int i = 0; i < biasedY.length; i++) {
                biasedY[i] = dPoints[i] - min;
                dPointsY[i] = biasedY[i] * s;
            }

        }
        for (int i = 0; i < dPoints.length; i++) {
            dPointsY[i] = 390 - dPointsY[i];
        }

        System.out.println(Arrays.toString(dPointsY));
        System.out.println(Arrays.toString(dPoints));

        double dPointsX[] = new double[dPointsY.length];

        int padding = 20;
        double spacing = (Display.getWidth() - 2 * padding) / (double) dPointsX.length;

        double xCoord = padding;
        for (int i = 0; i < dPointsX.length; i++) {
            dPointsX[i] = xCoord;
            xCoord += spacing;
        }

        double dPointsInterleaved[] = new double[dPointsX.length + dPointsY.length];

        //interleaving, als PolyLine wants its points in this format
        for (int i = 0; i < dPointsY.length; i++) {
            dPointsInterleaved[2 * i] = dPointsX[i];
            dPointsInterleaved[2 * i + 1] = dPointsY[i];
        }

        Polyline plot = new Polyline(dPointsInterleaved);
        //Display.getChildren().clear();
        plot.setStroke(Color.GREEN);
        Display.getChildren().add(plot);
    }

    /**
     * Sets the StockData the Panel is supposed to display.
     *
     * @param workset the Stockdata to be used
     */
    public void setStock(StockData workset) {
        if (workset == null) {
            System.err.println("Missin somethin");
        }
        this.workSet = workset;
        this.update();
    }

}
