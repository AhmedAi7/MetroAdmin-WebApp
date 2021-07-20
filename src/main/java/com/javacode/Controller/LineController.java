package com.javacode.Controller;


import com.javacode.Interface.ILineService;
import com.javacode.Model.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LineController {


    @Autowired
    ILineService lineService;

    @RequestMapping("/GetAllLines")
    public ResponseEntity<?> getAllLines()
    {
        Map<String, List<Line>> map = new HashMap<>();
        map.put("All lines :",lineService.getAllLines());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping("/AddNewLine")
    public String addNewLine(String line_num)
    {
        lineService.addNewLine(Integer.valueOf(line_num));
        return "redirect:/Station";
    }

}
