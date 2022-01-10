package com.cvc.test.transfer.dataprovider.repository;

import com.cvc.test.transfer.dataprovider.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
