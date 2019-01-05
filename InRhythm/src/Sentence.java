import java.util.Arrays;
import java.util.Comparator;

public class Sentence {

	int numWords;
	String longestWord;
	String sentence;
	
	public Sentence() {
		this.sentence = "";
		setNumWords("");
		setLongestWord("");	
	}
	
	public Sentence(String sentence) {
		this.sentence = sentence;
		setNumWords(sentence);
		setLongestWord(sentence);	
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
		setNumWords(sentence);
		setLongestWord(sentence);
	}
	
	private void setNumWords(String sentence) {
		this.numWords = (sentence.trim().length() == 0) ? 0 : sentence.replaceAll("\\s+", " ").trim().split("(?U)\\W+").length;
	}
	
	private void setLongestWord(String sentence) {
	   this.longestWord = Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length)).orElse("");

	}
	
	public int getNumberOfWords() {
		return this.numWords;
	}
	
	public String getLongestWord() {
		return this.longestWord;
	}
	
	public String getAttributeString() {
		return this.longestWord + " " + Integer.toString(this.getNumberOfWords());
	}
	
	public void printAttributes() {
		System.out.println("Number of words: " + this.getNumberOfWords());
		System.out.println("Longest word: " + this.getLongestWord());
	}
}
