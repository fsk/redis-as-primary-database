package org.fsk.redisasprimarydatabase.repository;

import org.fsk.redisasprimarydatabase.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

}
