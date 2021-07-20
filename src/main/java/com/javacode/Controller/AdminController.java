package com.javacode.Controller;


import com.javacode.Interface.IAdminService;
import com.javacode.Model.Admin;
import com.javacode.Reporsitory.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AdminRepo adminRepo;

    @GetMapping("/login")
    public String Getlogin()
    {
        System.out.println("TEST");
        return "login";
    }

    @RequestMapping("/AdminLogin")
    public String Postlogin(String userName, String password)
    {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName,password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "index";
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "login";
    }


    @GetMapping("/Home")
    public String goToHome()
    {
        return "index";
    }

    @GetMapping("/logout")
    public String logout()
    {
        SecurityContextHolder.clearContext();
        return "index";
    }

}
