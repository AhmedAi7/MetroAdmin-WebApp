package com.javacode.Controller;

import com.javacode.Interface.IBasicStationService;
import com.javacode.Interface.IStationService;
import com.javacode.Model.Line;
import com.javacode.Model.Station;
import com.javacode.Reporsitory.LineRepo;
import com.javacode.Service.LineService;
import com.javacode.payload.request.UpdateStationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class StationController {

    @Autowired
    IStationService stationService;
    @Autowired
    LineRepo lineRepo;

    @Autowired
    IBasicStationService basicStationService;

    @Autowired
    private LineService lineService;


    @GetMapping("/GetAllStations")
    public ResponseEntity<?> getAllStations()
    {
        return new ResponseEntity<>(basicStationService.getAllStations(),HttpStatus.OK);
    }

    @RequestMapping("/addStation")
    public String getStations(Model model)
    {
        List<Station> stations = stationService.getAllStations();
        List<Line> lines = lineService.getAllLines();
        model.addAttribute("stations", stations);
        model.addAttribute("lines", lines);
        return "addStation";
    }

    @RequestMapping("/Station")
    public String goToStation(Model model)
    {
        List<Station> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "station";
    }

    @RequestMapping("/UpdateStation")
    public ResponseEntity<?> updateStation(@RequestBody UpdateStationRequest updateStationRequest)
    {
        Map<String, String> map = new HashMap<>();
        if(basicStationService.updateStation(updateStationRequest.getId(),updateStationRequest.getStation()))
            map.put("message", "success");
        else
            map.put("message", "failed");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
