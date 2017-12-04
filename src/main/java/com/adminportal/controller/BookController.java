package com.adminportal.controller;

import com.adminportal.domain.Book;
import com.adminportal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 02.12.2017.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }

//    @GetMapping("")
//    public List<Book> getAllBooks() {
//        return bookService.getAllTopics();
//    }
//
//    @GetMapping("/{id}")
//    public Book getBook(@PathVariable Long id){
//        return bookService.getBook(id);
//    }

    @PostMapping("")
    public String addBook(@RequestBody Book book){
        bookService.addBook(book);
        return "";
    }

//    @PutMapping("/{id}")
//    public void updateBook(@RequestBody Book book, @PathVariable Long id){
//        topicService.updateBook(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable Long id){
//        bookService.deleteBook(id);
//    }


//    Almost there...
//
//    Please check your email (marina.acoustic@gmail.com)
//    to confirm your account.
//
//    If marina.acoustic@gmail.com is not your email address, please go back and enter the correct one.
//
//    If you haven't received our email in 15 minutes, please check your spam folder.
//
//    Still can't find it? Try searching Gmail for in:all subject:(Confirm your account on Heroku)

//    Welcome to Heroku
//
//    A new account for
//    marina.acoustic@gmail.com
//    is all set up.
//
//    CLICK HERE TO PROCEED
}
