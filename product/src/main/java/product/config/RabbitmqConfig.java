package product.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    //交换机的名称
    public static final String EXCHANGE_TRANSMSG="exchange.transmsg";
    //队列bean的名称
    public static final String RETURN_ROLE = "return.role";
    public static final String ROLE = "role";

    /**
     * 交换机配置使用direct类型
     * @return the exchange
     */
    @Bean(EXCHANGE_TRANSMSG)
    public Exchange EXCHANGE_TRANSMSG() {
        return ExchangeBuilder.directExchange(EXCHANGE_TRANSMSG).durable(true).build();
    }

    //声明队列
    @Bean(RETURN_ROLE)
    public Queue QUEUE_TRANSMSG() {
        return new Queue(RETURN_ROLE);
    }

    @Bean(ROLE)
    public Queue ROLE() {
        return new Queue(ROLE);
    }
    /**
     * 绑定队列到交换机
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding EXCHANGE_TRANSMSG_RETURN_ROLE(@Qualifier(RETURN_ROLE) Queue queue, @Qualifier(EXCHANGE_TRANSMSG) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("return.role").noargs();
    }

    @Bean
    public Binding EXCHANGE_TRANSMSG_ROLE(@Qualifier(ROLE) Queue queue, @Qualifier(EXCHANGE_TRANSMSG) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("role").noargs();
    }
}
