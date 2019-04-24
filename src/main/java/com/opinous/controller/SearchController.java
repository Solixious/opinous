package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMapping;
import com.opinous.model.dto.SearchResponse;
import com.opinous.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(URLMapping.SEARCH)
    public String search(@RequestParam String q, Model model) {
        final SearchResponse searchResponse = searchService.search(q);
        model.addAttribute(AttributeName.SEARCH_RESPONSE, searchResponse);
        return JSPMapping.SEARCH;
    }
}
