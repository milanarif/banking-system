package org.bank.bankingsystem.repository;

import org.bank.bankingsystem.entity.TransferEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransactionRepository extends CrudRepository<TransferEntity, Long> {

    @Query("select t from TransferEntity t where t.senderAccountId = ?1")
    ArrayList<TransferEntity> findTransferEntityBySenderAccountId(Long id);
}
