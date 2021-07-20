package com.javacode.payload.request;


import com.javacode.Model.Station;

public class StationRequest {
    private String[] prevStation;
    private Station station;
    private String[] afterStation;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String[] getPrevStation() {
        return prevStation;
    }

    public void setPrevStation(String[] prevStation) {
        this.prevStation = prevStation;
    }

    public String[] getAfterStation() {
        return afterStation;
    }

    public void setAfterStation(String[] afterStation) {
        this.afterStation = afterStation;
    }
}
