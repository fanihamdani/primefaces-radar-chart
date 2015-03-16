package com.nostratech.viewobject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fani on 3/16/15.
 */
public class VoRadarDataset {

    private static final DecimalFormat df = new DecimalFormat("###.#");

    private static VoRadarDataset[] dataset = new VoRadarDataset[] {
            new VoRadarDataset("Q1", "rgba(192, 80, 77, 0.2)", "rgba(192, 80, 77, 1)"),
            new VoRadarDataset("Q2", "rgba(155, 187, 89, 0.2)", "rgba(155, 187, 89, 1)"),
            new VoRadarDataset("Q3", "rgba(187, 174, 45, 0.2)", "rgba(187, 174, 45, 1)"),
            new VoRadarDataset("Q4", "rgba(85, 134, 191, 0.2)", "rgba(85, 134, 191, 1)")
    };

    public static void resetData() {
        for (int i = 0; i < dataset.length; ++i) {
            dataset[i].data = null;
            dataset[i].data = new ArrayList<String>();
        }
    }

    public static void addData(int i, double d) {
        dataset[i].data.add(df.format(d));
    }

    public static void addDataQ1(double d) {
        addData(0, d);
    }

    public static void addDataQ2(double d) {
        addData(1, d);
    }

    public static void addDataQ3(double d) {
        addData(2, d);
    }

    public static void addDataQ4(double d) {
        addData(3, d);
    }

    public static VoRadarDataset[] get() {
        return dataset;
    }

    private final String pointStrokeColor = "#fff";
    private final String pointHighlightFill = "#fff";

    private String label;
    private String fillColor;
    private String strokeColor;
    private String pointColor;
    private String pointHighlightStroke;

    private List<String> data;

    private VoRadarDataset(String label, String fillColor, String strokeColor) {
        this.label = label;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.pointColor = strokeColor;
        this.pointHighlightStroke = strokeColor;

        this.data = new ArrayList<String>();
    }

    public String getPointHighlightFill() {
        return pointHighlightFill;
    }

    public String getPointHighlightStroke() {
        return pointHighlightStroke;
    }

    public String getPointStrokeColor() {
        return pointStrokeColor;
    }

    public String getLabel() {
        return label;
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public String getPointColor() {
        return pointColor;
    }

    public List<String> getData() {
        return data;
    }

}