package org.guidowb.chores.repositories;

import org.guidowb.chores.domain.Chore;
import org.springframework.data.repository.CrudRepository;

public interface ChoreRepository extends CrudRepository<Chore, Long> {

}
