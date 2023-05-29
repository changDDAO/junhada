package com.changddao.junhada.controller.laptop;

import com.changddao.junhada.controller.MsgAlert;
import com.changddao.junhada.controller.member.MemberSignResponse;
import com.changddao.junhada.entity.LaptopBoardDto;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopFile;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.repository.laptop.*;
import com.changddao.junhada.service.MemberService;
import com.changddao.junhada.service.laptop.LaptopFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/laptop")
public class LaptopBoardController {
    private final LaptopBoardRepository laptopBoardRepository;
    private final LaptopFileService laptopFileService;
    private final LaptopReplyRepository laptopReplyRepository;
    private final MemberService memberService;
    private final LaptopFileRepository laptopFileRepository;

    @GetMapping("/board")
    public String laptopBoardsList(Model model, @PageableDefault(size = 2) Pageable pageable) {
        Page<LaptopBoardDto> laptopBoards = laptopBoardRepository.laptopBoardPage(pageable);
        int startPage = Math.max(1, laptopBoards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(laptopBoards.getTotalPages(), laptopBoards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("laptopBoards", laptopBoards);

        return "boards/laptopBoardView";
    }

    @GetMapping("/board/write")
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

    @PostMapping("/board/write")
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
        LaptopBoard savedLaptopBoard = laptopBoardRepository.save(board);
        for (MultipartFile laptopFile : laptopFiles) {
            laptopFileService.saveLaptopFile(laptopFile, savedLaptopBoard);
        }
        model.addAttribute("data", new MsgAlert("글 작성이 완료되었습니다.", "/"));
        return "message";
    }

    //view에 업로드한 이미지 출력하는 부분
    @GetMapping("/images/{laptopFileId}")
    @ResponseBody
    public Resource printImage(@PathVariable("laptopFileId") Long id, Model model) throws IOException {
        LaptopFile laptopFile = laptopFileRepository.findById(id).orElse(null);
        return new UrlResource("file:" + laptopFile.getSavedPath());
    }


    @GetMapping("/board/{id}")
    public String findByIdLaptopBoard(@PathVariable("id") Long id, Model model,
                                      @PageableDefault(size = 2) Pageable pageable) {
        LaptopBoard findBoard = laptopBoardRepository.findById(id).orElse(null);
        List<LaptopFileDto> laptopFiles = laptopFileRepository.laptopFilesAtBoard(findBoard);
        Page<LaptopReplyDto> laptopReplies = laptopReplyRepository.RepliesAtBoard(findBoard, pageable);
        model.addAttribute("laptopReplyContentDto", new LaptopReplyContentDto());
        model.addAttribute("images", laptopFiles);
        model.addAttribute("laptopBoard", findBoard);
        model.addAttribute("laptopBoardReplies", laptopReplies);
        int startPage = Math.max(1, laptopReplies.getPageable().getPageNumber() - 4);
        int endPage = Math.min(laptopReplies.getTotalPages(), laptopReplies.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boards/specificLaptopBoardView";

    }

    @PostMapping("/board/reply/write")
    public String saveReply(
            @RequestParam("boardId") Long id,
            @Valid LaptopReplyContentDto laptopReplyContentDto, BindingResult result,
            Model model, @PageableDefault(size = 2)Pageable pageable,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("data", new MsgAlert("댓글을 200자 이내, 한글자이상 입력해야합니다.",
                    "/laptop/board/" + String.valueOf(id)));
            return "message";
        }
        LaptopBoard findBoard = laptopBoardRepository.findById(id).orElse(null);
        LaptopReply reply = new LaptopReply(laptopReplyContentDto.getContent());
        reply.setLaptopBoard(findBoard);
        HttpSession session = request.getSession(false);
        MemberSignResponse user = (MemberSignResponse) session.getAttribute("user");
        Member findMember = memberService.findOneMember(user.getMemberId());
        reply.setMember(findMember);
        laptopReplyRepository.save(reply);

        return "redirect:/laptop/board/" + id;
    }
}
