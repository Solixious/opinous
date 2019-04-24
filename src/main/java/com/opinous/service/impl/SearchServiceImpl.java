package com.opinous.service.impl;

import com.opinous.model.dto.SearchResponse;
import com.opinous.service.SearchService;
import com.opinous.service.UserService;
import com.opinous.utils.PreCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserService userService;

    @Override
    public SearchResponse search(String q) {
        PreCondition.checkNotNull(q, "q");

        final SearchResponse searchResponse = new SearchResponse();
        searchResponse.setUsers(userService.search(q));
        return searchResponse;
    }
}
