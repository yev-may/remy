package com.yevay.remy.core.service;

import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.domain.Workspace;

import java.util.List;

public interface WorkspaceService {
    void save(Workspace workspace);
    List<Workspace> getWorkspacesForUser(User user);
    Workspace getWorkspacesForUserById(User user, Long id);
}
