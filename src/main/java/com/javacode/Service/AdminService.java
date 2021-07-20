package com.javacode.Service;

import com.javacode.Interface.IAdminService;
import com.javacode.Model.Admin;
import com.javacode.Reporsitory.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public String login(String email, String password) {
        List<Admin> admins = adminRepo.findAll();
        for(Admin a : admins)
        {
            if(a.getEmail().equals(email) && a.getPassword().equals(password))
            {
                return a.getUsername();
            }
        }
        return null;
    }
}

