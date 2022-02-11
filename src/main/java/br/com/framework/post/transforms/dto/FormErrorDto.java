package br.com.framework.post.transforms.dto;

import lombok.Getter;

@Getter
public class FormErrorDto {
    private String field;
    private String message;

    public FormErrorDto(String campo, String error) {
        this.field = campo;
        this.message = error;
    }
}
