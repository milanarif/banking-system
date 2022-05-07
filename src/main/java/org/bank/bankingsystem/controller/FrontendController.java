package org.bank.bankingsystem.controller;

import javax.servlet.http.HttpServletResponse;

import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.security.JwtRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontendController {

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        return "index.html";
    }

    @GetMapping("/login")
    public void login() {
    }
}