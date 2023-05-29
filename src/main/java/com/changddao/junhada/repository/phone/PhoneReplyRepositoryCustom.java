package com.changddao.junhada.repository.phone;

import com.changddao.junhada.entity.phone.PhoneBoard;
import com.changddao.junhada.entity.phone.PhoneReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneReplyRepositoryCustom {
    public List<PhoneReply> findPhoneRepliesByMember(Long memberId);

    public Page<PhoneReplyDto> repliesAtBoard(PhoneBoard phoneBoard, Pageable pageable);

}
