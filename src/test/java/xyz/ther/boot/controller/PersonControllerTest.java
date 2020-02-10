package xyz.ther.boot.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.ther.boot.pojo.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_get_person_correctly() throws Exception {
        Person person = new Person();
        person.setSex("Man1");
        person.setClassId("82938390");
        person.setEmail("Snailclimb#qq.com");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.jsonPath("sex").value("sex 不在可选范围"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("name 不能为空"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("email 格式不正确"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}