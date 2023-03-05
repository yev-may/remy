package com.yevay.remy.view.proc;

import com.yevay.remy.model.dto.box.*;

public interface BoxProcessor {

    GetBoxPageResponse process(GetBoxPageRequest request);

    CreateBoxResponse process(CreateBoxRequest request);

    UpdateBoxResponse process(UpdateBoxRequest request);

    DeleteBoxResponse process(DeleteBoxRequest request);

}
