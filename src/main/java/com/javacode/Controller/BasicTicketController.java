package com.javacode.Controller;


import com.javacode.Interface.IBasicTicketService;
import com.javacode.Model.BasicTicket;
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
public class BasicTicketController
{
    @Autowired
    private IBasicTicketService basicTicketService;

    @RequestMapping("/AddBasicTicket")
    public String goToAddBasicTicket()
    {
        return "addTicket";
    }

    @RequestMapping("/AddNewBasicTicket")
    public String AddBasicTicket(Model model, String ticketPrice, String ticketLimit)
    {
        basicTicketService.addBasicTicket(Integer.valueOf(ticketPrice), Integer.valueOf(ticketLimit));
        model.addAttribute("message", "Basic Ticket Added Successfully");
        return "redirect:/BasicTicket";
    }
    @RequestMapping("/DeleteBasicTicket/{id}")
    public String deleteBasicTicket(Model model, @PathVariable(name = "id") Integer id)
    {
        basicTicketService.deleteBasicTicket(basicTicketService.getBasicTicket(id));
        model.addAttribute("message", "Basic Ticket Deleted Successfully");
        return "redirect:/BasicTicket";
    }

    @RequestMapping("/EditBasicTicket/{id}")
    public String goToEditBasicTicket(Model model, @PathVariable(name = "id") Integer id)
    {
        BasicTicket basicTicket = basicTicketService.getBasicTicket(id);
        model.addAttribute("basicTicket", basicTicket);
        return "editTicket";
    }

    @RequestMapping("/SaveBasicTicket")
    public String SaveBasicTicket(Model model, @ModelAttribute("basicTicket") BasicTicket basicTicket)
    {
        basicTicketService.updateBasicTicket(basicTicket);
        model.addAttribute("message", "Basic Ticket Updated Successfully");
        return "redirect:/BasicTicket";
    }

    @RequestMapping("/BasicTicket")
    public String goToBasicTicket(Model model)
    {
        List<BasicTicket> tickets = selectAllBasicTicket();
        model.addAttribute("tickets", tickets);
        return "ticket";
    }
    public List<BasicTicket>  selectAllBasicTicket()
    {
        List<BasicTicket> basicTickets = basicTicketService.selectAllBasicTicket();
        return basicTickets;
    }
}
