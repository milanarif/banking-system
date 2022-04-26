package org.bank.bankingsystem.auth;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.bank.bankingsystem.exception.CustomException;

@RestController
public class AuthController {

    @GetMapping
    public String homePage() {
      throw new CustomException("NEJ");
    }

    @GetMapping("userportal")
    public String userPortal() {
        return "Welcome, you are now authenticated";
    }

    @GetMapping("adminportal")
    public String adminPortal() {
        return "Welcome admin!";
    }

}
