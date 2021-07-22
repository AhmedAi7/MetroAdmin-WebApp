package com.javacode.Controller;

import com.javacode.Interface.IBasicStationService;
import com.javacode.Model.Station;
import com.javacode.payload.request.UpdateStationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BasicStationController {

    @Autowired
    IBasicStationService basicStationService;

    @RequestMapping("/AddNewStation")
    public String addStation(Model model, String station_name, String longitude, String latitude, String region,
                             String line_num, String previous_station, String next_station)
    {
        String message = null;
        try
        {
            if(station_name != null && latitude != null && longitude != null &&
                    region != null && line_num != null && next_station != null && previous_station != null)
            {
                message = addMiddleStation(previous_station, station_name, Double.valueOf(latitude), Double.valueOf(longitude),
                        Integer.valueOf(line_num), next_station, Integer.valueOf(region));
            }
            else if(station_name != null && latitude != null && longitude != null &&
                    region != null && line_num != null && previous_station != null)
            {
                message = addLastStation(previous_station, station_name, Double.valueOf(latitude), Double.valueOf(longitude),
                        Integer.valueOf(line_num), Integer.valueOf(region));
            }
            else if(station_name != null && latitude != null && longitude != null &&
                    region != null && line_num != null && next_station != null)
            {
                message = addAfterStation(station_name, Double.valueOf(latitude), Double.valueOf(longitude),
                        Integer.valueOf(line_num), next_station, Integer.valueOf(region));
            }
            else if(station_name != null && latitude != null && longitude != null && region != null && line_num != null)
            {
                message = addStation(station_name, Double.valueOf(latitude), Double.valueOf(longitude),
                        Integer.valueOf(line_num), Integer.valueOf(region));
            }}catch (Exception e)
        {
            System.out.println(e);
        }
        model.addAttribute("message", message);
        return "redirect:/addStation";
    }
    public String addMiddleStation(String prevStation,String stationName,double latitude,double longitude,Integer lineID,String afterStation, Integer region)
    {
        if (basicStationService.addMiddleStation(prevStation,stationName,latitude,longitude,lineID,afterStation, region))
            return  "Station Added Successfully";
        else
            return "Station Failed";
    }
    public String addLastStation(String prevStation,String stationName,double latitude,double longitude,Integer lineID, Integer region)
    {
        if (basicStationService.addLastStation(prevStation,stationName,latitude,longitude,lineID,region))
            return  "Station Added Successfully";
        else
            return "Station Failed";
    }

    public String addAfterStation(String stationName,double latitude,double longitude,Integer lineID,String afterStation, Integer region)
    {
        if (basicStationService.addAfterStation(stationName,latitude,longitude,lineID,afterStation,region))
            return  "Station Added Successfully";
        else
            return "Station Failed";
    }

    public String addStation(String stationName,double latitude,double longitude,Integer lineID, Integer region)
    {
        if ( basicStationService.addFirstLineStation(stationName,latitude,longitude,lineID,region))
            return  "Station Added Successfully";
        else
            return "Station Failed";
    }

    @RequestMapping("/EditStation/{id}")
    public String goToEditStation(Model model, @PathVariable(name = "id") Integer id)
    {
        model.addAttribute("station", basicStationService.getStation(id));
        return "editStation";
    }

    @RequestMapping("/SaveStation")
    public String saveStation(Model model, @ModelAttribute("station") Station station)
    {
        UpdateStationRequest updateStationRequest = new UpdateStationRequest();
        updateStationRequest.setStation(station);
        updateStationRequest.setId(station.getId());
        String message = updateStation(updateStationRequest);
        model.addAttribute("message", message);
        return "redirect:/Station";
    }

    public String updateStation(UpdateStationRequest updateStationRequest)
    {
        if(basicStationService.updateStation(updateStationRequest.getId(),updateStationRequest.getStation()))
            return "Station Updated Successfully";

        return "Station Failed";

    }
}


