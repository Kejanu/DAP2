package Blatt08L.KevinSolution;

import java.util.ArrayList;

public class Helper {
    private int value;
    private ArrayList<Operation> operations;
    String stringOutput;

    public Helper() {
        this.value = 0;
        this.operations = new ArrayList<>();
        this.stringOutput = "";
    }

    public String getStringOutput() {
        return stringOutput;
    }

    public void setStringOutput(String stringOutput) {
        this.stringOutput = stringOutput;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }
}
