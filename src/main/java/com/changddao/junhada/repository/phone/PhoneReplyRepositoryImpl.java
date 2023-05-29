package com.changddao.junhada.repository.phone;

import com.changddao.junhada.entity.QMember;
import com.changddao.junhada.entity.phone.PhoneBoard;
import com.changddao.junhada.entity.phone.PhoneReply;
import com.changddao.junhada.entity.phone.QPhoneReply;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.changddao.junhada.entity.QMember.*;
import static com.changddao.junhada.entity.phone.QPhoneReply.*;

public class PhoneReplyRepositoryImpl implements PhoneReplyRepositoryCustom{
    JPAQueryFactory queryFactory;

    public PhoneReplyRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<PhoneReply> findPhoneRepliesByMember(Long memberId) {
        List<PhoneReply> result = queryFactory.selectFrom(phoneReply)
                .leftJoin(phoneReply.member, member).fetchJoin()
                .where(phoneReply.member.id.eq(memberId))
                .fetch();
        return result;
    }

    @Override
    public Page<PhoneReplyDto> repliesAtBoard(PhoneBoard phoneBoard, Pageable pageable) {
        QueryResults<PhoneReplyDto> results = queryFactory.select(new QPhoneReplyDto(phoneReply.replyContent,
                        member.nickName, phoneReply.member.createdDate, phoneReply.member.lastModifiedDate))
                .from(phoneReply)
                .where(phoneReply.phoneBoard.eq(phoneBoard))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<PhoneReplyDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
