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

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "refrigerator_board_id")
    private RefrigeratorBoard refrigeratorBoard;
    @NotNull
    private String replyContent;

}
