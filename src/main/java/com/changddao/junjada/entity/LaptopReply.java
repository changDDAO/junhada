package com.changddao.junjada.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LaptopReply extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "laptop_reply_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "laptop_board_id")
    private LaptopBoard laptopBoard;
    @NotNull
    private String replyContent;



}
