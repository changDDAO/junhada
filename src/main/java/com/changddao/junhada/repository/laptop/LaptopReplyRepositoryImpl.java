package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.LaptopBoardDto;
import com.changddao.junhada.entity.QLaptopBoardDto;
import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.entity.laptop.QLaptopBoard;
import com.changddao.junhada.entity.laptop.QLaptopFile;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;


import static com.changddao.junhada.entity.QMember.*;
import static com.changddao.junhada.entity.laptop.QLaptopBoard.*;
import static com.changddao.junhada.entity.laptop.QLaptopFile.*;
import static com.changddao.junhada.entity.laptop.QLaptopReply.*;

public class LaptopReplyRepositoryImpl implements LaptopReplyRepositoryCustom{
    JPAQueryFactory queryFactory;

    public LaptopReplyRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<LaptopReply> findLaptopRepliesByMember(Long memberId) {
        List<LaptopReply> result = queryFactory.selectFrom(laptopReply)
                .leftJoin(laptopReply.member, member).fetchJoin()
                .where(laptopReply.member.id.eq(memberId))
                .fetchJoin()
                .fetch();
        return result;
    }

    @Override
    public Page<LaptopReplyDto> RepliesAtBoard(LaptopBoard laptopBoard,Pageable pageable) {
        QueryResults<LaptopReplyDto> result = queryFactory.select(new QLaptopReplyDto(laptopReply.replyContent,
                        member.nickName,
                        laptopReply.createdDate,
                        laptopReply.lastModifiedDate))
                .from(laptopReply)
                .leftJoin(laptopReply.member, member)
                .where(laptopReply.laptopBoard.eq(laptopBoard))
                .fetchResults();
        List<LaptopReplyDto> content = result.getResults();
        long count = result.getTotal();
        return new PageImpl<>(content,pageable,count);
    }
}
