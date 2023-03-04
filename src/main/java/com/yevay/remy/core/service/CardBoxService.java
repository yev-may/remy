package com.yevay.remy.core.service;

import com.yevay.remy.model.domain.Box;
import com.yevay.remy.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardBoxService {

    Page<Box> getByOwner(Pageable pageable, User owner);

    List<Box> getAllByOwner(User owner);

    Box getByIdAndOwner(long id, User owner);

    Box save(Box box);

    void delete(long cardBoxId);
}
