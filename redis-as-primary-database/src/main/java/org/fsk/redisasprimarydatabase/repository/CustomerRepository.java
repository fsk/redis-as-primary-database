package org.fsk.redisasprimarydatabase.repository;

import org.fsk.redisasprimarydatabase.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
