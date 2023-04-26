package com.changddao.junjada.repository;

import com.changddao.junjada.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
