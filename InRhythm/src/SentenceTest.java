import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SentenceTest {

	@Test
	void test1() {
		Sentence testSentence = new Sentence("This is a very simple test");
		
		assertEquals("simple", testSentence.getLongestWord());
		assertEquals(6, testSentence.getNumberOfWords());
		
	}
	
	@Test
	void test2() {
		Sentence testSentence = new Sentence("This is a very "
				+ "\nsimple test with a linebreak");
		
		assertEquals("linebreak", testSentence.getLongestWord());
		assertEquals(9, testSentence.getNumberOfWords());

	}
	
	@Test
	void test3() {
		Sentence testSentence = new Sentence("This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string ");
		
		assertEquals("simple", testSentence.getLongestWord());
		assertEquals(121, testSentence.getNumberOfWords());
	}
	
	@Test
	void test4() {
		Sentence testSentence = new Sentence("This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string This is a very simple test of a very long string ");
		
		assertEquals("simple", testSentence.getLongestWord());
		assertEquals(1089, testSentence.getNumberOfWords());
	}
	
	@Test
	void test5() {
		Sentence testSentence = new Sentence("Deep in the human unconscious is a pervasive need for a logical universe that makes sense. But the real universe is always one step beyond logic.");
		
		assertEquals("unconscious", testSentence.getLongestWord() );
		assertEquals(26, testSentence.getNumberOfWords());
	}
	
	@Test
	void test6() {
		Sentence testSentence = new Sentence("Michael could never remember his father ever having uttered a word about death, as if the Don respected death too much to philosophize about it.");
		
		assertEquals("philosophize", testSentence.getLongestWord());
		assertEquals(25, testSentence.getNumberOfWords());
	}
	
	@Test
	void test7() {
		//one word
		Sentence testSentence = new Sentence("r");
		
		assertEquals("r", testSentence.getLongestWord());
		assertEquals(1, testSentence.getNumberOfWords());
	}
	
	@Test
	void test8() {
		//unicode runes
		Sentence testSentence = new Sentence("ᚠ ᚳ ᛦ ᛰ");
		
		assertEquals("ᚠ", testSentence.getLongestWord());
		assertEquals(4, testSentence.getNumberOfWords());
	}
	
	@Test
	void test9() {
		//unicode latin
		Sentence testSentence = new Sentence("¥ Ñ sombrero pequeña");
		
		assertEquals("sombrero", testSentence.getLongestWord());
		assertEquals(4, testSentence.getNumberOfWords());
	}
	
	@Test
	void test10() {
		//whitespace 
		Sentence testSentence = new Sentence("                  ");
		
		assertEquals("", testSentence.getLongestWord());
		assertEquals(0, testSentence.getNumberOfWords());
	}
	
	@Test
	void test11() {
		//lots of whitespace 
		Sentence testSentence = new Sentence("    stuff      other     stuff    averylongword  ");
		
		assertEquals("averylongword", testSentence.getLongestWord());
		assertEquals(4, testSentence.getNumberOfWords());
	}

}
