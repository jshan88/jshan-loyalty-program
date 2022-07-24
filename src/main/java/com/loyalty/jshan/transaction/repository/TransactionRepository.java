package com.loyalty.jshan.transaction.repository;

import com.loyalty.jshan.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //TODO : Find the dupe record with the given parameters
    // * Parameters : Flight Number, Departure Date, Departure APO, Arrival APO
    // at first thought of using COUNT(), however, this may cause the performance issue
    // as it has to look for all the records of the table to count.
    // look for the reference : https://jojoldu.tistory.com/516

    @Query(value="select t.* from Transaction t where t.order_Id = ?1", nativeQuery = true)
    Transaction findTransactionByOrderId(Long orderId);
}
