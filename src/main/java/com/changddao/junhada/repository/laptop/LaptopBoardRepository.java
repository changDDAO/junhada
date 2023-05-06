package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.laptop.LaptopBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaptopBoardRepository extends JpaRepository<LaptopBoard,Long>, LaptopBoardRepositoryCustom {
    @Query("select lb from LaptopBoard lb left join fetch lb.member " +
            "where lb.member.id = :memberId")
    public List<LaptopBoard> laptopBoardsByMember(@Param("memberId") Long id);
}
