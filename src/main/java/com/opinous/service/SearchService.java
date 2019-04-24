package com.opinous.service;

import com.opinous.model.dto.SearchResponse;

public interface SearchService {

    /**
     * @param q Search query
     * @return Search Response object
     */
    SearchResponse search(String q);
}
