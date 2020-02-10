package xyz.ther.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
/*
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import xyz.ther.boot.dao.BookDAO;
 */
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.ther.boot.config.MyConfig;
import xyz.ther.boot.pojo.Book;

import java.util.*;

@RestController
@EnableConfigurationProperties
public class BookController {

    /*
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    MongoTemplate mongoTemplate;
     */
    @Autowired
    MyConfig myConfig;

    @GetMapping("/hello")
    public String hello(String name) {
        return "Hello " + name + " !";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello2";
    }

    @GetMapping("/book")
    public Book book() {
        Book book = new Book();
        book.setAuthor("中文");
        book.setName("A Book of C");
        book.setPrice(30f);
        book.setPublicationDate(new Date());
        return book;
    }

    @GetMapping("/config")
    public void config(Model model) {
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + "-->" + value);
        }
    }

    @PostMapping("/book")
    public String addBook(@RequestBody Book book) {
        return book.toString();
    }

    @DeleteMapping("/book/{id}")
    public String deleteBookById(@PathVariable Long id) {
        return String.valueOf(id);
    }

    /**
    @GetMapping("/redisTest")
    public void redisTest() {
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("name", "SpringBoot 实战");
        String name = ops1.get("name");
        System.out.println(name);
        ValueOperations ops2 = redisTemplate.opsForValue();
        Book book = new Book();
        book.setName("中文书");
        book.setAuthor("匿名");
        book.setPrice(66.66f);
        ops2.set("book", book);
        Book returnBook = (Book) ops2.get("book");
        System.out.println(book);
    }

    @GetMapping("/mongodbTest1")
    public void mongodbTest1() {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book();
        book1.setBookId(1);
        book1.setName("中文书");
        book1.setAuthor("匿名");
        bookList.add(book1);
        Book book2 = new Book();
        book2.setBookId(2);
        book2.setName("中文书 2");
        book2.setAuthor("匿名 2");
        bookList.add(book2);
        bookDAO.insert(bookList);
        List<Book> bookList1 = bookDAO.findByAuthorContains("匿名");
        System.out.println(bookList1);
        Book returnBook = bookDAO.findByNameEquals("中文书");
        System.out.println(returnBook);
    }

    @GetMapping("/mongodbTest2")
    public void mongodbTest2() {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book();
        book1.setBookId(3);
        book1.setName("英文书");
        book1.setAuthor("Unknown");
        bookList.add(book1);
        Book book2 = new Book();
        book2.setBookId(4);
        book2.setName("英文书 2");
        book2.setAuthor("Unknown 2");
        bookList.add(book2);
        mongoTemplate.insertAll(bookList);
        List<Book> bookList1 = mongoTemplate.findAll(Book.class);
        System.out.println(bookList1);
        Book returnBook = mongoTemplate.findById("5e3a51dd6bf70e5b7fadd9cf", Book.class);
        System.out.println(returnBook);
    }
     */

    @RequestMapping("/myconfig")
    public String index() {
        return "author's name is " + myConfig.getUsername() + ",ahtuor's age is " + myConfig.getAddress();
    }
}
