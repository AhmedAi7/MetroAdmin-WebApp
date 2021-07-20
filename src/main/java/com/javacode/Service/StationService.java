package com.javacode.Service;


import com.javacode.Interface.IStationService;
import com.javacode.Model.Station;
import com.javacode.Reporsitory.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationService implements IStationService {
    @Autowired
    private StationRepo stationRepo;

    private double getDistanceBetweenPoints(double lat1, double long1, double lat2,double long2) {
        double distance = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
        return distance;
    }

    @Override
    public Station getClosestStation(double latitude, double longitude) {
        List<Station> stations = stationRepo.findAll();
        Station nearestStation = new Station();
        nearestStation.setName(stations.get(0).getName());
        nearestStation.setLatitude(stations.get(0).getLatitude());
        nearestStation.setLongitude(stations.get(0).getLongitude());
        nearestStation.setId(stations.get(0).getId());
        double minDistance = getDistanceBetweenPoints(latitude,longitude,stations.get(0).getLatitude(),stations.get(0).getLongitude());
        for(int i = 1 ; i < stations.size() ; i++)
        {
            double tmpDistance = getDistanceBetweenPoints(latitude,longitude,stations.get(i).getLatitude(),stations.get(i).getLongitude());
            if(tmpDistance <= minDistance)
            {
                minDistance = tmpDistance;
                nearestStation.setId(stations.get(i).getId());
                nearestStation.setName(stations.get(i).getName());
                nearestStation.setLatitude(stations.get(i).getLatitude());
                nearestStation.setLongitude(stations.get(i).getLongitude());
            }
        }
        nearestStation.setLines(stationRepo.findById(nearestStation.getId()).get().getLines());
        return nearestStation;
    }

    @Override
    public List<Station> getAllStations()
    {
        List<Station> stations = stationRepo.findAll();
        return stations;
    }
}
