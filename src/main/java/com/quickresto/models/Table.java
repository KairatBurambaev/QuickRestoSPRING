package com.quickresto.models;

public class Table {
    private int rows;
    private int columns;
    private String[][] data;

    public Table(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new String[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String getData(int row, int column) {
        return data[row][column];
    }

    public void setData(int row, int column, String value) {
        data[row][column] = value;
    }
}
