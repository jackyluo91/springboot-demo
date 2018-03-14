package com.example.springboot.service.cache;

import com.example.springboot.entity.sell.Good;

import java.util.List;

public interface ICacheTestService {

    List<Good> get();
    void clear();
}
