package com.yevay.remy.core.facade;

import com.yevay.remy.model.dto.form.UserLoginForm;

public interface AuthFacade {
    void login(UserLoginForm form);
}
