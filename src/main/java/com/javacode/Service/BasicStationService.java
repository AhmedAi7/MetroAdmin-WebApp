package com.javacode.Service;

import com.javacode.Interface.IBasicStationService;
import com.javacode.Model.Line;
import com.javacode.Model.Station;
import com.javacode.Reporsitory.LineRepo;
import com.javacode.Reporsitory.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicStationService implements IBasicStationService {
    @Autowired
    StationRepo stationRepo;

    @Autowired
    LineRepo lineRepo;

    private boolean isSameLineStations(List<Line> stationOneLines, List<Line> stationTwoLines, List<Line> stationThreeLines)
    {
        List<Integer> firstStations = new ArrayList<>();
        for (Line line : stationOneLines)
            firstStations.add(line.getId());

        List<Integer> secondStations = new ArrayList<>();
        for (Line line : stationTwoLines)
            secondStations.add(line.getId());

        List<Integer> thirdStations = new ArrayList<>();
        for (Line line : stationThreeLines)
            thirdStations.add(line.getId());

        List<Line> allLines = lineRepo.findAll();
        for(int i = 0 ; i < allLines.size() ; i++)
        {
            if(firstStations.contains(allLines.get(i).getId()) && secondStations.contains(allLines.get(i).getId()) && thirdStations.contains(allLines.get(i).getId()))
                return true;
        }
        return false;
    }
    private boolean isSameLineStations(List<Line> stationOneLines,List<Line> stationTwoLines)
    {
        List<Integer> firstStations = new ArrayList<>();
        for (Line line : stationOneLines)
            firstStations.add(line.getId());

        List<Integer> secondStations = new ArrayList<>();
        for (Line line : stationTwoLines)
            secondStations.add(line.getId());

        List<Line> allLines = lineRepo.findAll();
        for(int i = 0 ; i < allLines.size() ; i++)
        {
            if(firstStations.contains(allLines.get(i).getId()) && secondStations.contains(allLines.get(i).getId()) )
                return true;
        }
        return false;
    }


    @Override
    public List<Station> getAllStations()
    {
        return stationRepo.findAll();
    }

    ///add station in the middle
    @Override
    public boolean addMiddleStation(String prevStation,String stationName,double latitude,double longitude,Integer lineID,String afterStation, Integer region)
    {
        Station station = stationRepo.findByName(stationName);
        if(station == null) {
            station = new Station();
            station.setName(stationName);
        }
        station.setLatitude(latitude);
        station.setLongitude(longitude);
        station.setRegion(region);
        Line line = lineRepo.getById(lineID);
        if(line == null)
            return false;
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        station.setLines(lines);

        Station prevSt = stationRepo.findByName(prevStation);
        if(prevSt == null)
            return false;

        Station afterSt = stationRepo.findByName(afterStation);
        if(afterSt == null)
            return false;

        if(!isSameLineStations(prevSt.getLines(),station.getLines(),afterSt.getLines()))
            return false;
        if(prevSt.getAfter().contains(afterSt))
        {
            prevSt.getAfter().remove(afterSt);
            afterSt.getPrevious().remove(prevSt);
            prevSt.getAfter().add(station);
            station.getPrevious().add(prevSt);
            station.getAfter().add(afterSt);
            afterSt.getPrevious().add(station);
            line.getStations().add(station);
            stationRepo.save(station);
            stationRepo.save(prevSt);
            stationRepo.save(afterSt);
            return true;
        }
        return false;
    }

    //add in the last
    @Override
    public boolean addLastStation(String prevStation,String stationName,double latitude,double longitude,Integer lineID, Integer region)
    {
        Station station = stationRepo.findByName(stationName);
        if(station == null) {
            station = new Station();
            station.setName(stationName);
        }
        station.setLatitude(latitude);
        station.setLongitude(longitude);
        station.setRegion(region);
        Line line = lineRepo.getById(lineID);
        if(line == null)
            return false;
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        station.setLines(lines);

        Station prevSt = stationRepo.findByName(prevStation);

        if(prevSt == null || prevSt.getAfter().size() > 0 || (!isSameLineStations(prevSt.getLines(),station.getLines())) )
            return false;

        prevSt.getAfter().add(station);
        station.getPrevious().add(prevSt);
        line.getStations().add(station);
        stationRepo.save(station);
        stationRepo.save(prevSt);
        return true;
    }

    //add in the first
    @Override
    public boolean addAfterStation(String stationName,double latitude,double longitude,Integer lineID,String afterStation, Integer region)
    {
        Station station = stationRepo.findByName(stationName);
        if(station == null) {
            station = new Station();
            station.setName(stationName);
        }
        station.setLatitude(latitude);
        station.setLongitude(longitude);
        station.setRegion(region);
        Line line = lineRepo.getById(lineID);
        if(line == null)
            return false;
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        station.setLines(lines);

        Station afterSt = stationRepo.findByName(afterStation);
        if(afterSt == null || afterSt.getPrevious().size() > 0 || (!isSameLineStations(afterSt.getLines(),station.getLines())))
            return false;

        station.getAfter().add(afterSt);
        afterSt.getPrevious().add(station);
        line.getStations().add(station);
        stationRepo.save(station);
        stationRepo.save(afterSt);
        return true;
    }

    // add to new line doesnt contain any stations
    @Override
    public boolean addFirstLineStation(String stationName,double latitude,double longitude,Integer lineID, Integer region)
    {
        Station station = stationRepo.findByName(stationName);
        if(station == null) {
            station = new Station();
            station.setName(stationName);
        }
        station.setLatitude(latitude);
        station.setLongitude(longitude);
        station.setRegion(region);
        Line line = lineRepo.getById(lineID);
        if(line == null)
            return false;
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        station.setLines(lines);

        if(line.getStations().size() > 0)
            return false;
        line.getStations().add(station);
        stationRepo.save(station);
        return true;
    }

    @Override
    public boolean updateStation(Integer id,Station newStation)
    {
        Station station = stationRepo.findById(id).get();
        if(station == null)
            return false;
        if(newStation.getName() != null)
            station.setName(newStation.getName());
        if(newStation.getLatitude() != null)
            station.setLatitude(newStation.getLatitude());
        if(newStation.getLongitude() != null)
            station.setLongitude(newStation.getLongitude());
        if(newStation.getRegion() != null)
            station.setRegion(newStation.getRegion());
        stationRepo.save(station);
        return true;
    }

    @Override
    public Station getStation(Integer id)
    {
        return stationRepo.findById(id).get();
    }

}
