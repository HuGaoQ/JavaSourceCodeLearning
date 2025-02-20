package com.bruis.rocketmqdemo.demo04;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 延时消息消费者
 *
 * @author lhy
 * @date 2021/7/24
 */
public class ScheduledMessageConsumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(ScheduledMessageProducer.GROUP_NAME);
        // 订阅指定的topic
        consumer.subscribe(ScheduledMessageProducer.TOPIC_NAME, "*");
        consumer.setNamesrvAddr(ScheduledMessageProducer.NAMESRV_ADDRESS);
        // 注册消息监听者
        // 消费消息
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            for (MessageExt message : messages) {
                // Print approximate delay time period
                System.out.println("Receive message[msgId=" + message.getMsgId() + "] " + (System.currentTimeMillis() - message.getBornTimestamp()) + "ms later");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }

}
