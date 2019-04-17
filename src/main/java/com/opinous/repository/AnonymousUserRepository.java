package com.opinous.repository;

import com.opinous.model.AnonymousUser;
import com.opinous.repository.common.CustomPagingAndSortRepository;

public interface AnonymousUserRepository
    extends CustomPagingAndSortRepository<AnonymousUser, Long> {
    AnonymousUser findByName(String name);
}
