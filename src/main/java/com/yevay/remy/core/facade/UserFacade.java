package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.UserDto;
import com.yevay.remy.model.dto.form.UserRegistrationForm;

public interface UserFacade {

    UserDto register(UserRegistrationForm userRegistrationForm);

    UserDto getCurrentUserInfo();
}
