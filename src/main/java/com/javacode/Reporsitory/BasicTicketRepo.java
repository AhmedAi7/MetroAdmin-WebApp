package com.javacode.Reporsitory;

import com.javacode.Model.BasicTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BasicTicketRepo extends JpaRepository<BasicTicket, Integer> {
}
