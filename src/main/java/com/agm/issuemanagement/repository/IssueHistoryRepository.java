package com.agm.issuemanagement.repository;

import com.agm.issuemanagement.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long> {
}
