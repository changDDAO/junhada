package com.changddao.junhada.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {
    @Id @GeneratedValue
    @JsonIgnore
    private Long id;
    private String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;



    //연관관계 메서드
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getRoles().remove(this);
        }
        this.member=member;

    }

}
