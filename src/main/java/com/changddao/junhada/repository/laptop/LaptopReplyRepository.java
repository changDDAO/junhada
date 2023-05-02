package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.laptop.LaptopReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopReplyRepository extends JpaRepository<LaptopReply,Long>, LaptopReplyRepositoryCustom {

}
