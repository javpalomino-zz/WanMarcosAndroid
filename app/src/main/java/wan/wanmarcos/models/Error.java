package wan.wanmarcos.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javier on 26/09/15.
 */
public class Error {
    private String message;
    private String suggestion;
    private String description;
    private int code;

    @Override
    public String toString(){
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
