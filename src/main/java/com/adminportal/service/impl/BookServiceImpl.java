package com.adminportal.service.impl;

import com.adminportal.domain.Book;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 02.12.2017.
 */
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book, Long id) {
        if (id == null) {
            throw new NullPointerException("Book id can't be null");
        }
        Book book1 = bookRepository.getOne(id);
        if (book1 == null) {
            throw new NullPointerException("Book with id=" + id + "wasn't find");
        }
        book1.setId(id);
        bookRepository.save(book1);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (id == null) {
            throw new NullPointerException("Book id can't be null");
        }
        Book book1 = bookRepository.getOne(id);
        if (book1 == null) {
            throw new NullPointerException("Book with id=" + id + "wasn't find");
        }

        bookRepository.delete(book1);
    }
}
