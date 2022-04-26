package org.bank.bankingsystem.repository;

import org.bank.bankingsystem.entity.TellerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerRepository extends CrudRepository<TellerEntity, Long> {
}
