package com.agm.issuemanagement.service.impl;

import com.agm.issuemanagement.entity.Project;
import com.agm.issuemanagement.repository.ProjectRepository;
import com.agm.issuemanagement.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        if(project.getProjectCode() == null){
            throw new IllegalArgumentException("Project code cannot be null!");
        }
        return projectRepository.save(project);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.getOne(id);
    }

    @Override
    public Page<Project> getAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public List<Project> getByProjectCode(Long id) {
        return null;
    }

    @Override
    public List<Project> getByProjectCodeContains(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Project project) {
        return null;
    }
}
