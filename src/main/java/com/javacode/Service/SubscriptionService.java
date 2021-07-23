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
    public boolean addSubscription(Subscription subscription)
    {
        try {
            List<Subscription> subscriptions = subscriptionRepo.findAll();
            boolean isExist = false;
            for(Subscription sub : subscriptions)
            {
                if(subscription.gettrips_num() == sub.gettrips_num() && subscription.getPrice() == sub.getPrice()
                && subscription.getregion_num() == sub.getregion_num() && subscription.getmonths_num() == sub.getmonths_num())
                {
                    isExist = true;
                    break;
                }
            }
            if (!isExist)
            {
                subscriptionRepo.save(subscription);
                return true;
            }
            return false;
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
        try {
            subscriptionRepo.save(sub);
            return true;
        }
        catch (Exception e){
            return false;
        }
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
