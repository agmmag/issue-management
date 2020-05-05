package com.agm.issuemanagement.service;

import com.agm.issuemanagement.dto.IssueDto;
import com.agm.issuemanagement.dto.ProjectDto;
import com.agm.issuemanagement.entity.Project;
import com.agm.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto project);

    ProjectDto getById(Long id);

    TPage<ProjectDto> getAllPageable(Pageable pageable);

    ProjectDto getByProjectCode(String id);

    List<Project> getByProjectCodeContains(Long id);

    Boolean delete(ProjectDto project);

    Boolean delete(Long id);

    ProjectDto update(Long id, ProjectDto project);
}
