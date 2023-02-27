package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.model.dto.user.UserRegistrationRequest;
import com.yevay.remy.view.proc.UserProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/page")
@AllArgsConstructor
public class UserPageController {

    private final UserProcessor userProcessor;

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) boolean error, Model model) {
        model.addAttribute("error", error);
        return "page/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationRequest());
        return "page/registration";
    }

    @PostMapping("/registration")
    public String registerAccount(@Valid UserRegistrationRequest userRegistrationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("userRegistrationForm", userRegistrationForm);
            return "page/registration";
        }
        userProcessor.process(userRegistrationForm);
        return "redirect:/login";
    }
}
