package com.changddao.junjada.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SmartPhoneFile {

    @Id @GeneratedValue
    @Column(name = "smartphone_file_id")
    private Long id;

    private String originName;
    private String savedName;
    private String savedPath;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "smartphone_board_id")
    private SmartPhoneBoard smartPhoneBoard;

    //연관관계 메서드
    public void setBoard(SmartPhoneBoard smartPhoneBoard){
        this.smartPhoneBoard = smartPhoneBoard;
        smartPhoneBoard.getSmartPhoneFiles().add(this);
    }

 //constructor
    public SmartPhoneFile(String originName, String savedName, String savedPath) {
        this.originName = originName;
        this.savedName = savedName;
        this.savedPath = savedPath;
    }
}
