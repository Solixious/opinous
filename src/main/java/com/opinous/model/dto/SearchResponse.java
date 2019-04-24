package com.opinous.model.dto;

import com.opinous.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponse {
    private List<User> users;
}
