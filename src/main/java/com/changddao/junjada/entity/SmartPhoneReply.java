package com.changddao.junjada.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
public class SmartPhoneReply extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "smartphone_reply_id")
    Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "smartphone_board_id")
    private SmartPhoneBoard smartPhoneBoard;
    @NotNull
    private String replyContent;

    //연관관계 메서드
    public void setSmartPhoneBoard(SmartPhoneBoard smartPhoneBoard) {
        this.smartPhoneBoard = smartPhoneBoard;
        smartPhoneBoard.getSmartPhoneReplies().add(this);
    }
    public void setMember(Member member) {
        this.member = member;
        member.getSmartPhoneReplies().add(this);
    }

    public SmartPhoneReply(String replyContent) {
        this.replyContent = replyContent;
    }
}
