package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.LaptopBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LaptopBoardRepositoryCustom{

    public Page<LaptopBoardDto> laptopBoardPage(Pageable pageable);

}
