package com.yevay.remy.view.proc;

import com.yevay.remy.model.dto.user.UserRegistrationRequest;
import com.yevay.remy.model.dto.user.UserRegistrationResponse;

public interface UserProcessor {

    UserRegistrationResponse process(UserRegistrationRequest request);

}
