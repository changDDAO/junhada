package com.changddao.junjada.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefrigeratorReply extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "refrigerator_reply_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "refrigerator_board_id")
    private RefrigeratorBoard refrigeratorBoard;
    @NotNull
    private String replyContent;

    //연관관계 메서드
    public void setRefrigeratorBoard(RefrigeratorBoard refrigeratorBoard) {
        this.refrigeratorBoard = refrigeratorBoard;
        refrigeratorBoard.getRefrigeratorReplies().add(this);
    }
    public void setMember(Member member){
        this.member = member;
        member.getRefrigeratorReplies().add(this);
    }
    //constructor
    public RefrigeratorReply(String replyContent) {
        this.replyContent = replyContent;
    }
}
