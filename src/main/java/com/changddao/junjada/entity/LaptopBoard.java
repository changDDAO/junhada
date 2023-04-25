package com.changddao.junjada.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LaptopBoard extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "laptop_board_id")
    private Long id;
    @NotNull
    private String productName;
    @NotNull
    private String title;
    @NotNull
    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = LAZY) //연관관계의 주인
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "laptopBoard")
    List<LaptopReply> laptopReplies = new ArrayList<>();


    //연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getLaptopBoards().add(this);
    }



}
