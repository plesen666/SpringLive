package org.skypro.skyshop4.model;

import org.skypro.skyshop4.model.search.Searchable;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String nameProduct;
    private final String contentType;

    public SearchResult(UUID id, String nameProduct, String contentType) {
        this.nameProduct = nameProduct;
        this.id = id;
        this.contentType = contentType;
    }

    static public SearchResult fromSearchable(Searchable object) {
        UUID id = object.getId();
        String nameProduct = object.getSearchTemp();
        String contentType = object.getContentType();
        return new SearchResult(id, nameProduct, contentType);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}