package com.wilinskiw.portfolio.formula.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wilinskiw.portfolio.formula.model.LatexDictionary;

import java.util.Set;

public class LatexDictionaryReader {
    private final LatexDictionary latexDictionary;

    public LatexDictionaryReader() {
        this.latexDictionary = readDictionaryFile();
    }

    public LatexDictionary readDictionaryFile() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.
                    readValue(getClass().getResource("/latex.yml"), LatexDictionary.class);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getLatexNames(){
        return latexDictionary.latexDictionary().keySet();
    }

    public String getLatexFormat(String latexName){
        return latexDictionary.latexDictionary().get(latexName).get("latexFormat");
    }

    public String getReplacementFormat(String latexName){
        return latexDictionary.latexDictionary().get(latexName).get("value");
    }
}
