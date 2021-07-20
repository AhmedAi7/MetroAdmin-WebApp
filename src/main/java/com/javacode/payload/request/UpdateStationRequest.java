package com.javacode.payload.request;


import com.javacode.Model.Station;

public class UpdateStationRequest {
    Integer id;
    Station station;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
