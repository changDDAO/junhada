package com.changddao.junhada.entity.phone;

import com.changddao.junhada.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PhoneBoard {

    @Id
    @GeneratedValue
    @Column(name = "phone_board_id")
    private Long id;
    @NotNull
    private String productName;
    @NotNull
    private int productPrice;
    @NotNull
    private String title;
    @NotNull
    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = LAZY) //연관관계의 주인
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "phoneBoard")
    List<PhoneReply> phoneReplies = new ArrayList<>();

    @OneToMany(mappedBy = "phoneBoard")
    private List<PhoneFile> phoneFiles = new ArrayList<>();


    //연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getPhoneBoards().add(this);
    }
    //constructor
    public PhoneBoard(String productName, int productPrice, String title, String content) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.title = title;
        this.content = content;
    }
}
