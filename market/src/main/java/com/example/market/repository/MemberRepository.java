package com.example.market.repository;

import com.example.market.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public Optional<Member> findByLoginId(String loginId);

    public Optional<Member> findByLoginIdAndPassword(String loginId, String password);

}
