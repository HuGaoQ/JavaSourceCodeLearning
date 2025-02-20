package com.bruis.learnaop.model;

import lombok.Data;

@Data
public class TestBean {
    private String testStr = "testStr";

    public void test() {
        System.out.println("testaspectJ");
    }
}
