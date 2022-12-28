package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.WorkspaceService;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.domain.Workspace;
import com.yevay.remy.model.repo.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultWorkspaceService implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    public DefaultWorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public void save(Workspace workspace) {
        workspaceRepository.save(workspace);
    }

    @Override
    public List<Workspace> getWorkspacesForUser(User user) {
        return workspaceRepository.findByOwner(user);
    }

    @Override
    public Workspace getWorkspacesForUserById(User user, Long id) {
        return getWorkspacesForUser(user).stream()
                .filter(workspace -> Objects.equals(workspace.getId(), id))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Workspace with id [" + id + "] not found"));
    }
}
