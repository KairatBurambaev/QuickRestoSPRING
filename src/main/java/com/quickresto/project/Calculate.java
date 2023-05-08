package com.quickresto.project;

import java.util.ArrayList;
import java.util.List;

public class Calculate {
    public static List<MathSymbol> MathAnalyze(String inpText, Table input) {
        ArrayList<MathSymbol> mathSymbols = new ArrayList<>();
        String ABCD = "ABCD";
        int pos = 0;
        while (pos < inpText.length()) {
            char s = inpText.charAt(pos);
            switch (s) {
                case '(':
                    mathSymbols.add(new MathSymbol(Math.LEFT_BRACE, s));
                    pos++;
                    continue;
                case ')':
                    mathSymbols.add(new MathSymbol(Math.RIGHT_BRACE, s));
                    pos++;
                    continue;
                case '+':
                    mathSymbols.add(new MathSymbol(Math.OP_PLUS, s));
                    pos++;
                    continue;
                case '-':
                    mathSymbols.add(new MathSymbol(Math.OP_MINUS, s));
                    pos++;
                    continue;
                case '*':
                    mathSymbols.add(new MathSymbol(Math.OP_MUL, s));
                    pos++;
                    continue;
                case '/':
                    mathSymbols.add(new MathSymbol(Math.OP_DIV, s));
                    pos++;
                    continue;
                default:
                    if (s <= '9' && s >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(s);
                            pos++;
                            if (pos >= inpText.length()) {
                                break;
                            }
                            s = inpText.charAt(pos);
                        } while (s <= '9' && s >= '0');
                        mathSymbols.add(new MathSymbol(Math.NUMBER, sb.toString()));
                    } else if (ABCD.contains(String.valueOf(s))) {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(s);
                            pos++;
                            if (pos >= inpText.length()) {
                                break;
                            }
                            s = inpText.charAt(pos);
                        } while (s <= '9' && s >= '0');
                        String linka;
                        String column = String.valueOf(sb.charAt(0));
                        int row = Integer.valueOf(sb.charAt(1)) - 49;
                        switch (column) {
                            case "A" :
                                linka = input.getData(row, 0);
                                if (linka.charAt(0) != '=') {
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, linka));
                                    continue;
                                } else {
                                    List<MathSymbol> mathSymbols1 = MathAnalyze(linka.substring(1), input);
                                    MathBuffer mathBuffer1 =  new MathBuffer(mathSymbols1);
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, String.valueOf(expr(mathBuffer1))));
                                }
                                continue;
                            case "B" :
                                linka = input.getData(row, 1);
                                if (linka.charAt(0) != '=') {
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, linka));
                                    continue;
                                } else {
                                    List<MathSymbol> mathSymbols1 = MathAnalyze(linka.substring(1), input);
                                    MathBuffer mathBuffer1 =  new MathBuffer(mathSymbols1);
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, String.valueOf(expr(mathBuffer1))));
                                }
                                continue;
                            case "C" :
                                linka = input.getData(row, 2);
                                if (linka.charAt(0) != '=') {
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, linka));
                                    continue;
                                } else {
                                    List<MathSymbol> mathSymbols1 = MathAnalyze(linka.substring(1), input);
                                    MathBuffer mathBuffer1 =  new MathBuffer(mathSymbols1);
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, String.valueOf(expr(mathBuffer1))));
                                }
                                continue;
                            case "D" :
                                linka = input.getData(row, 3);
                                if (linka.charAt(0) != '=') {
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, linka));
                                    continue;
                                } else {
                                    List<MathSymbol> mathSymbols1 = MathAnalyze(linka.substring(1), input);
                                    MathBuffer mathBuffer1 =  new MathBuffer(mathSymbols1);
                                    mathSymbols.add(new MathSymbol(Math.NUMBER, String.valueOf(expr(mathBuffer1))));
                                }
                                continue;
                            default:
                                System.out.println("гу епь");
                                continue;
                        }
                    }
            }
        }
        mathSymbols.add(new MathSymbol(Math.EOF, ""));
        return mathSymbols;
    }
    public static Table calculate(Table input) {
        String str;
        char c;
        for (int i = 0; i < input.getRows(); i++) {
            for (int j = 0; j < input.getColumns(); j++) {
                str = input.getData(i, j);
                if (str.charAt(0) != '=') {
                    continue;
                } else {
                    List<MathSymbol> mathSymbols = MathAnalyze(str.substring(1), input);
                    MathBuffer mathBuffer =  new MathBuffer(mathSymbols);
                    System.out.println(expr(mathBuffer));
                }
            }
        }
        return input;
    }

//    public static Table calcTable(Table input) {
//        Table inputTable = input;
//        Table resultTable = calculate(inputTable);
//        return resultTable;
//    }
    public static class MathSymbol {
        Math type;
        String value;

        public MathSymbol(Math type, String value) {
            this.type = type;
            this.value = value;
        }

        public MathSymbol(Math type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        @Override
        public String toString() {
            return "MathSymbol{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static double plusminus (MathBuffer mathSymbols) {
        double value = multdiv(mathSymbols);
        while (true) {
            MathSymbol mathSymbol = mathSymbols.next();
            switch (mathSymbol.type) {
                case OP_PLUS:
                    value += multdiv(mathSymbols);
                    break;
                case OP_MINUS:
                    value -= multdiv(mathSymbols);
                    break;
                default:
                    mathSymbols.back();
                    return value;
            }
        }
    }
    public static double expr (MathBuffer mathSymbols) {
        MathSymbol mathSymbol = mathSymbols.next();
        if (mathSymbol.type == Math.EOF) {
            return 0;
        } else {
            mathSymbols.back();
            return plusminus(mathSymbols);
        }
    }
    public static double multdiv (MathBuffer mathSymbols) {
        double value = factor(mathSymbols);
        while (true) {
            MathSymbol mathSymbol = mathSymbols.next();
            switch (mathSymbol.type) {
                case OP_MUL:
                    value *= factor(mathSymbols);
                    break;
                case OP_DIV:
                    value /= factor(mathSymbols);
                    break;
                default:
                    mathSymbols.back();
                    return value;
            }
        }
    }
    public static double factor (MathBuffer mathSymbols) {
        MathSymbol mathSymbol = mathSymbols.next();
        switch (mathSymbol.type) {
            case OP_MINUS:
                double value = factor(mathSymbols);
                return -value;
            case  NUMBER:
                return Integer.parseInt(mathSymbol.value);
            case LEFT_BRACE:
                value = expr(mathSymbols);
                mathSymbol = mathSymbols.next();
                if (mathSymbol.type != Math.RIGHT_BRACE) {
                    throw new RuntimeException("Unexpected token: " + mathSymbol.value);
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + mathSymbol.value);
        }
    }
}
