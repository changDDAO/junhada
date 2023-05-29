package com.changddao.junhada.repository.phone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneBoardRepositoryCustom {
    Page<PhoneBoardDto> phoneBoardPage(Pageable pageable);
}
