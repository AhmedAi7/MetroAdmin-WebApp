package com.javacode.Service;


import com.javacode.Interface.ISubscriptionService;
import com.javacode.Model.Subscription;
import com.javacode.Reporsitory.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {
    @Autowired
    SubscriptionRepo subscriptionRepo;

    @Override
    public Subscription getSubscription(int id)
    {
        List<Subscription> Subscriptions=subscriptionRepo.findAll();
        for(Subscription subscription : Subscriptions)
        {
            if(subscription.getSubscription_id()==id)
            {
                return subscription;
            }
        }
        return null;
    }

    @Override
    public boolean addSubscription(Subscription User_data)
    {
        try {
            subscriptionRepo.save(User_data);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateSubscription(Subscription subscription) {
        Subscription sub = subscriptionRepo.findById(subscription.getSubscription_id()).get();
        if(sub==null)
        {
            return false;
        }
        sub.setPrice(subscription.getPrice());
        sub.setregion_num(subscription.getregion_num());
        sub.setmonths_num(subscription.getmonths_num());
        sub.settrips_num(subscription.gettrips_num());
        subscriptionRepo.save(sub);
        return true;
    }

    @Override
    public boolean deleteSubscription(int id) {
        try {
            subscriptionRepo.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Subscription> getAllSubscription()
    {
        return subscriptionRepo.findAll();
    }
}
