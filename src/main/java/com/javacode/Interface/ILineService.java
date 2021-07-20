package com.javacode.Interface;

import com.javacode.Model.Line;

import java.util.List;


public interface ILineService {
    public List<Line> getAllLines();
    public Boolean addNewLine(Integer line_num);
}