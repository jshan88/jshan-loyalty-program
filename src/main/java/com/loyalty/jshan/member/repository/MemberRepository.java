package com.loyalty.jshan.member.repository;

import com.loyalty.jshan.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
