package product.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import product.amqp.sender.RabbitSender;
import product.amqp.util.RabbitMetaMessage;
import product.config.RabbitmqConfig;
import product.dao.UserDao;
import product.pojo.User;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    @Qualifier("customRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    public Boolean save(User user) {
        User save = userDao.save(user);
        if(StringUtils.isEmpty(save)){
            return false;
        }
        String data = JSONObject.toJSONString(user);
        RabbitMetaMessage rabbitMetaMessage = new RabbitMetaMessage();
        rabbitMetaMessage.setExchange(RabbitmqConfig.EXCHANGE_TRANSMSG);
        rabbitMetaMessage.setRoutingKey(RabbitmqConfig.ROLE);
        rabbitMetaMessage.setPayload(data);
        try {
            RabbitSender.send(rabbitMetaMessage,redisTemplate,rabbitTemplate,logger);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
