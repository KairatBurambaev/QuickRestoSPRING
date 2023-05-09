package com.quickresto.project;

public class Table {
    public static int SIZE = 4;
    private int rows;
    private int columns;
    private String[][] data;

    public Table() {
        this.rows = SIZE;
        this.columns = SIZE;
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