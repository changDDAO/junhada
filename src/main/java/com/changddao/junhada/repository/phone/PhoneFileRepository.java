package com.changddao.junhada.repository.phone;

import com.changddao.junhada.entity.phone.PhoneBoard;
import com.changddao.junhada.entity.phone.PhoneFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneFileRepository extends JpaRepository<PhoneFile,Long>{
    @Query("select new com.changddao.junhada.repository.phone.PhoneFileDto(pf.id,pf.originName)" +
            "from PhoneFile pf where pf.phoneBoard=:phoneBoard")
            List <PhoneFileDto> phoneFilesAtBoard(@Param("phoneBoard") PhoneBoard phoneBoard);
}
