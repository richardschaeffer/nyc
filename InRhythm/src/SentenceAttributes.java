
public class SentenceAttributes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Sentence newSentence = new Sentence("I thought this was going to be a lot harder.");
		newSentence.printAttributes();
		System.out.println(newSentence.getAttributeString());
		
		newSentence.setSentence("The cow jumped over the moon");
		newSentence.printAttributes();
		System.out.println(newSentence.getAttributeString());

	}

	
}
