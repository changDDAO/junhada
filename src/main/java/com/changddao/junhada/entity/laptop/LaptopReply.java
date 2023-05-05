package com.changddao.junhada.entity.laptop;

import com.changddao.junhada.entity.BaseEntity;
import com.changddao.junhada.entity.Member;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LaptopReply extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "laptop_reply_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "laptop_board_id")
    private LaptopBoard laptopBoard;
    @NotNull
    private String replyContent;

    //연관관계 메서드
    public void setLaptopBoard(LaptopBoard laptopBoard) {
        this.laptopBoard = laptopBoard;
        laptopBoard.getLaptopReplies().add(this);
    }

    public void setMember(Member member){
        this.member = member;
        member.getLaptopReplies().add(this);
    }

    public LaptopReply(String replyContent) {
        this.replyContent = replyContent;
    }
}
