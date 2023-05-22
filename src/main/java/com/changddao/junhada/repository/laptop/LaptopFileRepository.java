package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaptopFileRepository extends JpaRepository<LaptopFile, Long> {

    @Query("select lf.savedPath from LaptopFile lf where lf.laptopBoard=:laptopBoard")
    List<String> savedPathByBoardId(@Param("laptopBoard")LaptopBoard laptopBoard);

}
