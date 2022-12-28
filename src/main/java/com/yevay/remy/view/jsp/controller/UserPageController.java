package com.yevay.remy.view.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserPageController {

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) boolean error, Model model) {
        model.addAttribute("error", error);
        return "page/login";
    }
}
