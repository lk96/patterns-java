package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DataFetcher {

    private final String FILE_NAME = "world.txt";

    private long lastFetched;

    public DataFetcher() {
        this.lastFetched = -1;
    }

    private boolean isDirty(long fieldLastModified){
        if (lastFetched != fieldLastModified) {
            lastFetched = fieldLastModified;
            return true;
        }
        return false;
    }

    public List<String> fetch(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        if (isDirty(file.lastModified())) {
            log.info(FILE_NAME+" is dirty! Re-fetching file content...");
            try (var br = new BufferedReader(new FileReader(file))) {
                return br.lines().collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return List.of();
    }
}
