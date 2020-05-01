package xyz.ther.boot.amqp;
/*
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.ther.boot.config.RabbitFanoutConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend("hello-queue", "Direct: Hello RabbitMQ!");
    }

    @Test
    public void fanoutTest() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME, null, "Fanout: Hello RabbitMQ!");
    }
}
*/