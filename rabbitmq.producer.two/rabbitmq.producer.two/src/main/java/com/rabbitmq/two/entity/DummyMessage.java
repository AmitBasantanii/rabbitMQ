package com.rabbitmq.two.entity;

public class DummyMessage {

    private String content;

    private int publishOrder;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPublishOrder() {
        return publishOrder;
    }

    public void setPublishOrder(int publishOrder) {
        this.publishOrder = publishOrder;
    }

    @Override
    public String toString() {
        return "DummyMessage{" +
                "content='" + content + '\'' +
                ", publishOrder=" + publishOrder +
                '}';
    }

    public DummyMessage() {

    }

    public DummyMessage(String content, int publishOrder) {
        super();
        this.content = content;
        this.publishOrder = publishOrder;
    }
}
