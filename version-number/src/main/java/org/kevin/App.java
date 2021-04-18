package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) throws BookNotFoundException,BookDuplicateException,VersionMismatchException{
        long bookId = 1;
        BookRepository bookRepository = new BookRepository();
        Book book = new Book();
        book.setId(bookId);
        bookRepository.add(book);
        log.info("An empty book with version {} was added to repository", book.getVersion());
        final Book aliceBook = bookRepository.get(bookId);
        final Book bobBook = bookRepository.get(bookId);
        aliceBook.setTitle("Kama Sutra");
        bookRepository.update(aliceBook);
        log.info("Alice updates the Book with new Version {}", aliceBook.getVersion());
        bobBook.setAuthor("Vatsyayana Mallanaga");
        try {
            log.info("Bob tries to update the book with his version {}", bobBook.getVersion());
            bookRepository.update(bobBook);
        } catch (VersionMismatchException e) {
            log.info("Exception: {}", e.getMessage());
        }
    }
}
