package com.adminportal.controller;

import com.adminportal.domain.Book;
import com.adminportal.service.BookService;
import com.adminportal.service.impl.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by User on 02.12.2017.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "bookInfo";
    }

    @PostMapping("/add")
    public ModelAndView addBook(@ModelAttribute Book book, HttpServletRequest request) throws Exception {
        try {
            bookService.addBook(book);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return new ModelAndView("redirect:books", HttpStatus.OK);
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam Long id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book, HttpServletRequest request) throws Exception {
        try {
            bookService.updateBook(book);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return "redirect:book/" + book.getId();
    }

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
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


    /**
     * Exception handler
     * @param ex exception for handling
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} for error page
     * and exception message
     */
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(Exception ex) {
//        return new ModelAndView("/error", "exception", ex.getMessage());
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
