package com.changddao.junhada.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefrigeratorFile {

    @Id @GeneratedValue
    @Column(name = "refrigerator_file_id")
    private Long id;

    private String originName;
    private String savedName;
    private String savedPath;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "refrigerator_board_id")
    private RefrigeratorBoard refrigeratorBoard;

    //연관관계 메서드
    public void setBoard(RefrigeratorBoard refrigeratorBoard){
        this.refrigeratorBoard = refrigeratorBoard;
        refrigeratorBoard.getRefrigeratorFiles().add(this);
    }
    //constructor
    public RefrigeratorFile(String originName, String savedName, String savedPath) {
        this.originName = originName;
        this.savedName = savedName;
        this.savedPath = savedPath;
    }
}
