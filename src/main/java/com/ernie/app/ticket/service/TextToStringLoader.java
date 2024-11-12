package com.ernie.app.ticket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TextToStringLoader {

    @Value("classpath:ticketData.txt")
    private Resource resourceFile;

    private List<String> strings = new ArrayList<>();

    public List<String> loadTextFileAsStrings() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        }
        return strings;
    }
}