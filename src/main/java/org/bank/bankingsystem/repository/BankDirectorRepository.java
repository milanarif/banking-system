package org.bank.bankingsystem.repository;

import org.bank.bankingsystem.entity.BankDirectorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDirectorRepository extends CrudRepository<BankDirectorEntity, Long> {
}
