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

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                char letter = (char) ('A' + col);
                String value = String.format("%s%d", letter, row + 1);
                data[row][col] = value;
            }
        }
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