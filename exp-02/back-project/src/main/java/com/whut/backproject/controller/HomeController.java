package com.whut.backproject.controller;

import com.github.pagehelper.PageInfo;
import com.whut.backproject.POJO.AuthorData;
import com.whut.backproject.entity.Author;
import com.whut.backproject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class HomeController {

    private final AuthorService authorService;

    @Autowired
    public HomeController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ResponseBody
    @PostMapping("/addauthor")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorData data) {
        String date = data.getDate();
        String name = data.getName();
        String province = data.getProvince();
        String city = data.getCity();
        String address = data.getAddress();
        String zip = data.getZip();
        String authToken = "success";
        try {
            if (name == null || name.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
            }
            authorService.addAuthor(date, name, province, city, address, zip);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Author already exists");
        }
        return ResponseEntity.ok(authToken);
    }

    @GetMapping("/authors")
    public ResponseEntity<PageInfo<Author>> getAuthors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int pageSize) {
        PageInfo<Author> authorsPage = authorService.getAuthors(page, pageSize);
        return ResponseEntity.ok(authorsPage);
    }

    @GetMapping("/searchauthor")
    public ResponseEntity<PageInfo<Author>> searchAuthor(@RequestParam String name) {
        PageInfo<Author> authorsPage = authorService.searchAuthors(name);
        return ResponseEntity.ok(authorsPage);
    }

    @DeleteMapping("/deleteauthor")
    public ResponseEntity<String> deleteAuthor(@RequestParam int id) {
        authorService.removeById(id);

        return ResponseEntity.ok("success");
    }
}
