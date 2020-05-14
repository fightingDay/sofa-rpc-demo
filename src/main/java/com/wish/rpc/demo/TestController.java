package com.wish.rpc.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description: TODO
 * @Author liguo_rong
 * @Date 2020/4/20
 **/
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring boot";
    }
}
