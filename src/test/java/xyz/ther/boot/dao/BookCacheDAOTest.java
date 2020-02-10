package xyz.ther.boot.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.ther.boot.pojo.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCacheDAOTest {

    @Autowired
    BookCacheDAO bookCacheDAO;

    @Test
    public void test() {
        bookCacheDAO.getBookById(1);
        bookCacheDAO.getBookById(1);
        bookCacheDAO.deleteBookById(1);
        Book book = bookCacheDAO.getBookById(1);
        System.out.println(book);
        Book newBook = new Book();
        newBook.setBookId(1);
        newBook.setName("Cache Test 书");
        newBook.setAuthor("Cache Test 作者");
        bookCacheDAO.updateBook(newBook);
        Book returnBook = bookCacheDAO.getBookById(1);
        System.out.println(returnBook);
    }
}
