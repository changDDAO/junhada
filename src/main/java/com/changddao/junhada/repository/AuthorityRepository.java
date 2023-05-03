package com.changddao.junhada.repository;

import com.changddao.junhada.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
