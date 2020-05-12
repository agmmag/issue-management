package com.agm.issuemanagement.service;

import com.agm.issuemanagement.dto.UserDto;
import com.agm.issuemanagement.entity.User;
import com.agm.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto save(UserDto user);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);

    Boolean delete(User user);
}
