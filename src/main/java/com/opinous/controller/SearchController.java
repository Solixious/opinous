package com.opinous.controller;

import com.opinous.constants.AttributeName;
import com.opinous.constants.JSPMapping;
import com.opinous.constants.URLMapping;
import com.opinous.model.dto.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class SearchController {

    @GetMapping(URLMapping.SEARCH)
    public String search(@RequestParam String q, Model model) {
        System.out.println(q);
        final SearchResponse searchResponse = null;
        model.addAttribute(AttributeName.SEARCH_RESPONSE, searchResponse);
        return JSPMapping.SEARCH;
    }
}
