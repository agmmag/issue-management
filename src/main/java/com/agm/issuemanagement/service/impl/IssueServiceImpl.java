package com.agm.issuemanagement.service.impl;

import com.agm.issuemanagement.dto.IssueDetailDto;
import com.agm.issuemanagement.dto.IssueDto;
import com.agm.issuemanagement.dto.IssueHistoryDto;
import com.agm.issuemanagement.entity.Issue;
import com.agm.issuemanagement.repository.IssueRepository;
import com.agm.issuemanagement.service.IssueHistoryService;
import com.agm.issuemanagement.service.IssueService;
import com.agm.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueHistoryService issueHistoryService;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, IssueHistoryService issueHistoryService, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.issueHistoryService = issueHistoryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public IssueDto save(IssueDto issue) {
        if(issue.getDate() == null){
            throw new IllegalArgumentException("Issue date cannot be null");
        }
        Issue issueDb = modelMapper.map(issue , Issue.class);
        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb, IssueDto.class);
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        return modelMapper.map(issue, IssueDto.class);
    }

    public IssueDetailDto getByIdWithDetails(Long id){
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
        detailDto.setIssueHistories(issueHistoryDtos);
        return detailDto;
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage<IssueDto> page = new TPage();
        IssueDto[] dtos = modelMapper.map(data.getContent(), IssueDto[].class);
        page.setStat(data , Arrays.asList(dtos));
        return page;
    }

    @Override
    public Boolean delete(Long issueId) {
        issueRepository.deleteById(issueId);
        return true;
    }

    @Override
    public IssueDto update(Long id, IssueDto issue) {
        return null;
    }
}
