/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carloscortina.demo.model;

/**
 *
 * @author Ccortina_Mac
 */
public class GraphData {
    
    double coordinateX;
    double coordinateY;

    public GraphData() {
        this.coordinateX = 0;
        this.coordinateY = 0;
    }

    
    public GraphData(double coordinateX, double coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }
    
    
    
}
