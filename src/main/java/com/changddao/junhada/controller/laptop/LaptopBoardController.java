package com.changddao.junhada.controller.laptop;

import com.changddao.junhada.controller.MsgAlert;
import com.changddao.junhada.controller.member.MemberSignResponse;
import com.changddao.junhada.entity.LaptopBoardDto;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.jwt.JwtProvider;
import com.changddao.junhada.repository.laptop.LaptopBoardRepository;
import com.changddao.junhada.repository.laptop.LaptopFileRepository;
import com.changddao.junhada.repository.laptop.LaptopReplyRepository;
import com.changddao.junhada.service.MemberService;
import com.changddao.junhada.service.laptop.LaptopFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LaptopBoardController {
    private final LaptopBoardRepository laptopBoardRepository;
    private final LaptopFileService laptopFileService;
    private final LaptopReplyRepository laptopReplyRepository;
    private final MemberService memberService;
    private final LaptopFileRepository laptopFileRepository;

    @GetMapping("/laptop/board")
    public String laptopBoardsList(Model model, @PageableDefault(size = 2) Pageable pageable) {
        Page<LaptopBoardDto> laptopBoards = laptopBoardRepository.laptopBoardPage(pageable);
        int startPage = Math.max(1, laptopBoards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(laptopBoards.getTotalPages(), laptopBoards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("laptopBoards", laptopBoards);

        return "boards/laptopBoardView";
    }

    @GetMapping("/laptop/board/write")
    public String writeLaptopBoard(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            model.addAttribute("laptopBoardFormDto", new LaptopBoardFormDto());
            return "boards/laptopBoardForm";
        } else {
            model.addAttribute("data", new MsgAlert("로그인 후 이용가능 합니다.", "/"));
            return "message";
        }
    }

    @PostMapping("/laptop/board/write")
    public String enrollProduct(@Valid LaptopBoardFormDto form, BindingResult result,
                                @RequestParam("laptopFiles") List<MultipartFile> laptopFiles,
                                HttpServletRequest request, Model model)
            throws IOException {
        if (result.hasErrors()) return "boards/laptopBoardForm";
        LaptopBoard board = new LaptopBoard(form.getProductName(), form.getProductPrice(),
                form.getTitle(), form.getContent());
        HttpSession session = request.getSession(false);
        MemberSignResponse user = (MemberSignResponse) session.getAttribute("user");
        Member member = memberService.findOneMember(user.getMemberId());
        board.setMember(member);
        LaptopBoard saved = laptopBoardRepository.save(board);
        for (MultipartFile laptopFile : laptopFiles) {
            laptopFileService.saveLaptopFile(laptopFile, saved);
        }
        model.addAttribute("data", new MsgAlert("글 작성이 완료되었습니다.", "/"));
        return "message";
    }

    /*@GetMapping("/laptop/board/{id}")
    public String findByIdLaptopBoard(@PathVariable("id") Long id, Model model) {
        LaptopBoard findBoard = laptopBoardRepository.findById(id).orElse(null);
        List<String> savedPath = laptopFileRepository.savedPathByBoardId(findBoard);
        List<LaptopReply> repliesAtBoard =



    }*/
}
