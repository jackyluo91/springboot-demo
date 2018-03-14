package com.example.springboot.mapper.sell;

import com.example.springboot.entity.sell.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodMapper {

    List<Good> selectGoodByPage(@Param("startNum") int startNum, @Param("pageSize") int pageSize);
}
