package com.changddao.junhada.controller.laptop;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LaptopBoardController {
    @GetMapping("/laptop/board")
    public String laptopBoardsList(Model model, Pageable pageable) {
        return "boards/laptopBoardView";
    }

}
