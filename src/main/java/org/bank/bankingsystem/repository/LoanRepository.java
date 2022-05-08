package org.bank.bankingsystem.repository;

import org.bank.bankingsystem.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<LoanEntity, Long> {
}
