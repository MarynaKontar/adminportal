package com.adminportal.service.impl;

import com.adminportal.domain.Book;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by User on 02.12.2017.
 */
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

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
    public void addBook(Book book) throws Exception {
        book = bookRepository.save(book);
        addBookImage(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) throws Exception {
        if (book == null) {
            throw new NullPointerException("Book can't be null");
        }
        if (book.getId() == null) {
            throw new NullPointerException("Book id can't be null");
        }
//        Book book1 = bookRepository.getOne(id);
//        book1.setId(id);
        bookRepository.save(book);
        if (!book.getBookImage().isEmpty()) {
            updateBookImage(book);
        }
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


    private void addBookImage(Book book) throws IOException {
        String name = book.getId() + ".png";
//        String realPathtoUploads = ContextLoader
//                .getCurrentWebApplicationContext() //null. Если использовать проект без spring boot, то работает
//                .getServletContext()
//                .getRealPath("");
        String realPathtoUploads = "src/main/resources";
        Path path = Paths.get( "src/main/resources/static/image/book/" + name);
        MultipartFile bookImage = book.getBookImage();

////        1-st way
//        if (!bookImage.isEmpty() && bookImage != null) {
//            try {
//                Files.delete(path);
//                byte[] bytes = bookImage.getBytes();
//                Files.write(path, bytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//                logger.info("Cannot save image for book: " + book);
//                throw new FileSystemException("Cannot save image for book: " + book);
//            }
//        }

//        2-nd way
        if (!bookImage.isEmpty() && bookImage != null) {
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                    new File(realPathtoUploads + "/static/image/book/" + name)))
            ) {
                byte[] bytes = bookImage.getBytes();
                stream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("Cannot save image for book: " + book);
                throw new FileSystemException("Cannot save image for book: " + book);
            }
        }
    }

    private void updateBookImage(Book book) throws IOException {
        String name = book.getId() + ".png";
        String realPathtoUploads = "src/main/resources";
        Path path = Paths.get( "src/main/resources/static/image/book/" + name);
        MultipartFile bookImage = book.getBookImage();

        //check if the image already exists (when update book)
        if(!bookImage.isEmpty()){
            Files.delete(path);
        }

//        2-nd way
        if (!bookImage.isEmpty() && bookImage != null) {
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                    new File(realPathtoUploads + "/static/image/book/" + name)))
            ) {
                byte[] bytes = bookImage.getBytes();
                stream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("Cannot save image for book: " + book);
                throw new FileSystemException("Cannot save image for book: " + book);
            }
        }
    }

}
