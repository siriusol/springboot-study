package xyz.ther.boot.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import xyz.ther.boot.pojo.Book;

@Repository
@CacheConfig(cacheNames = "book_cache")
public class BookCacheDAO {

    @Cacheable
    public Book getBookById(Integer id) {
        System.out.println("getBookById");
        Book book = new Book();
        book.setBookId(id);
        book.setName("Cache 书");
        book.setAuthor("Cache 作者");
        return book;
    }

    @CachePut(key = "#book.bookId")
    public Book updateBook(Book book) {
        System.out.println("updateBook");
        book.setName("Cache 书(第二版)");
        return book;
    }

    @CacheEvict(key = "#bookId")
    public void deleteBookById(Integer bookId) {
        System.out.println("deleteBookById");
    }
}
