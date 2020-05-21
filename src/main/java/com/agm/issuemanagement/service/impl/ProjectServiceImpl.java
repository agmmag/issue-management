package com.agm.issuemanagement.service.impl;

import com.agm.issuemanagement.dto.ProjectDto;
import com.agm.issuemanagement.entity.Project;
import com.agm.issuemanagement.entity.User;
import com.agm.issuemanagement.repository.ProjectRepository;
import com.agm.issuemanagement.repository.UserRepository;
import com.agm.issuemanagement.service.ProjectService;
import com.agm.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ProjectDto save(ProjectDto project) {
        if (projectRepository.getByProjectCode(project.getProjectCode()) != null) {
            throw new IllegalArgumentException("Project code already exists!");
        }
        Project p = modelMapper.map(project, Project.class);
        User user = userRepository.getOne(project.getManagerId());
        p.setManager(user);
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
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));
        return response;
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
        if (projectDb == null)
            throw new IllegalArgumentException("Project does not exist!");

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(), id);
        if (projectCheck != null) {
            throw new IllegalArgumentException("Project code already exists!");
        }

        projectDb.setProjectCode(project.getProjectCode());
        projectDb.setProjectName(project.getProjectName());
        projectRepository.save(projectDb);
        return modelMapper.map(projectDb, ProjectDto.class);
    }

    public List<ProjectDto> getAll() {
        List<Project> data = projectRepository.findAll();
        return Arrays.asList(modelMapper.map(data, ProjectDto[].class));
    }
}
