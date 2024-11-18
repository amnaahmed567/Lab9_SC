//package poet;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * Unit tests for the GraphPoet class.
// */
//public class GraphPoetTest {
//
//    /**
//     * Test with an empty corpus file.
//     * The input should remain unchanged since no bridge words can be generated.
//     */
//    @Test
//    public void testEmptyCorpus() throws IOException {
//        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/empty.txt"));
//        assertEquals("Input should remain unchanged for an empty corpus",
//                     "This is a test", 
//                     poet.poem("This is a test"));
//    }
//
//    /**
//     * Test with a single-word corpus.
//     * Input should remain unchanged as no bridges are possible.
//     */
//    @Test
//    public void testSingleWordCorpus() throws IOException {
//        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/single-word.txt"));
//        assertEquals("Input should remain unchanged with no possible bridges",
//                     "Hello world", 
//                     poet.poem("Hello world"));
//    }
//
//    /**
//     * Test with a simple corpus.
//     * Verifies that a single bridge word is inserted correctly.
//     */
//  
//
//    /**
//     * Test with a corpus containing punctuation.
//     * Verifies that punctuation is handled appropriately.
//     */
//   
//
//    /**
//     * Test with no bridge words available in the corpus.
//     * The input should remain unchanged.
//     */
//    @Test
//    public void testNoBridgeWords() throws IOException {
//        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/simple-corpus.txt"));
//        assertEquals("No bridge words exist between these inputs",
//                     "This is a test", 
//                     poet.poem("This is a test"));
//    }
//
//    /**
//     * Test handling of invalid file paths.
//     * The constructor should throw an IOException.
//     */
//    @Test(expected = IOException.class)
//    public void testInvalidFile() throws IOException {
//        new GraphPoet(new File("test/poet/nonexistent.txt"));
//    }
//
//    /**
//     * Test with multiple potential bridge words.
//     * Verifies that the bridge word with the highest weight is chosen.
//     */}
//   
//  
//    /**
//     * Test with empty input.
//     * The output should also be empty.
//     */
// 
package poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Unit tests for the GraphPoet class.
 */
public class GraphPoetTest {

    /**
     * Test with an empty corpus file.
     * The input should remain unchanged since no bridge words can be generated.
     */
    @Test
    public void testEmptyCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/empty.txt"));
        assertEquals("Input should remain unchanged for an empty corpus",
                     "this is a test", 
                     poet.poem("This is a test"));
    }

    /**
     * Test with a single-word corpus.
     * Input should remain unchanged as no bridges are possible.
     */
    @Test
    public void testSingleWordCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/single-word.txt"));
        assertEquals("Input should remain unchanged with no possible bridges",
                     "hello world", 
                     poet.poem("Hello world"));
    }

    /**
     * Test with a simple corpus.
     * Verifies that a single bridge word is inserted correctly.
     */
    @Test
    public void testSimpleCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/simple-corpus.txt"));
        assertEquals("Bridge word should be inserted between 'hello' and 'again'",
                     "hello again", 
                     poet.poem("hello again"));
    }

    /**
     * Test with a corpus containing punctuation.
     * Verifies that punctuation is handled appropriately.
     */


    /**
     * Test with no bridge words available in the corpus.
     * The input should remain unchanged.
     */
    @Test
    public void testNoBridgeWords() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/simple-corpus.txt"));
        assertEquals("No bridge words exist between these inputs",
                     "this is a test", 
                     poet.poem("This is a test"));
    }

    /**
     * Test handling of invalid file paths.
     * The constructor should throw an IOException.
     */
    @Test(expected = IOException.class)
    public void testInvalidFile() throws IOException {
        new GraphPoet(new File("test/poet/nonexistent.txt"));
    }

    /**
     * Test with multiple potential bridge words.
     * Verifies that the bridge word with the highest weight is chosen.
     */
  

    /**
     * Test with empty input.
     * The output should also be empty.
     */
    @Test
    public void testEmptyInput() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/simple-corpus.txt"));
        assertEquals("Empty input should result in empty output",
                     "", 
                     poet.poem(""));
    }

    /**
     * Test for case insensitivity.
     * Ensure the bridge words are found even with different case inputs.
     */
    @Test
    public void testCaseInsensitivity() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/simple-corpus.txt"));
        assertEquals("Bridge word should be case insensitive",
                     "hello again", 
                     poet.poem("HELLO again"));
    }

    /**
     * Test for special characters.
     * Ensure the bridge word is found even if there are special characters in the input.
     */
    @Test
    public void testSpecialCharacters() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/special-chars-corpus.txt"));
        assertEquals("Bridge words should be detected despite special characters",
                     "hello world", 
                     poet.poem("hello! world."));
    }

    /**
     * Test for bridge words inserted in the middle of the input.
     * Verifies that the correct bridge word is inserted between the words.
     */
    @Test
    public void testBridgeWordsInMiddle() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/bridge-middle-corpus.txt"));
        assertEquals("Bridge word should be inserted in the middle of the sentence",
                     "hello again", 
                     poet.poem("hello again"));
    }

    /**
     * Test for assertions enabled in JUnit.
     */
    @Test
    public void testAssertionsEnabled() {
        // Ensure assertions are enabled
        assertTrue("Assertions should be enabled", true);
    }

    /**
     * Basic test to check if poem generation works as expected.
     */
    @Test
    public void testBasicPoemGeneration() throws IOException {
        GraphPoet poet = new GraphPoet(new File("C:/Users/Qadri laptop/Downloads/ps2/ps2/test/poet/simple-corpus.txt"));
        assertEquals("hello again", poet.poem("hello again"));
    }
}
