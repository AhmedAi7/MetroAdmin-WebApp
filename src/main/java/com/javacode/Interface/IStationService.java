package com.javacode.Interface;


import com.javacode.Model.Station;

import java.util.List;


public interface IStationService {
    public Station getClosestStation(double latitude, double longitude);
    public List<Station> getAllStations();

}
