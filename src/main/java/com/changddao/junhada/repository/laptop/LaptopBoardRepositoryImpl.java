package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.LaptopBoardDto;
import com.changddao.junhada.entity.QLaptopBoardDto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.changddao.junhada.entity.QMember.member;
import static com.changddao.junhada.entity.laptop.QLaptopBoard.laptopBoard;

public class LaptopBoardRepositoryImpl implements LaptopBoardRepositoryCustom{
    JPAQueryFactory queryFactory;
    public LaptopBoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<LaptopBoardDto> laptopBoardPage(Pageable pageable) {
        QueryResults<LaptopBoardDto> results = queryFactory.select(
                        new QLaptopBoardDto(laptopBoard.id.as("boardId"),
                                laptopBoard.title,member.nickName))
                .from(laptopBoard)
                .leftJoin(laptopBoard.member, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<LaptopBoardDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable,total);
    }
}
