package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.core.facade.CardFacade;
import com.yevay.remy.model.dto.form.CardCreationForm;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/card-box/{boxId}/card")
public class CardPageController {

    private final CardFacade cardFacade;

    public CardPageController(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    @GetMapping("/all")
    public String getCardsPage(@PathVariable long boxId, @RequestParam(defaultValue = "0") int pageNumber,
                               @RequestParam(defaultValue = "5") int pageSize, Model model) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        model.addAttribute("boxId", boxId);
        model.addAttribute("cardPage", cardFacade.getFacetsByBoxForCurrentUser(boxId, pageable));
        return "page/card-list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("cardCreationForm", new CardCreationForm());
        return "page/card-create";
    }

    @PostMapping("/create")
    public String create(@PathVariable long boxId, @Valid CardCreationForm cardCreationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("cardCreationForm", cardCreationForm);
            return "page/card-create";
        }
        cardCreationForm.setBoxId(boxId);
        cardFacade.create(cardCreationForm);
        return "redirect:/card-box/" + boxId;
    }
}
