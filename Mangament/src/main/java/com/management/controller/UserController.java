package com.management.controller;

import com.management.model.User;
import com.management.service.UserService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.Date;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<Page> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String pinCode,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Pageable pageable) {

        if (startDate == null) startDate = new Date(0); // Unix epoch
        if (endDate == null) endDate = new Date(); // Current date

        Page users = userService.searchUsers(name, pinCode, startDate, endDate, pageable);
        return ResponseEntity.ok(users);
    }
}
