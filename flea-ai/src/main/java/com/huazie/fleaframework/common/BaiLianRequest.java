package com.huazie.fleaframework.common;





public class BaiLianRequest {
    public static class Message {
        String role;
        String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class input{
        String prompt;

        public input(String prompt) {
            this.prompt = prompt;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }
    }

    public static class RequestBody {
        String model;
        BaiLianRequest.Message[] messages;

        Boolean stream;

        BaiLianRequest.input input;

        public RequestBody(String model, BaiLianRequest.input input) {
            this.model = model;
            this.input = input;
        }

        public RequestBody(String model, BaiLianRequest.Message[] messages, Boolean stream) {
            this.model = model;
            this.messages = messages;
            this.stream = stream;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Message[] getMessages() {
            return messages;
        }

        public void setMessages(Message[] messages) {
            this.messages = messages;
        }

        public Boolean getStream() {
            return stream;
        }

        public void setStream(Boolean stream) {
            this.stream = stream;
        }

        public BaiLianRequest.input getInput() {
            return input;
        }

        public void setInput(BaiLianRequest.input input) {
            this.input = input;
        }
    }
}
