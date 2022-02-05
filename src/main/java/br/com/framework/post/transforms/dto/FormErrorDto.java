package br.com.framework.post.transforms.dto;

public class FormErrorDto {
    private String field;
    private String message;

    public FormErrorDto(String campo, String error) {
        this.field = campo;
        this.message = error;
    }


    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
