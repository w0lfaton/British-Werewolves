package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class NeuronNetworkData {
    private static final int INPUT_LAYER_INDEX = 0;
    private static NeuronNetworkData instance = new NeuronNetworkData();
    private List<NeuronNetwork> neuronNetworkList = new LinkedList<>();

    private NeuronNetworkData() {
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NeuronNetwork getNeuronNetwork(int networkId) {
        return this.neuronNetworkList.get(networkId-1);
    }

    public static NeuronNetworkData getInstance() {
        return instance;
    }

    public boolean saveData() throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File("configuration.txt");
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (NeuronNetwork network : neuronNetworkList) {
                bufferedWriter.write(network.toString());
                bufferedWriter.write("\n");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

    public boolean loadData() throws IOException {
        BufferedReader bufferedReader = null;
        try {
            File file = new File("configuration.txt");
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            //Read line
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                String[] networks = dataLine.split("<network>");
                for (String networkcheck : networks) {
                    if (networkcheck.equals("")) {
                        continue;
                    }
                    List<Neuron> neuronList = new LinkedList<>();
                    //Split into an array with id as index 0.
                    String[] separated = networkcheck.split(",", 2);
                    int networkId;
                    for (int i = 0; i < separated.length; i++) {
                        String networkLine = "";
                        if (i == 0) {
                            //Store first string in array as id for network.
                            networkId = Integer.parseInt(separated[i]);
                        } else {
                            //Remove '[' and ']' from beginning and end.
                            networkLine = separated[i].substring(1, separated[i].length() - 1);
                            //Split string with regex of ', '.
                            String[] neuronLines = networkLine.split("<neuron>");
                            for (int j = 0; j < neuronLines.length; j++) {
                                if (neuronLines[j].equals("")) {
                                    continue;
                                }
                                String neuron = neuronLines[j];
                                //Split neuronVales into an array.
                                String[] neuronValues = neuron.split(";", 5);
                                Neuron loadedNeuron = new Neuron(Integer.parseInt(neuronValues[0]),
                                        Integer.parseInt(neuronValues[1]),
                                        Integer.parseInt(neuronValues[2]),
                                        Integer.parseInt(neuronValues[3]));
                                if (!neuronValues[4].equals("[], ") && !neuronValues[4].equals("]]") && !neuronValues[4].equals("[]")) {
                                    String connections = neuronValues[4].substring(1, neuronValues[4].length() - 2);
                                    String[] connectionArray = connections.split(", ");
                                    for (String connection : connectionArray) {
                                        loadedNeuron.addNeuronConnection(connection);
                                    }
                                }
                                neuronList.add(loadedNeuron);
                            }
                        }
                    }
                    loadNetworkToList(neuronList);
                }
                dataLine = bufferedReader.readLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    private boolean loadNetworkToList(List<Neuron> neuronList) {
        if (neuronList != null) {
            NeuronNetwork neuronNetwork = NeuronNetwork.buildNetworkFromList(neuronList);
            this.neuronNetworkList.add(neuronNetwork.getId()-1, neuronNetwork);
            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        return instance.neuronNetworkList.toString();
    }
}
