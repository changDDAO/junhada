package com.changddao.junjada.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @NotNull @Column(unique = true)
    private String email;
    @NotNull
    @Column(length = 10)
    private String nickName;
    @NotNull
    private String password;
    @NotNull
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member")//거울
    private List<RefrigeratorBoard> refrigeratorBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member")//거울
    private List<SmartPhoneBoard> smartPhoneBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member")//거울
    private List<LaptopBoard> laptopBoards = new ArrayList<>();

    @OneToOne(fetch = LAZY, mappedBy = "member")
    RefrigeratorReply laptopReply;



    public Member(String email, String nickName, String password, Address address) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.address = address;
    }

    //연관관계메서드

}
