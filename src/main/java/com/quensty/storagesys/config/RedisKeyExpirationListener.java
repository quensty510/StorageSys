package com.quensty.storagesys.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Description： Redis键过期监听器
 * @Author： Laiwenjun
 * @Date： 2023/6/28  14:02
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        super.onMessage(message, pattern);
        if("export_message:admin".equals(message.toString())){
            System.out.println("-----------------------发送短信至admin用户------------------------");
        }
    }
}
