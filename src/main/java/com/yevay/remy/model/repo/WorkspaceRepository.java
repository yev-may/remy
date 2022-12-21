package com.yevay.remy.model.repo;

import com.yevay.remy.model.domain.Workspace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {

}
