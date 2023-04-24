package com.changddao.junjada.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class RefrigeratorBoard extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "refrigerator_board_id")
    private Long id;
    @NotNull
    private String title;
    @NotNull
    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = LAZY) //연관관계의 주인
    @JoinColumn(name = "member_id")
    private Member member;

    //연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getRefrigeratorBoards().add(this);
    }



}
