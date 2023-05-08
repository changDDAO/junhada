package com.changddao.junhada.controller.laptop;

import com.changddao.junhada.controller.MsgAlert;
import com.changddao.junhada.entity.LaptopBoardDto;
import com.changddao.junhada.repository.laptop.LaptopBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LaptopBoardController {
    private final LaptopBoardRepository laptopBoardRepository;
    @GetMapping("/laptop/board")
    public String laptopBoardsList(Model model,@PageableDefault(size=2)Pageable pageable) {
        Page<LaptopBoardDto> laptopBoards = laptopBoardRepository.laptopBoardPage(pageable);
        int startPage = Math.max(1, laptopBoards.getPageable().getPageNumber()-4);
        int endPage = Math.min(laptopBoards.getTotalPages(), laptopBoards.getPageable().getPageNumber()+4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("laptopBoards", laptopBoards);

        return "boards/laptopBoardView";
    }
    @GetMapping("/laptop/board/write")
    public String writeLaptopBoard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            model.addAttribute("data", new MsgAlert("로그인 후 이용가능합니다.", "/"));
        } else {
            return "boards/laptopBoardForm";
        }
        return "message";
    }

}
