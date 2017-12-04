package com.adminportal.service;

import com.adminportal.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 02.12.2017.
 */
public interface BookService {

    Book getBook(Long id);
    List<Book> getAllBooks();
    void addBook(Book book);
    void updateBook(Book book, Long id);
    void deleteBook(Long id);
}
