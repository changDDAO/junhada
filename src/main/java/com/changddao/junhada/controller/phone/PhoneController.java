package com.changddao.junhada.controller.phone;

import com.changddao.junhada.controller.MsgAlert;
import com.changddao.junhada.controller.laptop.LaptopReplyContentDto;
import com.changddao.junhada.controller.member.MemberSignResponse;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.entity.phone.PhoneBoard;
import com.changddao.junhada.entity.phone.PhoneFile;
import com.changddao.junhada.entity.phone.PhoneReply;
import com.changddao.junhada.repository.phone.*;
import com.changddao.junhada.service.MemberService;
import com.changddao.junhada.service.phone.PhoneFileService;
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
@RequestMapping("/phone")
public class PhoneController {
    private final PhoneBoardRepository phoneBoardRepository;
    private final PhoneFileService phoneFileService;
    private final PhoneReplyRepository phoneReplyRepository;
    private final MemberService memberService;
    private final PhoneFileRepository phoneFileRepository;

    @GetMapping("/board")
    public String phoneBoardsList(Model model, @PageableDefault(size = 2) Pageable pageable) {
        Page<PhoneBoardDto> phoneBoards = phoneBoardRepository.phoneBoardPage(pageable);
        int startPage = Math.max(1, phoneBoards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(phoneBoards.getTotalPages(), phoneBoards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("phoneBoards", phoneBoards);

        return "boards/phoneBoardView";
    }

    @GetMapping("/board/write")
    public String writeLaptopBoard(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            model.addAttribute("phoneBoardFormDto", new PhoneBoardFormDto());
            return "boards/phoneBoardForm";
        } else {
            model.addAttribute("data", new MsgAlert("로그인 후 이용가능 합니다.", "/"));
            return "message";
        }
    }

    @PostMapping("/board/write")
    public String enrollProduct(@Valid PhoneBoardFormDto form, BindingResult result,
                                @RequestParam("phoneFiles") List<MultipartFile> phoneFiles,
                                HttpServletRequest request, Model model)
            throws IOException {
        if (result.hasErrors()) return "boards/phoneBoardForm";
        PhoneBoard board = new PhoneBoard(form.getProductName(), form.getProductPrice(),
                form.getTitle(), form.getContent());
        HttpSession session = request.getSession(false);
        MemberSignResponse user = (MemberSignResponse) session.getAttribute("user");
        Member member = memberService.findOneMember(user.getMemberId());
        board.setMember(member);
        PhoneBoard savedPhoneBoard = phoneBoardRepository.save(board);
        for (MultipartFile phoneFile : phoneFiles) {
            phoneFileService.savePhoneFile(phoneFile, savedPhoneBoard);
        }
        model.addAttribute("data", new MsgAlert("글 작성이 완료되었습니다.", "/"));
        return "message";
    }

    //view에 업로드한 이미지 출력하는 부분
    @GetMapping("/images/{phoneFileId}")
    @ResponseBody
    public Resource printImage(@PathVariable("phoneFileId") Long id, Model model) throws IOException {
        PhoneFile phoneFile = phoneFileRepository.findById(id).orElse(null);
        return new UrlResource("file:" + phoneFile.getSavedPath());
    }


    @GetMapping("/board/{id}")
    public String findByIdLaptopBoard(@PathVariable("id") Long id, Model model,
                                      @PageableDefault(size = 2) Pageable pageable) {
        PhoneBoard findBoard = phoneBoardRepository.findById(id).orElse(null);
        List<PhoneFileDto> phoneFiles = phoneFileRepository.phoneFilesAtBoard(findBoard);
        Page<PhoneReplyDto> phoneReplies = phoneReplyRepository.repliesAtBoard(findBoard, pageable);
        model.addAttribute("phoneReplyContentDto", new PhoneReplyContentDto());
        model.addAttribute("images", phoneFiles);
        model.addAttribute("phoneBoard", findBoard);
        model.addAttribute("phoneBoardReplies", phoneReplies);
        int startPage = Math.max(1, phoneReplies.getPageable().getPageNumber() - 4);
        int endPage = Math.min(phoneReplies.getTotalPages(), phoneReplies.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boards/specificPhoneBoardView";

    }

    @PostMapping("/board/reply/write")
    public String saveReply(
            @RequestParam("boardId") Long id,
            @Valid PhoneReplyContentDto phoneReplyContentDto, BindingResult result,
            Model model, @PageableDefault(size = 2)Pageable pageable,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("data", new MsgAlert("댓글을 200자 이내, 한글자이상 입력해야합니다.",
                    "/phone/board/" + String.valueOf(id)));
            return "message";
        }
        PhoneBoard findBoard = phoneBoardRepository.findById(id).orElse(null);
        PhoneReply reply = new PhoneReply(phoneReplyContentDto.getContent());
        reply.setPhoneBoard(findBoard);
        HttpSession session = request.getSession(false);
        MemberSignResponse user = (MemberSignResponse) session.getAttribute("user");
        Member findMember = memberService.findOneMember(user.getMemberId());
        reply.setMember(findMember);
        phoneReplyRepository.save(reply);

        return "redirect:/phone/board/" + id;
    }
}
