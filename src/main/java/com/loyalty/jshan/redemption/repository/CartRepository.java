package com.loyalty.jshan.redemption.repository;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.redemption.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value="select c.* from Cart c where c.member_Id = ?1", nativeQuery = true)
    public Cart findByMemberId(Long memberId);
//    @Query("select c from Cart c where c.member = ?1")
//    public Cart findByMemberId(Member member);
}
