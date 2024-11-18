package poet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import graph.Graph;

/**
 * A graph-based poetry generator.
 * Uses a word affinity graph built from a given text corpus
 * to generate poems by inserting "bridge" words.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();

    /**
     * Create a new poet with the graph derived from the corpus.
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        List<String> lines = Files.readAllLines(corpus.toPath());
        String corpusText = String.join(" ", lines);
        
        // Preprocess the corpus text: Remove non-alphanumeric characters except spaces
        corpusText = preprocessText(corpusText);
        
        // Tokenize the corpus into words
        String[] words = tokenize(corpusText);
        
        // Build the graph
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i].toLowerCase(); // Normalize case
            String word2 = words[i + 1].toLowerCase(); // Normalize case
            
            graph.add(word1);
            graph.add(word2);
            
            // Set or update the weight of the edge between word1 and word2
            int newWeight = graph.targets(word1).getOrDefault(word2, 0) + 1;
            graph.set(word1, word2, newWeight);
        }

        // Debugging: Print the graph for validation
        System.out.println("Graph edges:");
        for (String vertex : graph.vertices()) {
            System.out.println(vertex + " -> " + graph.targets(vertex));
        }
    }
    
    /**
     * Generate a poem based on the input text by inserting bridge words.
     * 
     * @param input the input text from which to generate the poem
     * @return a poem with bridge words inserted
     */
    public String poem(String input) {
        // Preprocess input text to remove special characters and normalize case
        String sanitizedInput = preprocessText(input);
        
        // Split sanitized input into words
        String[] words = sanitizedInput.split("\\s+");
        StringBuilder poem = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            poem.append(words[i]); // Add current word to poem
            
            if (i < words.length - 1) {
                String word1 = words[i].toLowerCase(); // Lowercase for graph processing
                String word2 = words[i + 1].toLowerCase(); // Lowercase for graph processing
                
                String bridgeWord = findBridgeWord(word1, word2);
                
                if (bridgeWord != null) {
                    poem.append(" ").append(bridgeWord);
                }
            }
            
            if (i < words.length - 1) {
                poem.append(" "); // Add space between words
            }
        }
        return poem.toString();
    }
    
    private String findBridgeWord(String word1, String word2) {
        // Debug: Print connections for troubleshooting
        System.out.println("Finding bridge for: " + word1 + " -> " + word2);
        System.out.println("Targets from " + word1 + ": " + graph.targets(word1));

        // If there's already a direct edge between word1 and word2, no bridge word is needed
        if (graph.targets(word1).containsKey(word2)) {
            System.out.println("Direct edge exists, no bridge needed.");
            return null;
        }

        Map<String, Integer> targetsFromWord1 = graph.targets(word1);
        if (targetsFromWord1 == null) return null;

        int maxWeight = 0;
        String bestBridge = null;

        // Find the best bridge word that leads to word2
        for (String bridge : targetsFromWord1.keySet()) {
            Map<String, Integer> targetsFromBridge = graph.targets(bridge);
            if (targetsFromBridge != null && targetsFromBridge.containsKey(word2)) {
                int weight = targetsFromWord1.get(bridge) + targetsFromBridge.get(word2);
                if (weight > maxWeight) {
                    maxWeight = weight;
                    bestBridge = bridge;
                }
            }
        }

        // Debug: Print best bridge found (if any)
        System.out.println("Best bridge for " + word1 + " -> " + word2 + ": " + bestBridge);
        return bestBridge;
    }

    /**
     * Preprocess the input text to remove non-alphanumeric characters except spaces.
     * This helps in handling special characters.
     * 
     * @param text the input text to preprocess
     * @return the cleaned text
     */
    private String preprocessText(String text) {
        // Remove any non-alphanumeric characters (except spaces) and normalize spaces
        return text.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase().trim();
    }

    private static String[] tokenize(String text) {
        // Tokenize the cleaned text by splitting on spaces
        return text.split("\\s+");
    }
}
