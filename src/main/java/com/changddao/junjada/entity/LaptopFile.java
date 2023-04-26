package com.changddao.junjada.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor
@Getter
public class LaptopFile {
    @Id @GeneratedValue
    @Column(name = "laptop_file_id")
    private Long id;

    private String originName;
    private String savedName;
    private String savedPath;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "laptop_board_id")
    private LaptopBoard laptopBoard;

    //연관관계 메서드
    public void setBoard(LaptopBoard laptopBoard){
        this.laptopBoard = laptopBoard;
        laptopBoard.getLaptopFiles().add(this);
    }

    public LaptopFile(String originName, String savedName, String savedPath) {
        this.originName = originName;
        this.savedName = savedName;
        this.savedPath = savedPath;
    }

}
