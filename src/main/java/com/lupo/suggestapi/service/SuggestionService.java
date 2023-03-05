package com.lupo.suggestapi.service;

import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.code.ssm.api.CacheName;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughAssignCache;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.lupo.suggestapi.respository.SolrGateway;

@Service
public class SuggestionService {

    private final SolrGateway solrGateway;

    public SuggestionService(SolrGateway solrGateway) {
        this.solrGateway = solrGateway;
    }

    @ReadThroughSingleCache(namespace="SuggestApi")
    public SolrDocumentList findSuggestions(@ParameterValueKeyProvider final String query) {
        return solrGateway.fetchSuggestions(query);
    }
}
