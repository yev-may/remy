package com.yevay.remy.core.facade.impl;

import com.yevay.remy.core.facade.WorkspaceFacade;
import com.yevay.remy.core.service.SessionService;
import com.yevay.remy.core.service.WorkspaceService;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.domain.Workspace;
import com.yevay.remy.model.dto.WorkspaceDto;
import com.yevay.remy.model.dto.form.WorkspaceCreationForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultWorkspaceFacade implements WorkspaceFacade {

    private final WorkspaceService workspaceService;
    private final SessionService sessionService;

    public DefaultWorkspaceFacade(WorkspaceService workspaceService, SessionService sessionService) {
        this.workspaceService = workspaceService;
        this.sessionService = sessionService;
    }

    @Override
    public List<WorkspaceDto> getAllForCurrentUser() {
        return workspaceService.getWorkspacesForUser(sessionService.getCurrentUser()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public WorkspaceDto getForCurrentUserById(long id) {
        Workspace workspace = workspaceService.getWorkspacesForUserById(sessionService.getCurrentUser(), id);
        return toDto(workspace);
    }

    private WorkspaceDto toDto(Workspace workspace) {
        return WorkspaceDto.builder()
                .id(workspace.getId())
                .title(workspace.getTitle()).build();
    }

    @Override
    public void create(WorkspaceCreationForm form) {
        create(form, sessionService.getCurrentUser());
    }
    private void create(WorkspaceCreationForm form, User user) {
        Workspace workspace = Workspace.builder()
                .title(form.getTitle())
                .owner(user).build();
        workspaceService.save(workspace);
    }
}
