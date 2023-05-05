package com.changddao.junhada.entity.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor
@Getter
public class PhoneFile {

    @Id @GeneratedValue
    @Column(name="phone_file_id")
    private Long id;
    private String originName;
    private String savedName;
    private String savedPath;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "phone_board_id")
    private PhoneBoard phoneBoard;

    public void setPhoneBoard(PhoneBoard phoneBoard) {
        this.phoneBoard = phoneBoard;
        phoneBoard.getPhoneFiles().add(this);
    }

    public PhoneFile(String originName, String savedName, String savedPath) {
        this.originName = originName;
        this.savedName = savedName;
        this.savedPath = savedPath;
    }
}
