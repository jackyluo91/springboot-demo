package com.example.springboot.service.sell;

import com.example.springboot.entity.sell.Good;

import java.util.List;

public interface IGoodService {

    List<Good> findGoodsByPage(int pageNum, int pageSize);
}
