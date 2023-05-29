package com.changddao.junhada.repository.phone;

import com.changddao.junhada.entity.QMember;
import com.changddao.junhada.entity.phone.QPhoneBoard;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.changddao.junhada.entity.QMember.*;
import static com.changddao.junhada.entity.phone.QPhoneBoard.*;

public class PhoneBoardRepositoryImpl implements PhoneBoardRepositoryCustom{
    JPAQueryFactory queryFactory;
    public PhoneBoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

@Override
    public Page<PhoneBoardDto> phoneBoardPage(Pageable pageable) {
    QueryResults<PhoneBoardDto> results = queryFactory.select(new QPhoneBoardDto(phoneBoard.id.as("boardId"),
                    phoneBoard.title, member.nickName))
            .from(phoneBoard)
            .leftJoin(phoneBoard.member, member)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
    List<PhoneBoardDto> content = results.getResults();
    long total = results.getTotal();
    return new PageImpl<>(content, pageable, total);
}

}
