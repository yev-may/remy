package com.yevay.remy.view.jsp.controller;

import com.yevay.remy.core.facade.WorkspaceFacade;
import com.yevay.remy.model.dto.form.WorkspaceCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/workspace")
public class WorkspacePageController {

    private final WorkspaceFacade workspaceFacade;

    public WorkspacePageController(WorkspaceFacade workspaceFacade) {
        this.workspaceFacade = workspaceFacade;
    }

    @GetMapping("/all")
    public String getWorkspacesPage(Model model) {
        model.addAttribute("workspaces", workspaceFacade.getAllForCurrentUser());
        model.addAttribute("workspaceCreationForm", new WorkspaceCreationForm());
        return "page/workspaces";
    }

    @PostMapping("/create")
    public String createWorkspace(@Valid WorkspaceCreationForm workspaceCreationForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("workspaces", workspaceFacade.getAllForCurrentUser());
            model.addAttribute("workspaceCreationForm", workspaceCreationForm);
            return "page/workspaces";
        }
        workspaceFacade.create(workspaceCreationForm);
        return "redirect:/workspace/all";
    }

    @GetMapping("/{id}")
    public String getWorkspacePage(@PathVariable Long id, Model model) {
        model.addAttribute("workspace", workspaceFacade.getForCurrentUserById(id));
        return "page/workspace";
    }
}
