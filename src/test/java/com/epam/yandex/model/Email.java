package com.epam.yandex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Email {
        @JsonProperty("body")
        private String body;
        @JsonProperty("subject")
        private String subject;
        @JsonProperty("addressee")
        private String addressee;

        @JsonProperty("body")
        public String getBody() {
            return body;
        }

        @JsonProperty("body")
        public void setBody(String body) {
            this.body = body;
        }

        @JsonProperty("subject")
        public String getSubject() {
            return subject;
        }

        @JsonProperty("subject")
        public void setSubject(String subject) {
            this.subject = subject;
        }

        @JsonProperty("addressee")
        public String getAddressee() {
            return addressee;
        }

        @JsonProperty("addressee")
        public void setAddressee(String addressee) {
            this.addressee = addressee;
        }

}
