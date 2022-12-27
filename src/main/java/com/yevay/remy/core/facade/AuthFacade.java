package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.UserSessionDto;
import com.yevay.remy.model.dto.form.UserLoginForm;

public interface AuthFacade {
    UserSessionDto login(UserLoginForm form);
}
