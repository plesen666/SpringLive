package org.skypro.skyshop4.model.article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop4.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private String titleAtribute;
    private String textAtribute;
    private final UUID id;

    public Article(UUID id, String titleAtribute, String textAtribute) {
        this.titleAtribute = titleAtribute.trim();
        this.textAtribute = textAtribute;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public String sortingElement() {
        return titleAtribute;
    }

    @Override
    public String toString() {
        return (String.format("%27s\n%35s", titleAtribute, textAtribute));
    }

    @JsonIgnore
    @Override
    public String getSearchTemp() {
        return (String.format("%27s\n%35s", titleAtribute, textAtribute));
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}