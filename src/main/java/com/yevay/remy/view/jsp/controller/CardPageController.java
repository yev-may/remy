package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.core.facade.CardFacade;
import com.yevay.remy.model.dto.form.CardCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/card-box/{cardBoxId}/card")
public class CardPageController {

    private final CardFacade cardFacade;

    public CardPageController(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("cardCreationForm", new CardCreationForm());
        return "page/card-create";
    }

    @PostMapping("/create")
    public String create(@PathVariable long cardBoxId, @Valid CardCreationForm cardCreationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("cardCreationForm", cardCreationForm);
            return "page/card-create";
        }
        cardCreationForm.setBoxId(cardBoxId);
        cardFacade.create(cardCreationForm);
        return "redirect:/card-box/" + cardBoxId;
    }
}
