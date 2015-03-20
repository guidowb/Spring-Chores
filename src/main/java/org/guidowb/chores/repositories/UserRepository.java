package org.guidowb.chores.repositories;

import org.guidowb.chores.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
}
