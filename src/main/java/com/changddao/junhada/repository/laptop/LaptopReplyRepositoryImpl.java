package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.LaptopReply;
import com.changddao.junhada.entity.QLaptopReply;
import com.changddao.junhada.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static com.changddao.junhada.entity.QLaptopReply.*;
import static com.changddao.junhada.entity.QMember.*;

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
}
