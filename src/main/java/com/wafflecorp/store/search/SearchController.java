package com.wafflecorp.store.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);
    private final SearchSource searchSource;

    public SearchController(SearchSource searchSource) {
        this.searchSource = searchSource;
    }

    @QueryMapping
    List<Object> search(@Argument String text) {
        log.info("Searching for '" + text + "'");
        return searchSource.search(text);
    }

}
