package org.fsk.redisasprimarydatabase.repository;

import org.fsk.redisasprimarydatabase.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

}
