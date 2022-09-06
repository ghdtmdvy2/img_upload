package com.ll.exam.app10.app.member.repository;

import com.ll.exam.app10.app.member.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUsername(String username);
}
