package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.model.dto.form.CardCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/page/card-box/{boxId}/card")
public class CardPageController {

    @GetMapping("/all")
    public String getCardsPage(@PathVariable long boxId, Model model) {
        model.addAttribute("boxId", boxId);
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
        return "redirect:/card-box/" + boxId;
    }
}
