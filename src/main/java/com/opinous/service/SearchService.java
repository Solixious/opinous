package com.opinous.service;

import com.opinous.model.dto.SearchResponse;
import org.springframework.stereotype.Service;

@Service
public interface SearchService {

    /**
     * @param q Search query
     * @return Search Response object
     */
    SearchResponse search(String q);
}
