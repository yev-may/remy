package com.yevay.remy.view.proc;

import com.yevay.remy.model.dto.card.CreateCardRequest;
import com.yevay.remy.model.dto.card.CreateCardResponse;

public interface CardProcessor {

    CreateCardResponse process(CreateCardRequest request);

}
