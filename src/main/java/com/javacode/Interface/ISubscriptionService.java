package com.javacode.Interface;


import com.javacode.Model.Subscription;

import java.util.List;

public interface ISubscriptionService {
    public Subscription getSubscription(int id);
    public boolean addSubscription(Subscription User_data);
    public boolean updateSubscription(Subscription subscription);
    public boolean deleteSubscription(int id);
    public List<Subscription> getAllSubscription();
}
