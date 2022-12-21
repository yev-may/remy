package com.yevay.remy.core.facade;

import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.dto.UserDto;

public interface UserFacade {
    User create(UserDto userDto);
}
