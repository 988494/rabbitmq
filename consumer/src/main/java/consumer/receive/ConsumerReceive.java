package consumer.receive;

import com.alibaba.fastjson.JSON;
import consumer.amqp.listener.AbstractMessageListener;
import consumer.dao.RoleDao;
import consumer.pojo.Role;
import consumer.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerReceive extends AbstractMessageListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleDao roleDao;
    @Autowired()
    @Qualifier("customRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Override
    public void receiveMessage(Message message, MessageConverter messageConverter) {
        Object bizObj = messageConverter.fromMessage(message);
        logger.info("get message success:"+bizObj.toString());
        User user = JSON.parseObject(bizObj.toString(), User.class);
        roleDao.insert(user.getUsername());
    }
}
