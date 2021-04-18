package org.kevin;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private List<String> countries;

    private final DataFetcher dataFetcher;

    public Word() {
        this.countries = new ArrayList<>();
        this.dataFetcher = new DataFetcher();
    }

    public List<String> fetch(){
        List<String> fetch = dataFetcher.fetch();
        countries = fetch.isEmpty() ? countries : fetch;
        return countries;
    }
}
