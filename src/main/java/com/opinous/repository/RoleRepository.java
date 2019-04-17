package com.opinous.repository;

import com.opinous.model.Role;
import com.opinous.repository.common.CustomPagingAndSortRepository;

public interface RoleRepository extends CustomPagingAndSortRepository<Role, Long> {
	Role findByName(String name);
}
