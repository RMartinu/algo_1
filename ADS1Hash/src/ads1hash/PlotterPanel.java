/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
    int numberOfPlotPoints = 30;
    int numberOfAvailablePoints;
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

    Label lPlotDepth = new Label("Plot Depth");
    TextField lPotDepthIndicator = new TextField("");

    Slider Plotdepth = new Slider();

    boolean renderAbsolute = true;
    StockData data;

    private void adjustDepthSlider() {
        if (workSet != null && workSet.containsDayData()) {
            //System.err.println("enable slider");

            lPotDepthIndicator.setText(Integer.toString(numberOfPlotPoints));

            Plotdepth.setMin(5);
            Plotdepth.setDisable(false);
            Plotdepth.setMax(numberOfAvailablePoints);
            Plotdepth.setValue(30);
        } else {
            lPotDepthIndicator.setText("");
            Plotdepth.setDisable(true);
        }
    }

    public PlotterPanel() {

        Plotdepth.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            numberOfPlotPoints = (int) Plotdepth.getValue();

            // System.err.println(Plotdepth.getValue());
            lPotDepthIndicator.setText(Integer.toString(numberOfPlotPoints));
            update();
        });
        adjustDepthSlider();

        lPotDepthIndicator.setOnAction(e -> {
            int t = 30;
            try {
                t = Integer.parseInt(lPotDepthIndicator.getText());
            } catch (NumberFormatException ex) {
                System.err.println("missparse");
            }
            numberOfPlotPoints = t;
            Plotdepth.setValue(t);
            update();
        });
        lPotDepthIndicator.setDisable(true);

