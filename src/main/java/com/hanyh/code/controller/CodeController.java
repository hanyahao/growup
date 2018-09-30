package com.hanyh.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CodeController {
    @RequestMapping("http.htm")
    public String http(HttpServletRequest request, HttpServletResponse response) {
        return "http";
    }


    @RequestMapping("start.htm")
    public String start(HttpServletRequest request, HttpServletResponse response) {
        return "start";
    }
}
