package vn.edu.huce.beforeigner.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
    
    private int errorCode;

    private List<String> messages;

    public void setMessages(String... messages) {
        List<String> inputMessages = Arrays.asList(messages);
        if (this.messages == null) {
            this.messages = inputMessages;
        } else {
            this.messages.addAll(inputMessages);
        }
    }
}
