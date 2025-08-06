package com.management.controller;

import com.management.service.StringUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/string")
public class StringUtilityController {
    @Autowired
    private StringUtilityService stringUtilityService;

    @GetMapping("/permutations")
    public ResponseEntity<Set<String>> generatePermutations(@RequestParam String input) {
        Set<String> permutations = stringUtilityService.generatePermutations(input);
        return ResponseEntity.ok(permutations);
    }
}
