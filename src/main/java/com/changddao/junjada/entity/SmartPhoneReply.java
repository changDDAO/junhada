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

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "smartphone_board_id")
    private SmartPhoneBoard smartPhoneBoard;
    @NotNull
    private String replyContent;
}
