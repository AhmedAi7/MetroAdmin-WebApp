package com.javacode.Service;

import com.javacode.Interface.ILineService;
import com.javacode.Model.Line;
import com.javacode.Reporsitory.LineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService implements ILineService {
    @Autowired
    LineRepo lineRepo;

    @Override
    public List<Line> getAllLines() {
        return lineRepo.findAll();
    }
    @Override
    public Boolean addNewLine(Integer line_num)
    {
        Line line = new Line();
        line.setId(line_num);
        lineRepo.save(line);
        return true;
    }
}
