package com.javacode.Reporsitory;

import com.javacode.Model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LineRepo extends JpaRepository<Line, Integer> {
}