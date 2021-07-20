package com.javacode.Reporsitory;

import com.javacode.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StationRepo extends JpaRepository<Station, Integer>{
    Station findByName(String name);
}
