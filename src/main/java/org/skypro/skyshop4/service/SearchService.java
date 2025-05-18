package org.skypro.skyshop4.service;

import org.skypro.skyshop4.model.SearchResult;
import org.skypro.skyshop4.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final Map<UUID, Searchable> searchService;

    public SearchService(Map<UUID, Searchable> storageService) {
        this.searchService = storageService;
    }

    public List<SearchResult> search(String query) {
        Map<UUID, Searchable> collect = searchService.values().stream().filter(Objects::nonNull)
                .filter(product -> product.sortingElement().equalsIgnoreCase(query.trim())).collect(Collectors.toMap(Searchable::getId, product -> product));
        ArrayList<SearchResult> variant;
        variant = (ArrayList<SearchResult>) collect.values().stream().map(SearchResult::fromSearchable).collect(Collectors.toList());
        return variant;
    }
}