package com.agm.issuemanagement.service;

import com.agm.issuemanagement.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    Project save(Project project);

    Project getById (Long id);

    Page<Project> getAllPageable(Pageable pageable);

    List<Project> getByProjectCode(Long id);

    List<Project> getByProjectCodeContains(Long id);

    Boolean delete(Project project);
}
