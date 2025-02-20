package com.bruis.learnsb.initializer.controller;

import com.bruis.learnsb.initializer.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LuoHaiYang
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/testBootInitializer")
    public String test() {
        return testService.test();
    }

    @GetMapping("/testBootInitializer2")
    public String test2() {
        return testService.test2();
    }

}
