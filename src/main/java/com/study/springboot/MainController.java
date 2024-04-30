package com.study.springboot;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/hi")
    public ResponseEntity main(){
        System.out.println("왜 안 되냐");
        return ResponseEntity.ok("왜 안 되냐");
    }
}
