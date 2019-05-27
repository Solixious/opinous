package com.opinous.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OpBaseResponse {
    private boolean success;
    private String errorMessage;

    public OpBaseResponse() {
        this.success = true;
        this.errorMessage = "";
    }
}
