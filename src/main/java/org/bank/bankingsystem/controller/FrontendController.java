package org.bank.bankingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontendController {

    @RequestMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView model;
        model = new ModelAndView("login");
        return model;
    }
}