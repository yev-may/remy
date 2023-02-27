package com.yevay.remy.view.proc;

import com.yevay.remy.model.dto.box.*;
import com.yevay.remy.model.dto.card.box.request.CreateCardBoxRequest;

public interface BoxProcessor {

    GetBoxPageResponse process(GetBoxPageRequest request);

    CreateBoxResponse process(CreateCardBoxRequest request);

    UpdateBoxResponse process(UpdateBoxRequest request);

    DeleteBoxResponse process(DeleteBoxRequest request);

}
