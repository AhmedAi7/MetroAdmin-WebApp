package com.javacode.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station {
    private Integer id;
    private String station_name;
    private double station_latitude;
    private double station_longitude;
    private Integer region;
    private List<Line> lines = new ArrayList<>();
    private List<Station> after = new ArrayList<>();
    private List<Station> previous = new ArrayList<>();

    public Station(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @ManyToMany
    @JoinTable(name = "station_line", joinColumns = { @JoinColumn(name = "station_id") },
            inverseJoinColumns = { @JoinColumn(name = "line_id") })

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    ///relation with it self
    @ManyToMany(mappedBy = "after")
    @JsonIgnore
    public List<Station> getPrevious() {
        return previous;
    }

    public void setPrevious(List<Station> links) {
        this.previous = links;
    }

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "station_link", joinColumns = { @JoinColumn(name = "station_id") },
            inverseJoinColumns = { @JoinColumn(name = "after_id") })
    public List<Station> getAfter() {
        return after;
    }

    public void setAfter(List<Station> after) {
        this.after = after;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return station_name;
    }

    public void setName(String name) {
        this.station_name = name;
    }

    public Double getLatitude() {
        return station_latitude;
    }

    public void setLatitude(double latitudue) {
        this.station_latitude = latitudue;
    }

    public Double getLongitude() {
        return station_longitude;
    }

    public void setLongitude(double longitude) {
        this.station_longitude = longitude;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }
}
