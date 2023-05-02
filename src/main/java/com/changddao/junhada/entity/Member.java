package com.changddao.junhada.entity;

import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.entity.refrigerator.RefrigeratorBoard;
import com.changddao.junhada.entity.refrigerator.RefrigeratorReply;
import com.changddao.junhada.entity.smartphone.SmartPhoneBoard;
import com.changddao.junhada.entity.smartphone.SmartPhoneReply;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany( mappedBy = "member")
    List<RefrigeratorReply> refrigeratorReplies = new ArrayList<>();

    @OneToMany( mappedBy = "member")
    List<SmartPhoneReply> smartPhoneReplies = new ArrayList<>();
    @OneToMany( mappedBy = "member")
    List<LaptopReply> laptopReplies = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Authority> roles = new ArrayList<>();



    public Member(String email, String nickName, String password, Address address) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.address = address;
    }
    //메서드
    public void changeNickname(String name) {
        this.nickName = name;
    }

}
