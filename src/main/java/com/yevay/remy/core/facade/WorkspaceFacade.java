package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.WorkspaceDto;
import com.yevay.remy.model.dto.form.WorkspaceCreationForm;

import java.util.List;

public interface WorkspaceFacade {
    List<WorkspaceDto> getAllForCurrentUser();
    WorkspaceDto getForCurrentUserById(long id);
    void create(WorkspaceCreationForm form);
}
