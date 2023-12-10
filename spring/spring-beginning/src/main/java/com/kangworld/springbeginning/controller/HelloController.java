package com.kangworld.springbeginning.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // web application에서 /hello로 들어오면 아래 메서드를 호출해줌
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");

        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);

        return "hello-template";
    }

    // ResponseBody : http에서 header와 body가 있는데 body부에 메서드의 반환값을 직접 넣어주겠다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(name);

        return hello;
    }

    @Getter
    @AllArgsConstructor
    static class Hello{
        private String name;
    }
}
