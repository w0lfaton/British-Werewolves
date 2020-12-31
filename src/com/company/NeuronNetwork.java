package com.company;

import java.util.LinkedList;
import java.util.List;

public class NeuronNetwork {
    private List<Neuron> neuronList;
    private static int instanceCount = 0;
    private int id;

    private NeuronNetwork(List<Neuron> neuronList) {
        instanceCount++;
        this.id = instanceCount;
        this.neuronList = neuronList;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Neuron getNeuron(int id) {
        return this.neuronList.get(id);
    }

    public boolean addNeuron(Neuron neuron) {
        if (neuron == null || isNeuronInList(neuron)) {
            return false;
        }
        this.neuronList.add(neuron.getId(),neuron);
        return true;
    }

    public boolean removeNeuron(int id) {
        if (id >= 0) {
            this.neuronList.remove(id);
            return true;
        }
        return false;
    }

    public boolean updateNeuron(Neuron neuron) {
        if (neuron == null) {
            return false;
        }
        if (isNeuronInList(neuron)) {
            this.neuronList.set(neuron.getId(),neuron);
            return true;
        }
        return false;
    }

    private boolean isNeuronInList(Neuron neuron) {
        for (Neuron check : this.neuronList) {
            if (neuron.equals(check)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "<network>" +
                id +
                "," +
                neuronList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NeuronNetwork) {
            return this.id == ((NeuronNetwork) obj).getId();
        }
        return false;
    }

    public static NeuronNetwork buildEmptyNetwork(int neuronCount) {
        List<Neuron> resultNeuronList = new LinkedList<>();
        for (int i = 0; i < neuronCount; i++) {
            Neuron neuron = new Neuron(i,0,0,i);
            resultNeuronList.add(neuron.getId(),neuron);
        }
        return new NeuronNetwork(resultNeuronList);
    }

    public static NeuronNetwork buildNetworkFromList(List<Neuron> neuronList) {
        if (neuronList == null) {
            return null;
        }
        return new NeuronNetwork(neuronList);
    }

    public void calculateNeuronEfficiency() {

    }

    public void trainNetwork(InOutDataModel trainingData) {
        for (int i = 0; i < trainingData.getaRow().length; i++) {
            int aNumber = trainingData.getaRow()[i];
            int desiredOutcome = trainingData.getDesiredOutcome()[i];
            int prediction = getPrediction(aNumber);

        }
    }

    private int getPrediction(int a) {

        int index = 0;
        for (Neuron neuron : this.neuronList) {

        }
        return -1;
    }
}
