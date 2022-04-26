package org.bank.bankingsystem.repository;

import org.bank.bankingsystem.entity.BankEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends CrudRepository<BankEntity, Long> {
}
