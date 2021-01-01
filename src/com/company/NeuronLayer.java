package com.company;

import java.util.LinkedList;
import java.util.List;

public class NeuronLayer {
    private List<Neuron> neuronList;
    private static int instanceCount = 0;
    private int id;

    private NeuronLayer(List<Neuron> neuronList) {
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
        this.neuronList.add(neuron.getLayerId(),neuron);
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
            this.neuronList.set(neuron.getNeuronId(),neuron);
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
        return "<layer>" +
                id +
                "," +
                neuronList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NeuronLayer) {
            return this.id == ((NeuronLayer) obj).getId();
        }
        return false;
    }

    public static NeuronLayer buildEmptyNetwork(int neuronCount) {
        List<Neuron> resultNeuronList = new LinkedList<>();
        for (int i = 0; i < neuronCount; i++) {
            Neuron neuron = new Neuron(i,0,0,i);
            resultNeuronList.add(neuron.getNeuronId(),neuron);
        }
        return new NeuronLayer(resultNeuronList);
    }

    public static NeuronLayer buildNetworkFromList(List<Neuron> neuronList) {
        if (neuronList == null) {
            return null;
        }
        return new NeuronLayer(neuronList);
    }

    public void calculateNeuronEfficiency() {

    }

    private int getPrediction(int a) {
        return -1;
    }
}
