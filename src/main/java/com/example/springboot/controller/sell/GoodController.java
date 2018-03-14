package com.example.springboot.controller.sell;

import com.alibaba.fastjson.JSON;
import com.example.springboot.entity.sell.Good;
import com.example.springboot.service.sell.IGoodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodController {

    private final int pageNum = 1;
    private final int pageSize = 20;
    private final boolean isBeauty = false;

    @Resource
    private IGoodService goodService;

    @RequestMapping(value = "/good/list")
    public String findGoodsByPage(@RequestParam(required = false) Integer pn, @RequestParam(required = false) Integer ps, @RequestParam(required = false) Boolean ib) {
        pn = pn == null ? pageNum : pn;
        ps = ps == null ? pageSize : ps;
        ib = ib == null ? isBeauty : ib;
        List<Good> list = goodService.findGoodsByPage(pn, ps);
        return JSON.toJSONString(list, ib);
    }
}
