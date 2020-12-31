package com.company;

import java.util.Arrays;

public class InOutDataModel {
    private int[] aRow;
    private int[] desiredOutcome;

    public InOutDataModel(int[] firstSet, int[] outcomeSet) {
        this.aRow = firstSet;
        this.desiredOutcome = outcomeSet;
        System.out.println(this.toString());
    }

    public int[] getaRow() {
        return aRow;
    }

    public void setaRow(int[] aRow) {
        this.aRow = aRow;
    }

    public int[] getDesiredOutcome() {
        return desiredOutcome;
    }

    public void setDesiredOutcome(int[] desiredOutcome) {
        this.desiredOutcome = desiredOutcome;
    }

    @Override
    public String toString() {
        return Arrays.toString(aRow).substring(1, Arrays.toString(aRow).length() - 1) +
                ";"  +
                Arrays.toString(desiredOutcome).substring(1, Arrays.toString(desiredOutcome).length() - 1);
    }
}
