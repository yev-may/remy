package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.core.facade.CardBoxFacade;
import com.yevay.remy.model.dto.form.CardBoxCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/card-box")
public class CardBoxPageController {

    private final CardBoxFacade cardBoxFacade;

    public CardBoxPageController(CardBoxFacade cardBoxFacade) {
        this.cardBoxFacade = cardBoxFacade;
    }

    @GetMapping("/all")
    public String getCardBoxesPage(Model model) {
        model.addAttribute("cardBoxes", cardBoxFacade.getAllForCurrentUser());
        return "page/card-box/card-box-list";
    }

    @GetMapping("/create")
    public String getCreateCardNoxPage(Model model) {
        model.addAttribute("cardBoxCreationForm", new CardBoxCreationForm());
        return "page/card-box/card-box-create";
    }

    @PostMapping("/create")
    public String create(@Valid CardBoxCreationForm cardBoxCreationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("cardBoxCreationForm", cardBoxCreationForm);
            return "page/card-box/card-box-create";
        }
        cardBoxFacade.create(cardBoxCreationForm);
        return "redirect:/card-box/all";
    }

    @GetMapping("/{id}")
    public String getCardBoxPage(@PathVariable long id, Model model) {
        model.addAttribute("cardBox", cardBoxFacade.getByIdForCurrentUser(id));
        return "page/card-box/card-box";
    }
}
