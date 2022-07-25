package com.loyalty.jshan.transaction.repository;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.transaction.domain.Transaction;
import com.loyalty.jshan.transaction.domain.enums.SourceType;
import com.loyalty.jshan.transaction.domain.enums.TransactionSubType;
import com.loyalty.jshan.transaction.domain.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //TODO : Find the dupe record with the given parameters
    // * Parameters : Flight Number, Departure Date, Departure APO, Arrival APO
    // at first thought of using COUNT(), however, this may cause the performance issue
    // as it has to look for all the records of the table to count.
    // look for the reference : https://jojoldu.tistory.com/516

    @Query(value="select t.* from Transaction t where t.order_Id = ?1", nativeQuery = true)
    Optional<Transaction> findTransactionByOrderId(Long orderId);

    @Query("select t from Transaction t where t.member = ?1 and t.txnType = ?2 and t.txnSubType = ?3 and t.sourceType = ?4")
    List<Transaction> findTransactionListBy(Member member, TransactionType txnType, TransactionSubType txnSubType, SourceType sourceType);
}
