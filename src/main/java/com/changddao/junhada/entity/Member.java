package com.changddao.junhada.entity;

import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.entity.refrigerator.RefrigeratorBoard;
import com.changddao.junhada.entity.refrigerator.RefrigeratorReply;
import com.changddao.junhada.entity.smartphone.SmartPhoneBoard;
import com.changddao.junhada.entity.smartphone.SmartPhoneReply;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
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

    //처음에는 왜이렇게 구현했지 최적화에 문제있을텐데? 라고 생각했다가 Spring Security를 사용한다면 불가피한 조치
    //회원가입시 Role을 지정해주어야함 아니면 Security filter에서 걸림
    //role entity를 생성하고 연관메서드로 주입하려 했으나 실패 security때문에 어쩔 수 없는 부분이란 생각이 듬
    //그리고 최적화부분에서 따지고 보면 크게 문제가 되지도않음 왜?
    //Member가 여러 Role을 가질수 있지만, 그 Role이 많아봤자 얼마나 많겠나 걱정ㄴㄴ
    @OneToMany(mappedBy = "member", fetch = EAGER, cascade = CascadeType.ALL)
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

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(o->o.setMember(this));
    }

}
