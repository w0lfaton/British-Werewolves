package com.company;

import java.util.LinkedList;
import java.util.List;

public class Neuron {
    private int id;
    private double value;
    private double weight;
    private int action;
    private List<String> connectionActionList = new LinkedList<>();

    public Neuron(int id, double value, double weight, int action) {
        this.id = id;
        this.value = value;
        this.weight = weight;
        this.action = action;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public int getAction() {
        return action;
    }

    public int getId() {
        return id;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAction(int action) {
        this.action = action;
    }

    private boolean isConnectionInList(int id, int action) {
        for (String connection : connectionActionList) {
            String[] connectionData = connection.split(";");
            if (Integer.parseInt(connectionData[0]) == id && Integer.parseInt(connectionData[1]) == action) {
                return true;
            }
        }
        return false;
    }

    public String getNeuronConnection(int id, int action) {
        if (isConnectionInList(id, action)) {
            for (String connection : connectionActionList) {
                String[] connectionData = connection.split(";");
                if (Integer.parseInt(connectionData[0]) == id && Integer.parseInt(connectionData[1]) == action) {
                    return connection;
                }
            }
        }
        return "";
    }

    public boolean addNeuronConnection(int id, int action, double weight) {
        if (isConnectionInList(id, action)) {
            return false;
        }
        this.connectionActionList.add(id + ";" + action + ";" + weight);
        return true;
    }

    public boolean addNeuronConnection(String connection) {
        String[] connectionValues = connection.split(";");
        if (isConnectionInList(Integer.parseInt(connectionValues[0]), Integer.parseInt(connectionValues[1]))) {
            return false;
        }
        this.connectionActionList.add(connection);
        return true;
    }

    public boolean updateNeuronConnection(String connection) {
        String[] connectionValues = connection.split(";");
        if (isConnectionInList(Integer.parseInt(connectionValues[0]), Integer.parseInt(connectionValues[1]))) {
            this.connectionActionList.set(
                    this.connectionActionList.indexOf(
                            getNeuronConnection(Integer.parseInt(connectionValues[0]), Integer.parseInt(connectionValues[1]))),
                    connection);
            return true;
        }
        return false;
    }

    public boolean removeNeuronConnection(int id, int action) {
        if (isConnectionInList(id, action)) {
            this.connectionActionList.remove(getNeuronConnection(id, action));
            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        return "<neuron>" +
                id +
                ";" +
                value +
                ";" +
                weight +
                ";" +
                action +
                ";" +
                connectionActionList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Neuron) {
            if (((Neuron) obj).action == this.action)
                return ((Neuron) obj).id == this.id;
        }
        return false;
    }
}
