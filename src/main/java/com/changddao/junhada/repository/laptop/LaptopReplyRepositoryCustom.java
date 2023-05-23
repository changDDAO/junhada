package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.LaptopBoardDto;
import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LaptopReplyRepositoryCustom {
    public List<LaptopReply> findLaptopRepliesByMember(Long memberId);

    public List<LaptopReplyDto> RepliesAtBoard(LaptopBoard laptopBoard);





}
