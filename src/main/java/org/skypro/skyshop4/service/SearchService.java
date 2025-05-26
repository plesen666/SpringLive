package org.skypro.skyshop4.service;

import org.skypro.skyshop4.model.search.SearchResult;
import org.skypro.skyshop4.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService searchService;

    public SearchService(StorageService storageService) {
        this.searchService = storageService;
    }

    public List<SearchResult> search(String query) {
       return  this.searchService.getSearchableItems(){
            .stream();

        }
    }
}