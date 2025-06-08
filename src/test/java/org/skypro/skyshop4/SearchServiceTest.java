package org.skypro.skyshop4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop4.model.SearchResult;
import org.skypro.skyshop4.service.SearchService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    SearchService searchService;

    @Test
    void searchIfThereAreNoObjectsIn_StorageServis() {
        when(searchService.search("Test")).thenReturn(null);
        List<SearchResult> results = searchService.search("Test");
        assertNull(results);
    }

    @Test
    void searchIfThereAreObjectsButThereIsNoSuitableOneIn_StorageService() {
        UUID idProduct = UUID.randomUUID();
        List<SearchResult> product = List.of(new SearchResult(idProduct, "Рыба", "PRODUCT"));
        when(searchService.search("Test")).thenReturn(product);
        List<SearchResult> results = searchService.search("Test");
        assertEquals(product, results);
    }

    @Test
    void searchWhenThereIsSuitableObjectIn_StorageService() {
        UUID idProduct = UUID.randomUUID();
        List<SearchResult> product = List.of(new SearchResult(idProduct, "Test", "PRODUCT"));
        when(searchService.search("Test")).thenReturn(product);
        List<SearchResult> results = searchService.search("Test");
        assertEquals(product, results);
    }
}