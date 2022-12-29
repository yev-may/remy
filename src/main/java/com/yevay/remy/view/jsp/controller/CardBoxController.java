package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.core.facade.CardBoxFacade;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/card")
public class CardBoxController {

    private final CardBoxFacade cardBoxFacade;

    public CardBoxController(CardBoxFacade cardBoxFacade) {
        this.cardBoxFacade = cardBoxFacade;
    }

    @PostMapping("/create")
    public String create(@Valid CardBoxCreationForm cardBoxCreationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("cardBoxCreationForm", cardBoxCreationForm);
            return "page/workspace";
        }
        cardBoxFacade.create(cardBoxCreationForm);
        return "redirect:/workspaces";
    }
}
