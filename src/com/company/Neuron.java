package com.company;

import java.util.LinkedList;
import java.util.List;

public class Neuron {
    private int layerId;
    private double value;
    private double weight;
    private int neuronId;
    private List<String> connectionList = new LinkedList<>();

    public Neuron(int layerId, double value, double weight, int neuronId) {
        this.layerId = layerId;
        this.value = value;
        this.weight = weight;
        this.neuronId = neuronId;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public int getNeuronId() {
        return neuronId;
    }

    public int getLayerId() {
        return layerId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setLayerId(int layerId) {
        this.layerId = layerId;
    }

    public void setNeuronId(int neuronId) {
        this.neuronId = neuronId;
    }

    private boolean isConnectionInList(int layerId, int neuronId) {
        for (String connection : connectionList) {
            String[] connectionData = connection.split(";");
            if (Integer.parseInt(connectionData[0]) == layerId && Integer.parseInt(connectionData[1]) == neuronId) {
                return true;
            }
        }
        return false;
    }

    public String getNeuronConnection(int layerId, int neuronId) {
        if (isConnectionInList(layerId, neuronId)) {
            for (String connection : connectionList) {
                String[] connectionData = connection.split(";");
                if (Integer.parseInt(connectionData[0]) == layerId && Integer.parseInt(connectionData[1]) == neuronId) {
                    return connection;
                }
            }
        }
        return "";
    }

    public boolean addNeuronConnection(int layerId, int neuronId, double weight) {
        if (isConnectionInList(layerId, neuronId)) {
            return false;
        }
        this.connectionList.add(layerId + ";" + neuronId + ";" + weight);
        return true;
    }

    public boolean addNeuronConnection(String connection) {
        String[] connectionValues = connection.split(";");
        if (isConnectionInList(Integer.parseInt(connectionValues[0]), Integer.parseInt(connectionValues[1]))) {
            return false;
        }
        this.connectionList.add(connection);
        return true;
    }

    public boolean updateNeuronConnection(String connection) {
        String[] connectionValues = connection.split(";");
        if (isConnectionInList(Integer.parseInt(connectionValues[0]), Integer.parseInt(connectionValues[1]))) {
            this.connectionList.set(
                    this.connectionList.indexOf(
                            getNeuronConnection(Integer.parseInt(connectionValues[0]), Integer.parseInt(connectionValues[1]))),
                    connection);
            return true;
        }
        return false;
    }

    public boolean removeNeuronConnection(int layerId, int neuronId) {
        if (isConnectionInList(layerId, neuronId)) {
            this.connectionList.remove(getNeuronConnection(layerId, neuronId));
            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        return "<neuron>" +
                layerId +
                ";" +
                value +
                ";" +
                weight +
                ";" +
                neuronId +
                ";" +
                connectionList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Neuron) {
            if (((Neuron) obj).neuronId == this.neuronId)
                return ((Neuron) obj).layerId == this.layerId;
        }
        return false;
    }
}
