package xyz.ther.boot.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.ther.boot.pojo.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    TestRestTemplate testRestTemplate;

    MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/hello")
                                      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                      .param("name", "Ther"))
                                      .andExpect(MockMvcResultMatchers.status().isOk())
                                      .andDo(MockMvcResultHandlers.print())
                                      .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void test2() throws Exception {
        Book book = new Book();
        book.setAuthor("测试作者");
        book.setName("测试书籍");
        book.setBookId(1);
        String jsonString = JSONObject.toJSONString(book);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void test3() {
        ResponseEntity<String> hello =
                testRestTemplate.getForEntity("/hello?name={0}", String.class, "Ther");
        System.out.println(hello.getBody());
    }
}
