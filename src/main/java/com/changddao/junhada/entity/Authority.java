package com.changddao.junhada.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {
    @Id @GeneratedValue
    private Long id;
    private String memberRole;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //연관관계 메서드
    public void setMember(Member member) {
        this.member=member;
        member.getRoles().add(this);
    }

}
