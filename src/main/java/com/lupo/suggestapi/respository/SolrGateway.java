package com.lupo.suggestapi.respository;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

@Component
public class SolrGateway {
    private final CloudSolrClient cloudSolrClient;
    private final String Q = "q";
    private final String COLLECTION = "collection";
    private final String SUGGEST = "suggest";
    private final String WT = "wt";
    private final String JSON = "json";

    public SolrGateway(CloudSolrClient cloudSolrClient) {
        this.cloudSolrClient = cloudSolrClient;
    }

    public SolrDocumentList fetchSuggestions(final String query) {
        final QueryResponse response = executeSuggestQuery(query);
        return response.getResults();
    }

    private QueryResponse executeSuggestQuery(final String queryValue) {
        final SolrQuery query = new SolrQuery();
        query.set(Q,
                  queryValue);
        query.set(COLLECTION,
                  SUGGEST);
        query.setRequestHandler("/" + SUGGEST);
        query.set(WT,
                  JSON);
        try {
            return cloudSolrClient.query(query);
        } catch (SolrServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