//        lPotDepthIndicator.setOnMouseEntered(e -> {
//            lPotDepthIndicator.setDisable(false);
//        });
//        lPotDepthIndicator.setOnMouseExited(e -> {
//            lPotDepthIndicator.setDisable(true);
//        });
        Display.setOnMouseEntered(e -> {
            lPotDepthIndicator.setDisable(true);
        });
        Display.setOnMouseExited(e -> {
            if (!Plotdepth.isDisable()) {
                lPotDepthIndicator.setDisable(false);
            }
        });
        Plotdepth.setOnMouseExited(e -> {
            lPotDepthIndicator.setDisable(false);
        });

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
        gp.add(lPlotDepth, 0, 12);
        gp.add(lPotDepthIndicator, 1, 12);
        gp.add(Plotdepth, 0, 13);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(5));
        gp.setPrefWidth(200);
        gp.setMinWidth(200);

        //ToDo: Set alignment for other data fields
        GridPane.setHalignment(name, HPos.RIGHT);
        GridPane.setHgrow(name, Priority.ALWAYS);

        GridPane.setHalignment(abbrev, HPos.RIGHT);
        GridPane.setHgrow(abbrev, Priority.ALWAYS);

        GridPane.setHalignment(wkn, HPos.RIGHT);
        GridPane.setHgrow(wkn, Priority.ALWAYS);

        GridPane.setHalignment(adjClose, HPos.RIGHT);
        GridPane.setHgrow(adjClose, Priority.ALWAYS);

        GridPane.setHalignment(close, HPos.RIGHT);
        GridPane.setHgrow(close, Priority.ALWAYS);

        GridPane.setHalignment(date, HPos.RIGHT);
        GridPane.setHgrow(date, Priority.ALWAYS);
        GridPane.setHalignment(high, HPos.RIGHT);
        GridPane.setHgrow(high, Priority.ALWAYS);
        GridPane.setHalignment(low, HPos.RIGHT);
        GridPane.setHgrow(low, Priority.ALWAYS);

        GridPane.setHalignment(volume, HPos.RIGHT);
        GridPane.setHgrow(volume, Priority.ALWAYS);

        GridPane.setHalignment(open, HPos.RIGHT);
        GridPane.setHgrow(open, Priority.ALWAYS);

        Layout.getChildren().addAll(gp, Display);
        // Rectangle backgnd = new Rectangle(650, 400);

        // Display.getChildren().add(backgnd);
        this.getChildren().addAll(Layout);

    }

    //ToDo: implement all toggle functions
    public void showOpen(boolean show) {
        this.showOpen = show;
        this.update();
    }

    public void showHigh(boolean show) {
        this.showHigh = show;
        this.update();
    }

    public void showLow(boolean show) {
        this.showLow = show;
        this.update();
    }

    public void showClose(boolean show) {
        showClose = show;
        update();
    }

    public void showVolume(boolean show) {
        showVolume = show;
        update();
    }

    public void showAdjClose(boolean show) {
        showAdjClose = show;
        update();
    }

    public void updateStockData() {
        if (workSet == null) {
            //update();
            Plotdepth.setValue(30);
            Plotdepth.setDisable(true);
            lPotDepthIndicator.setDisable(true);
            this.name.setText("");

            this.abbrev.setText("");
            this.wkn.setText("");
            this.date.setText("");

            //ToDo: all the other labels need to be reset
            this.open.setText("");
            this.high.setText("");
            this.low.setText("");
            this.close.setText("");
            this.volume.setText("");
            this.adjClose.setText("");
            update();

            return;
        }
        numberOfAvailablePoints = workSet.getOpeningCourse().length;
        this.numberOfPlotPoints = Integer.min(numberOfAvailablePoints, 30);
        this.adjustDepthSlider();
        this.Plotdepth.setValue(numberOfPlotPoints);
        update();
    }

    /**
     * Fills the pane with active plots based on recent StockData
     */
    public void update() {

        Display.getChildren().clear();

        Rectangle bg = new Rectangle(this.getWidth() - gp.getWidth(), this.getHeight());
        bg.setFill(Color.BLACK);
        Display.getChildren().add(bg);
        if (workSet == null) {
            return;
        }

        /*Plotting the opening course*/
        //ToDo: the same for the others
//        System.out.println(Display.getWidth());
//        System.err.println(this.workSet.getName());
        if (this.showOpen) {

            double dPointsY[] = this.workSet.getOpeningCourse(numberOfPlotPoints);

            plotData(dPointsY, Color.CYAN);
        }

        if (this.showHigh) {
            double dPointsY[] = this.workSet.getHighestCourse(numberOfPlotPoints);
            plotData(dPointsY, Color.GREEN);
        }

        if (this.showLow) {
            double dPointsY[] = this.workSet.getLowestCourse(numberOfPlotPoints);
            plotData(dPointsY, Color.RED);
        }

        if (this.showVolume) {
            long ldPointsY[] = this.workSet.getVolume(numberOfPlotPoints);
            double dPointsY[] = new double[ldPointsY.length];
            for (int i = 0; i < dPointsY.length; i++) {
                dPointsY[i] = ldPointsY[i];
            }
            plotData(dPointsY, Color.BLUE);

        }
        if (this.showAdjClose) {
            double dPointsY[] = this.workSet.getAdjustedCloseCourse(numberOfPlotPoints);
            plotData(dPointsY, Color.CRIMSON);
        }

        if (this.showClose) {
            double dPointsY[] = this.workSet.getCloseCourse(numberOfPlotPoints);
            plotData(dPointsY, Color.BLUEVIOLET);
        }

        // System.out.println("Updatingthe plot");
        this.name.setText(workSet.getName());
        this.abbrev.setText(workSet.getAbbreviation());
        this.wkn.setText(workSet.getWKN());

        if (workSet.containsDayData()) {
            DayData mostRecent = workSet.getMostRecentDataPoint();
            this.open.setText(String.format("%.2f",mostRecent.getOpenCourse()));
            //ToDo: The other fields need values too

            
            this.high.setText(String.format("%.2f",mostRecent.getHighestCourse()));
            this.low.setText(String.format("%.2f",mostRecent.getLowestCourse()));
            this.close.setText(String.format("%.2f",mostRecent.getCloseCourse()));
            this.volume.setText(Long.toString(mostRecent.getVolume()));
            this.adjClose.setText(String.format("%.2f",mostRecent.getAdjustedCloseCourse()));
            this.date.setText(mostRecent.getDate().toString());
        } else {
            //ToDo: The data labels may contains debris, clean up

            this.open.setText("");
            this.high.setText("");
            this.low.setText("");
            this.close.setText("");
            this.volume.setText("");
            this.adjClose.setText("");
        }

    }

    /**
     * DRY: reusable Code for every plotline
     */
    private void plotData(double dPoints[], Color theColorToUse) {
        double height = Display.getHeight();
        double width = Display.getWidth();
        double padding = 10; //leave a little bit of distance

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

        double s = (height - (2 * padding)) / max;
        //System.err.println(min + " " + max + " " + s);

        /**
         * Apply scaling factor between stock value and display size Reverse
         * array so latest entries appear on the right
         */
        if (renderAbsolute) {
            for (int i = 0; i < dPoints.length; i++) {
                dPointsY[i] = dPoints[dPoints.length - (i + 1)] * s;
            }
        } else {
            double biasedY[] = new double[dPoints.length];
            s = (height - (2 * padding)) / (max - min);

            for (int i = 0; i < biasedY.length; i++) {
                biasedY[i] = dPoints[dPoints.length - (i + 1)] - min;
                dPointsY[i] = biasedY[i] * s;
            }

        }
        /**
         * Java screen coordinates go from up to down, high course values should
         * make the plot go up -> stock value is distance from larger edge
         */
        for (int i = 0; i < dPoints.length; i++) {
            dPointsY[i] = height - padding - dPointsY[i];
        }

        //  System.out.println(Arrays.toString(dPointsY));
//        System.out.println(Arrays.toString(dPoints));
        double dPointsX[] = new double[dPointsY.length];

        // int padding = 20;
        double spacing = (width - 2 * padding) / (double) dPointsX.length;

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
        plot.setStroke(theColorToUse);
        Display.getChildren().add(plot);
    }

    /**
     * Sets the StockData the Panel is supposed to display.
     *
     * @param workset the Stockdata to be used
     */
    public void setStock(StockData workset) {
//        if (workset == null) {
//            System.err.println("Missin somethin");
//        }
        this.workSet = workset;
        updateStockData();

    }

}
