package com.yevay.remy.view.proc;

import com.yevay.remy.model.dto.token.TokenRequest;
import com.yevay.remy.model.dto.token.TokenResponse;

public interface TokenProcessor {

    TokenResponse process(TokenRequest request);

}
