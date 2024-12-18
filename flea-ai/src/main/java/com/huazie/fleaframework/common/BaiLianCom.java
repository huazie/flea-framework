package com.huazie.fleaframework.common;

import com.huazie.fleaframework.util.Main;

public class BaiLianCom {
    public static class Message {
        String role;
        String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    public static class input{
        String prompt;

        public input(String prompt) {
            this.prompt = prompt;
        }
    }

    public static class RequestBody {
        String model;
        BaiLianCom.Message[] messages;

        Boolean stream;

        BaiLianCom.input input;

        public RequestBody(String model, BaiLianCom.input input) {
            this.model = model;
            this.input = input;
        }

        public RequestBody(String model, BaiLianCom.Message[] messages, Boolean stream) {
            this.model = model;
            this.messages = messages;
            this.stream = stream;
        }
    }
}
