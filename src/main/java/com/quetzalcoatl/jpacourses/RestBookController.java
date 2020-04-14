package com.quetzalcoatl.jpacourses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RestBookController {
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book(1, "JPA in pictures", "John Smith"),
                new Book(2, "Book", "OAuth"),
                new Book(3, "JWT", "Ivan"));
    }

}
