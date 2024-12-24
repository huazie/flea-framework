package com.huazie.fleaframework.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenAiApi {


    public static class ChatRequest {
        /**
         * 默认为 0 -2.0 到 2.0 之间的数字。正值根据文本目前的存在频率惩罚新标记,降低模型重复相同行的可能性。  有关频率和存在惩罚的更多信息。
         */
        private Double frequencyPenalty;

        /**
         * 修改指定标记出现在补全中的可能性。
         * 接受一个 JSON 对象,该对象将标记(由标记器指定的标记 ID)映射到相关的偏差值(-100 到 100)。
         * 从数学上讲,偏差在对模型进行采样之前添加到模型生成的 logit 中。
         */
        private Object logitBias;

        /**
         * 默认为 inf
         * 在聊天补全中生成的最大标记数。
         */
        private Long maxTokens;

        /**
         * 至今为止对话所包含的消息列表。
         */
        private List<Message> messages;

        /**
         * 要使用的模型的 ID。
         */
        private String model;

        /**
         * 默认为 1
         * 为每个输入消息生成多少个聊天补全选择。
         */
        private Long n;

        /**
         * -2.0 和 2.0 之间的数字。正值会根据到目前为止是否出现在文本中来惩罚新标记，从而增加模型谈论新主题的可能性。
         */
        private Double presencePenalty;

        /**
         * 指定模型必须输出的格式的对象。
         */
        private Map<String, Object> responseFormat;

        /**
         * 此功能处于测试阶段。如果指定,我们的系统将尽最大努力确定性地进行采样。
         */
        private Long seen;

        /**
         * 默认为 null 最多 4 个序列,API 将停止进一步生成标记。
         */
        private String stop;

        /**
         * 默认为 false 如果设置,则像在 ChatGPT 中一样会发送部分消息增量。
         */
        private Boolean stream;

        /**
         * 使用什么采样温度，介于 0 和 2 之间。较高的值（如 0.8）将使输出更加随机，而较低的值（如 0.2）将使输出更加集中和确定。
         */
        private Long temperature;

        /**
         * 控制模型调用哪个函数(如果有的话)。
         */
        private Map<String, Object> toolChoice;

        /**
         * 模型可以调用的一组工具列表。
         */
        private List<String> tools;

        /**
         * 一种替代温度采样的方法，称为核采样，其中模型考虑具有 top_p 概率质量的标记的结果。
         */
        private Long topP;

        /**
         * 代表您的最终用户的唯一标识符，可以帮助 OpenAI 监控和检测滥用行为。
         */
        private String user;

        // Private constructor to enforce the use of the builder
        private ChatRequest(Builder builder) {
            this.frequencyPenalty = builder.frequencyPenalty;
            this.logitBias = builder.logitBias;
            this.maxTokens = builder.maxTokens;
            this.messages = builder.messages;
            this.model = builder.model;
            this.n = builder.n;
            this.presencePenalty = builder.presencePenalty;
            this.responseFormat = builder.responseFormat;
            this.seen = builder.seen;
            this.stop = builder.stop;
            this.stream = builder.stream;
            this.temperature = builder.temperature;
            this.toolChoice = builder.toolChoice;
            this.tools = builder.tools;
            this.topP = builder.topP;
            this.user = builder.user;
        }

        public Builder convertBuilder() {
            return new Builder()
                    .model(this.model)
                    .messages(this.messages)
                    .frequencyPenalty(this.frequencyPenalty)
                    .logitBias(this.logitBias)
                    .maxTokens(this.maxTokens)
                    .n(this.n)
                    .presencePenalty(this.presencePenalty)
                    .responseFormat(this.responseFormat)
                    .seen(this.seen)
                    .stop(this.stop)
                    .stream(this.stream)
                    .temperature(this.temperature)
                    .toolChoice(this.toolChoice)
                    .tools(this.tools)
                    .topP(this.topP)
                    .user(this.user);
        }

        // Static inner Builder class
        public static class Builder {
            private Double frequencyPenalty;
            private Object logitBias;
            private Long maxTokens;
            private List<Message> messages;
            private String model;
            private Long n;
            private Double presencePenalty;
            private Map<String, Object> responseFormat;
            private Long seen;
            private String stop;
            private Boolean stream = false;//默认false
            private Long temperature;
            private Map<String, Object> toolChoice;
            private List<String> tools;
            private Long topP;
            private String user;

            public Builder() {
            }


            public Builder model(String model) {
                this.model = model;
                return this;
            }

            public Builder message(Message message) {
                List<Message> messages = new ArrayList<>();
                messages.add(message);
                this.messages = messages;
                return this;
            }

            public Builder messages(List<Message> messages) {
                this.messages = messages;
                return this;
            }

            public Builder frequencyPenalty(Double frequencyPenalty) {
                this.frequencyPenalty = frequencyPenalty;
                return this;
            }

            public Builder logitBias(Object logitBias) {
                this.logitBias = logitBias;
                return this;
            }

            public Builder maxTokens(Long maxTokens) {
                this.maxTokens = maxTokens;
                return this;
            }

            public Builder n(Long n) {
                this.n = n;
                return this;
            }

            public Builder presencePenalty(Double presencePenalty) {
                this.presencePenalty = presencePenalty;
                return this;
            }

            public Builder responseFormat(Map<String, Object> responseFormat) {
                this.responseFormat = responseFormat;
                return this;
            }

            public Builder seen(Long seen) {
                this.seen = seen;
                return this;
            }

            public Builder stop(String stop) {
                this.stop = stop;
                return this;
            }

            public Builder stream(Boolean stream) {
                this.stream = stream;
                return this;
            }

            public Builder temperature(Long temperature) {
                this.temperature = temperature;
                return this;
            }

            public Builder toolChoice(Map<String, Object> toolChoice) {
                this.toolChoice = toolChoice;
                return this;
            }

            public Builder tools(List<String> tools) {
                this.tools = tools;
                return this;
            }

            public Builder topP(Long topP) {
                this.topP = topP;
                return this;
            }

            public Builder user(String user) {
                this.user = user;
                return this;
            }

            // Method to build the final ChatRequest object
            public ChatRequest build() {
                return new ChatRequest(this);
            }
        }


        public static class Message {
            /**
             * 消息的内容。
             */
            private String content;

            /**
             * 消息的角色，如 "user" 或 "assistant"。
             */
            private String role;

            // Private constructor
            private Message(Builder builder) {
                this.content = builder.content;
                this.role = builder.role;
            }

            // Static inner Builder class
            public static class Builder {
                private String content;
                private String role;

                // Builder constructor with required fields
                public Builder(String role, String content) {
                    this.role = role;
                    this.content = content;
                }

                // Method to build the final Message object
                public Message build() {
                    return new Message(this);
                }
            }

            // Getters
            public String getContent() {
                return content;
            }

            public String getRole() {
                return role;
            }
        }


        // Getters
        public Double getFrequencyPenalty() {
            return frequencyPenalty;
        }

        public Object getLogitBias() {
            return logitBias;
        }

        public Long getMaxTokens() {
            return maxTokens;
        }

        public List<Message> getMessages() {
            return messages;
        }

        public String getModel() {
            return model;
        }

        public Long getN() {
            return n;
        }

        public Double getPresencePenalty() {
            return presencePenalty;
        }

        public Map<String, Object> getResponseFormat() {
            return responseFormat;
        }

        public Long getSeen() {
            return seen;
        }

        public String getStop() {
            return stop;
        }

        public Boolean getStream() {
            return stream;
        }

        public Long getTemperature() {
            return temperature;
        }

        public Map<String, Object> getToolChoice() {
            return toolChoice;
        }

        public List<String> getTools() {
            return tools;
        }

        public Long getTopP() {
            return topP;
        }

        public String getUser() {
            return user;
        }
    }


    public static class ChatResponse {

        @JsonProperty("id")
        private String id;

        @JsonProperty("choices")
        private List<Choice> choices;

        @JsonProperty("created")
        private Long created;

        @JsonProperty("model")
        private String model;

        @JsonProperty("object")
        private String object;

        @JsonProperty("service_tier")
        private String serviceTier;

        @JsonProperty("system_fingerprint")
        private String systemFingerprint;

        @JsonProperty("usage")
        private Usage usage;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Choice> getChoices() {
            return choices;
        }

        public void setChoices(List<Choice> choices) {
            this.choices = choices;
        }

        public Long getCreated() {
            return created;
        }

        public void setCreated(Long created) {
            this.created = created;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getServiceTier() {
            return serviceTier;
        }

        public void setServiceTier(String serviceTier) {
            this.serviceTier = serviceTier;
        }

        public String getSystemFingerprint() {
            return systemFingerprint;
        }

        public void setSystemFingerprint(String systemFingerprint) {
            this.systemFingerprint = systemFingerprint;
        }

        public Usage getUsage() {
            return usage;
        }

        public void setUsage(Usage usage) {
            this.usage = usage;
        }

        @Override
        public String toString() {
            return "ChatResponse{" +
                    "id='" + id + '\'' +
                    ", choices=" + choices +
                    ", created=" + created +
                    ", model='" + model + '\'' +
                    ", object='" + object + '\'' +
                    ", serviceTier='" + serviceTier + '\'' +
                    ", systemFingerprint='" + systemFingerprint + '\'' +
                    ", usage=" + usage +
                    '}';
        }

        // Nested classes for Choice, ToolCall, Function, Usage, etc.

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Choice {

            @JsonProperty("delta")
            private Delta delta;

            @JsonProperty("finish_reason")
            private String finishReason;

            @JsonProperty("index")
            private Integer index;

            @JsonProperty("logprobs")
            private String logprobs;

            // Getters and Setters
            public Delta getDelta() {
                return delta;
            }

            public void setDelta(Delta delta) {
                this.delta = delta;
            }

            public String getFinishReason() {
                return finishReason;
            }

            public void setFinishReason(String finishReason) {
                this.finishReason = finishReason;
            }

            public Integer getIndex() {
                return index;
            }

            public void setIndex(Integer index) {
                this.index = index;
            }

            public String getLogprobs() {
                return logprobs;
            }

            public void setLogprobs(String logprobs) {
                this.logprobs = logprobs;
            }

            @Override
            public String toString() {
                return "Choice{" +
                        "delta=" + delta +
                        ", finishReason='" + finishReason + '\'' +
                        ", index=" + index +
                        ", logprobs='" + logprobs + '\'' +
                        '}';
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Delta {

            @JsonProperty("content")
            private String content;

            @JsonProperty("function_call")
            private Object functionCall;

            @JsonProperty("refusal")
            private Object refusal;

            @JsonProperty("role")
            private String role;

            @JsonProperty("tool_calls")
            private List<ToolCall> toolCalls;

            // Getters and Setters
            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getFunctionCall() {
                return functionCall;
            }

            public void setFunctionCall(Object functionCall) {
                this.functionCall = functionCall;
            }

            public Object getRefusal() {
                return refusal;
            }

            public void setRefusal(Object refusal) {
                this.refusal = refusal;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public List<ToolCall> getToolCalls() {
                return toolCalls;
            }

            public void setToolCalls(List<ToolCall> toolCalls) {
                this.toolCalls = toolCalls;
            }

            @Override
            public String toString() {
                return "Delta{" +
                        "content='" + content + '\'' +
                        ", functionCall=" + functionCall +
                        ", refusal=" + refusal +
                        ", role='" + role + '\'' +
                        ", toolCalls=" + toolCalls +
                        '}';
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class ToolCall {

            @JsonProperty("index")
            private String index;

            @JsonProperty("id")
            private String id;

            @JsonProperty("function")
            private Function function;

            @JsonProperty("type")
            private String type;

            // Getters and Setters
            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Function getFunction() {
                return function;
            }

            public void setFunction(Function function) {
                this.function = function;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "ToolCall{" +
                        "index='" + index + '\'' +
                        ", id='" + id + '\'' +
                        ", function=" + function +
                        ", type='" + type + '\'' +
                        '}';
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Function {

            @JsonProperty("name")
            private String name;

            @JsonProperty("arguments")
            private String arguments;

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getArguments() {
                return arguments;
            }

            public void setArguments(String arguments) {
                this.arguments = arguments;
            }

            @Override
            public String toString() {
                return "Function{" +
                        "name='" + name + '\'' +
                        ", arguments='" + arguments + '\'' +
                        '}';
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Usage {

            @JsonProperty("prompt_tokens")
            private String promptTokens;

            @JsonProperty("completion_tokens")
            private String completionTokens;

            @JsonProperty("total_tokens")
            private String totalTokens;

            // Getters and Setters
            public String getPromptTokens() {
                return promptTokens;
            }

            public void setPromptTokens(String promptTokens) {
                this.promptTokens = promptTokens;
            }

            public String getCompletionTokens() {
                return completionTokens;
            }

            public void setCompletionTokens(String completionTokens) {
                this.completionTokens = completionTokens;
            }

            public String getTotalTokens() {
                return totalTokens;
            }

            public void setTotalTokens(String totalTokens) {
                this.totalTokens = totalTokens;
            }

            @Override
            public String toString() {
                return "Usage{" +
                        "promptTokens='" + promptTokens + '\'' +
                        ", completionTokens='" + completionTokens + '\'' +
                        ", totalTokens='" + totalTokens + '\'' +
                        '}';
            }
        }
    }
}
