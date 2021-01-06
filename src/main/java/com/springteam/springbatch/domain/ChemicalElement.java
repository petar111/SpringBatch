/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.springbatch.domain;

/**
 *
 * @author siux
 */
public class ChemicalElement {
    private String name;
    private int[] energyLevels;
    private boolean isMetal;
    private String state;

    public ChemicalElement() {
    }

    public ChemicalElement(String name, int[] energyLevels, boolean isMetal, String state) {
        this.name = name;
        this.energyLevels = energyLevels;
        this.isMetal = isMetal;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getEnergyLevels() {
        return energyLevels;
    }

    public void setEnergyLevels(int[] energyLevels) {
        this.energyLevels = energyLevels;
    }

    public boolean isIsMetal() {
        return isMetal;
    }

    public void setIsMetal(boolean isMetal) {
        this.isMetal = isMetal;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ChemicalElement{" + "name=" + name + ", energyLevels=" + energyLevels + ", isMetal=" + isMetal + ", state=" + state + '}';
    }
    
    
    
}
