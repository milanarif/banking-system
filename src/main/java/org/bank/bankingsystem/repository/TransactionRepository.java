package org.bank.bankingsystem.repository;

import org.bank.bankingsystem.entity.TransferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransferEntity, Long> {
}
