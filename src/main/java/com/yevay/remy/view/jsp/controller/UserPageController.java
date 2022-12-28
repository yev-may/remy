package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.core.facade.UserFacade;
import com.yevay.remy.model.dto.form.UserRegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserPageController {

    private final UserFacade userFacade;

    public UserPageController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) boolean error, Model model) {
        model.addAttribute("error", error);
        return "page/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "page/registration";
    }

    @PostMapping("/registration")
    public String registerAccount(@Valid UserRegistrationForm userRegistrationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("userRegistrationForm", userRegistrationForm);
            return "page/registration";
        }
        userFacade.create(userRegistrationForm);
        return "redirect:/login";
    }
}
