/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Word extends Thread {
    
    Conn c = new Conn();
    private String filePath;

    public Word(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            c.connect();
            countWords(filePath);
            find_LongestWord(filePath);
            find_ShortestWord( filePath);
            the_Longest(new String[]{"array", "of", "long", "words"});
            the_Shortest(new String[]{"array", "of", "short", "words"});
            countIs( filePath);
            countAre( filePath);
            countYou( filePath);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
    }
    
    public int countWords(String filePath) throws FileNotFoundException {
        c.connect();
        int wordCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // Remove punctuation and other non-alphabetic characters
                    word = word.replaceAll("[^a-zA-Z]", "");
                    if (!word.isEmpty()) {
                        wordCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return wordCount;
    }
    
    public String find_LongestWord(String filePath) throws FileNotFoundException {
        c.connect();
        String longestWord = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // Remove punctuation and other non-alphabetic characters
                    word = word.replaceAll("[^a-zA-Z]", "");
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return longestWord;
    }
    
    public String find_ShortestWord(String filePath) {
        String shortestWord = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words using whitespace as the delimiter
                for (String word : words) {
                    if (shortestWord == null || word.length() < shortestWord.length()) {
                        shortestWord = word;
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return shortestWord;
    }
    
    public String the_Longest(String[] Longs) {
        String longestWord = Longs[0];
        for (String Long : Longs) {
            if (Long.length() > longestWord.length()) {
                longestWord = Long;
            }
        }
        return longestWord;
    }
    
    public String the_Shortest(String[] Shorts) {
        String shortestWord = Shorts[0];
        for (String Short : Shorts) {
            if (Short.length() < shortestWord.length()) {
                shortestWord = Short;
            }
        }
        return shortestWord;
    }

    public int countIs(String filePath) {
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words using whitespace as the delimiter
                for (String word : words) {
                    if ("is".equals(word)) {
                        counter++;
                    }
                }
            }
            return counter;
        } catch (IOException e) {
            e.getMessage();
        }
        return counter;
    }
    
    public int countAre(String filePath) {
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words using whitespace as the delimiter
                for (String word : words) {
                    if ("are".equals(word)) {
                        counter++;
                    }
                }
            }
            return counter;
        } catch (IOException e) {
            e.getMessage();
        }
        return counter;
    }
    
    public int countYou(String filePath) {
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words using whitespace as the delimiter
                for (String word : words) {
                    if ("you".equals(word)) {
                        counter++;
                    }
                }
            }
            return counter;
        } catch (IOException e) {
            e.getMessage();
        }
        return counter;
    }

}
