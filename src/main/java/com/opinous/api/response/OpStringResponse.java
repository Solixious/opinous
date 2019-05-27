package com.opinous.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpStringResponse extends OpBaseResponse {
    private String data;

    public static OpStringResponse generateSuccessResponse(String data) {
        final OpStringResponse response = new OpStringResponse();
        response.data = data;
        response.setSuccess(true);
        return response;
    }
}
