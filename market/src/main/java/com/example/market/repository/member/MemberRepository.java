package com.example.market.repository.member;


import com.example.market.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public Optional<Member> findByLoginId(String loginId);

    public Optional<Member> findByLoginIdAndPassword(String loginId, String password);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from Member m where m.id = :id")
    public Optional<Member> findMemberByIdWithPessimisticLock(Long id);
}
