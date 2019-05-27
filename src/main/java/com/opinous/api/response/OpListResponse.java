package com.opinous.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OpListResponse<T> extends OpBaseResponse {
    private List<T> data;
}
