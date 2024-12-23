package com.huazie.fleaframework.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenAiApi {

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
