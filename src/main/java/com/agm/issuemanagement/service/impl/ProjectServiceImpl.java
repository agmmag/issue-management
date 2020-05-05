package com.agm.issuemanagement.service.impl;

import com.agm.issuemanagement.dto.ProjectDto;
import com.agm.issuemanagement.entity.Project;
import com.agm.issuemanagement.repository.ProjectRepository;
import com.agm.issuemanagement.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto save(ProjectDto project) {
        if(projectRepository.getByProjectCode(project.getProjectCode()) != null){
            throw new IllegalArgumentException("Project code already exists!");
        }
        Project p = modelMapper.map(project, Project.class);
        p = projectRepository.save(p);
        project.setId(p.getId());
        return project;
    }

    @Override
    public ProjectDto getById(Long id) {
        Project p = projectRepository.getOne(id);
        return modelMapper.map(p, ProjectDto.class);
    }

    @Override
    public Page<Project> getAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public ProjectDto getByProjectCode(String id) {
        Project p = projectRepository.getByProjectCode(id);
        return modelMapper.map(p, ProjectDto.class);
    }

    @Override
    public List<Project> getByProjectCodeContains(Long id) {
        return null;
    }

    @Override
    public Boolean delete(ProjectDto project) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
        return true;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto project) {
        Project projectDb = projectRepository.getOne(id);
        if(projectDb == null)
            throw new IllegalArgumentException("Project does not exist!");

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(), id);
        if(projectCheck != null){
            throw new IllegalArgumentException("Project code already exists!");
        }

        projectDb.setProjectCode(project.getProjectCode());
        projectDb.setProjectName(project.getProjectName());
        projectRepository.save(projectDb);
        return modelMapper.map(projectDb, ProjectDto.class);
    }
}
