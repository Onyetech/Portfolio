package com.mypisubproject.PiSub.Project.paystack;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mypisubproject.PiSub.Project.model.User;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PaystackPaymentResponse {

    private boolean status;
    private String message;
    private Data data;
    private User user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Data {
        private String authorization_url;
        private String access_code;
        private String reference;


        public String getAuthorization_url() {
            return authorization_url;
        }

        public void setAuthorization_url(String authorization_url) {
            this.authorization_url = authorization_url;
        }

        public String getAccess_code() {
            return access_code;
        }

        public void setAccess_code(String access_code) {
            this.access_code = access_code;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
