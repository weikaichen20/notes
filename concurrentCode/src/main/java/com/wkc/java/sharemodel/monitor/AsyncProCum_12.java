package com.wkc.java.sharemodel.monitor;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
public class AsyncProCum_12 {
    /*
     * 异步模式之生产者/消费者
     *
     * 与前面的保护性暂停中的GuardObject不同，不需要产生结果和消费结果的线程一一对应
     * 消息队列可以用来平衡生产和消费的线程资源
     * 生产者仅负责产生结果数据，不关心数据该如何处理，而消费者专心处理结果数据
     * 消息队列是有容量限制的，满时不会再加入数据，空时不会再消耗数据
     * JDK中的阻塞队列，采用的就是这种模式
     * */
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                messageQueue.put(new Message(id, "message" + id));
            }, "生产者" + id).start();
        }


        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Message take = messageQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();


    }
}

//消息队列类，java线程之间通信的
@Slf4j(topic = "messageQueue")
class MessageQueue {
    //消息队列集合
    private LinkedList<Message> list = new LinkedList<>();
    //消息队列容量
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized int getSize() {
        return list.size();
    }

    //存入消息
    public void put(Message message) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    log.debug("队列已满,等待消费者消费");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("生产消息{}", message);
            list.addLast(message);
            list.notifyAll();
        }
    }

    //获取消息
    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("队列已空,等待生产者生产消息");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = list.removeFirst();
            log.debug("消费消息 {}", message);
            list.notifyAll();
            return message;
        }
    }

}

class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
