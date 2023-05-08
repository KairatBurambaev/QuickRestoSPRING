package com.quickresto.project;

import java.util.List;

public class MathBuffer {
    private int pos;
    public List<Calculate.MathSymbol> mathSymbols;
    public MathBuffer (List<Calculate.MathSymbol> mathSymbols) {
        this.mathSymbols = mathSymbols;
    }
    public Calculate.MathSymbol next() {
        return mathSymbols.get(pos++);
    }

    public void back() {
        pos--;
    }

    public int getPos() {
        return pos;
    }
}
