package xyz.ther.boot.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.ther.boot.pojo.Message;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQTest {

    @Autowired
    JmsComponent jmsComponent;

    @Test
    public void contextLoads() {
        Message message = new Message();
        message.setContent("Hello jms!");
        message.setDate(new Date());
        jmsComponent.send(message);
    }
}
