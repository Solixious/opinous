package com.opinous.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateRoomRequest extends BaseRequest {
    private String title;
    private String description;
}
