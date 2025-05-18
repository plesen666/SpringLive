package org.skypro.skyshop4.model.search;


import java.util.UUID;

public interface Searchable {
    String getSearchTemp();

    String getContentType();

    UUID getId();

    String sortingElement();

    default String getStringRepresentation() {
        return String.format("%5s%30s%10s%30s", "Имя ", getSearchTemp(), " — тип - ", getContentType());
    }
}