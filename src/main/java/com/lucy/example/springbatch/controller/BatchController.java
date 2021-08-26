package com.lucy.example.springbatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    @GetMapping("/")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("success");
    }
}
