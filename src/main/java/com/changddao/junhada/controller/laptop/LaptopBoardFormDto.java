package com.changddao.junhada.controller.laptop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopBoardFormDto {
    private Long id;
    @NotBlank(message = "제품 이름을 입력하세요")
    private String productName;
    private int productPrice;
    @NotBlank(message = "제품 제목을 입력하세요")
    private String title;
    @NotBlank(message = "제품 설명을 입력하세요")
    @Length(message = "1000자 이내로 입력 가능합니다.")
    private String content;

}
