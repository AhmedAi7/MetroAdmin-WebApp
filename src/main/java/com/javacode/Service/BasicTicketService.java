package com.javacode.Service;

import com.javacode.Interface.IBasicTicketService;
import com.javacode.Model.BasicTicket;
import com.javacode.Reporsitory.BasicTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class BasicTicketService implements IBasicTicketService
{
    @Autowired
    BasicTicketRepo basicTicketRepo;

    @Override
    public Boolean addBasicTicket(Integer price, Integer limit)
    {
        BasicTicket basicTicket = new BasicTicket();
        basicTicket.setPrice(price);
        basicTicket.setMaximum_trips(limit);
        List<BasicTicket> basicTickets = basicTicketRepo.findAll();
        boolean isExist = false;
        for(BasicTicket b : basicTickets)
        {
            if(b.getPrice().equals(basicTicket.getPrice()) &&
                    b.getMaximum_trips().equals(basicTicket.getMaximum_trips())) {
                isExist = true;
                break;
            }
        }
        if(!isExist)
        {
            basicTicketRepo.save(basicTicket);
            return true;
        }
        return false;
    }


    @Override
    public Boolean updateBasicTicket(BasicTicket basicTicket)
    {
        BasicTicket ticket = basicTicketRepo.findById(basicTicket.getId()).get();
        ticket.setPrice(basicTicket.getPrice());
        ticket.setMaximum_trips(basicTicket.getMaximum_trips());
        basicTicketRepo.save(ticket);
        return true;
    }

    @Override
    public Boolean deleteBasicTicket(BasicTicket basicTicket) throws NoSuchElementException
    {
        basicTicketRepo.deleteById(basicTicket.getId());
        return true;
    }

    @Override
    public List<BasicTicket> selectAllBasicTicket()
    {
        return basicTicketRepo.findAll();
    }

    @Override
    public BasicTicket getBasicTicket(Integer id)
    {
        return basicTicketRepo.findById(id).get();
    }
}
