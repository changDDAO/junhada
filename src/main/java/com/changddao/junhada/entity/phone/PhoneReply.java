package com.changddao.junhada.entity.phone;

import com.changddao.junhada.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PhoneReply {
    @Id @GeneratedValue
    @Column(name = "phone_reply_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "phone_board_id")
    private PhoneBoard phoneBoard;

    @NotNull
    private String replyContent;

    public void setPhoneBoard(PhoneBoard phoneBoard) {
        this.phoneBoard = phoneBoard;
        phoneBoard.getPhoneReplies().add(this);
    }
    public void setMember(Member member) {
        this.member = member;
        member.getPhoneReplies().add(this);
    }

    public PhoneReply(String replyContent) {
        this.replyContent = replyContent;
    }
}
