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
    void addBook(Book book) throws Exception;
    void updateBook(Book book) throws Exception;
    void deleteBook(Long id);
}
