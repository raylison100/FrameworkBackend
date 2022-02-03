package br.com.framework.post.transforms.dto;

public class FormErrorDto {
    private String field;
    private String error;

    public FormErrorDto(String campo, String error) {
        this.field = campo;
        this.error = error;
    }


    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
