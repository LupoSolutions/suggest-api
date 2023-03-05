package com.lupo.suggestapi.controller;

import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lupo.suggestapi.service.SuggestionService;


@RestController
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/suggest")
    public SolrDocumentList suggest(@RequestParam(value = "query",
                                                  required = true) final String query) {
        return suggestionService.findSuggestions(query);
    }
}

