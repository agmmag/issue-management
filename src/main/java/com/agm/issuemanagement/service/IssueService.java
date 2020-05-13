package com.agm.issuemanagement.service;

import com.agm.issuemanagement.dto.IssueDto;
import com.agm.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;


public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById (Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(Long issueId);

    IssueDto update(Long id, IssueDto issue);
}
