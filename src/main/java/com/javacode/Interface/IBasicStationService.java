package com.javacode.Interface;


import com.javacode.Model.Station;

import java.util.List;

public interface IBasicStationService {
    public List<Station> getAllStations();
    public boolean addMiddleStation(String prevStation,String stationName,double latitude,double longitude,Integer lineID,String afterStation, Integer region);
    public boolean addLastStation(String prevStation,String stationName,double latitude,double longitude,Integer lineID, Integer region);
    public boolean addAfterStation(String stationName,double latitude,double longitude,Integer lineID,String afterStation, Integer region);
    public boolean addFirstLineStation(String stationName,double latitude,double longitude,Integer lineID, Integer region);
    public boolean updateStation(Integer id,Station newStation);
    public Station getStation(Integer id);
}