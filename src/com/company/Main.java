package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static NeuronLayerData neuronLayerData = NeuronLayerData.getInstance();

    public static void main(String[] args) {
        try {
            InOutDataModel dataSet = new InOutDataModel(
                    new int[]{3,4,8,1,4,5,9,4,0,7,2},
                    new int[]{6,7,11,4,7,8,12,7,3,10,5}
            );
            System.out.println(dataSet.toString());
            Thread.sleep(100);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String input = scanner.next();
                if (input.equals("save")) {
                    try {
                        neuronLayerData.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (input.equals("display")) {
                    String[] networks = neuronLayerData.toString()
                            .substring(1, neuronLayerData.toString().length()-1)
                            .split("<network>");
                    for (String network : networks) {
                        if (network.equals("")) {
                            continue;
                        }
                        System.out.println(network);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
