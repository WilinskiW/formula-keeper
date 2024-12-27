package com.wilinskiw.portfolio.formula.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wilinskiw.portfolio.formula.model.LatexDictionary;

import java.util.Set;

/**
 * Reads and provides access to LaTeX dictionary definitions from a YAML file.
 */
public class LatexDictionaryReader {
    private final LatexDictionary latexDictionary;

    /**
     * Constructs a LatexDictionaryReader instance and loads the dictionary.
     */
    public LatexDictionaryReader() {
        this.latexDictionary = readDictionaryFile();
    }

    /**
     * Reads the LaTeX dictionary from the YAML file.
     *
     * @return The loaded LatexDictionary object.
     */
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


    /**
     * Retrieves all LaTeX pattern names from the dictionary.
     *
     * @return A set of LaTeX pattern names.
     */
    public Set<String> getLatexNames(){
        return latexDictionary.latexDictionary().keySet();
    }

    /**
     * Retrieves the LaTeX format for a given pattern name.
     *
     * @param latexName The name of the LaTeX pattern.
     * @return The LaTeX format string.
     */
    public String getLatexFormat(String latexName){
        return latexDictionary.latexDictionary().get(latexName).get("latexFormat");
    }

    /**
     * Retrieves the replacement value for a given LaTeX pattern name.
     *
     * @param latexName The name of the LaTeX pattern.
     * @return The replacement value string.
     */
    public String getReplacementFormat(String latexName){
        return latexDictionary.latexDictionary().get(latexName).get("value");
    }
}
