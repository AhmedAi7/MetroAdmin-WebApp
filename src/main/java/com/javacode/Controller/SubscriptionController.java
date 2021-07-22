package com.javacode.Controller;


import com.javacode.Interface.ISubscriptionService;
import com.javacode.Model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SubscriptionController {

    @Autowired
    ISubscriptionService subscriptionService;

    @RequestMapping("/Subscription")
    public String goToSubscription(Model model)
    {
        List<Subscription> subscriptions = subscriptionService.getAllSubscription();
        model.addAttribute("subscriptions", subscriptions);
        return "subscription";
    }

    @RequestMapping("/AddSubscription")
    public String goToAddSubscription()
    {
        return "addSubscription";
    }

    @RequestMapping("/AddNewSubscription")
    public String AddNewSubscription(Model model, String trips_num, String price, String months_num, String regions_num)
    {
        Subscription subscription = new Subscription();
        subscription.settrips_num(Integer.valueOf(trips_num));
        subscription.setPrice(Integer.valueOf(price));
        subscription.setmonths_num(Integer.valueOf(months_num));
        subscription.setregion_num(Integer.valueOf(regions_num));
        String message = addSubscription(subscription);
        model.addAttribute("message", message);
        return "redirect:/Subscription";
    }

    @RequestMapping("/EditSubscription/{id}")
    public String goToEditSubscription(Model model, @PathVariable(name = "id") Integer id)
    {
        System.out.println(id); 
        Subscription subscription = getSubscription(id);
        model.addAttribute("subscription", subscription);
        return "editSubscription";
    }

    @RequestMapping("/SaveSubscription")
    public String saveSubscription(Model model, @ModelAttribute("subscription") Subscription subscription)
    {
        String message = updateSubscription(subscription);
        model.addAttribute("message", message);
        return "redirect:/Subscription";
    }

    @RequestMapping("/DeleteSubscription/{id}")
    public String deleteSubscription(Model model, @PathVariable(name = "id") Integer id)
    {
        String message = deleteSubscription(id);
        model.addAttribute("message", message);
        return "redirect:/Subscription";
    }

    public Subscription getSubscription(int id)
    {
        Subscription subscription = subscriptionService.getSubscription(id);
        return subscription;
    }

    public String addSubscription(Subscription subscription)
    {
        if(subscriptionService.addSubscription(subscription))
        {
            return "Subscription Added Successfully";
        }
        return "Subscription Failed";
    }
    public String updateSubscription(Subscription subscription)
    {
        if(subscriptionService.updateSubscription(subscription))
        {
            return "Subscription Updated Successfully";
        }
        return "Subscription Failed";
    }
    public String deleteSubscription(int id)
    {
        if(subscriptionService.deleteSubscription(id))
        {
            return "Subscription Deleted Successfully";
        }
        return "Subscription Failed";
    }

}
