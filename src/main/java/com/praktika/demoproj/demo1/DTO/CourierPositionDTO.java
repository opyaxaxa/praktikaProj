package com.praktika.demoproj.demo1.DTO;

import org.locationtech.jts.geom.Point;

public class CourierPositionDTO {
    private double latitude;
    private double longitude;
    private int id;

    public CourierPositionDTO(Point point, int id) {
        this.latitude = point.getX();  // Широта = Y
        this.longitude = point.getY(); // Долгота = X
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }
}
