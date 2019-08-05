package batch;

import org.springframework.batch.item.ItemProcessor;

public class StringEncryptor implements ItemProcessor<String, String> {

	@Override
	public String process(final String textToEncrypt) throws Exception {
		System.out.println("textToEncrypt: " + textToEncrypt);
		
		String encryptedString = "";

		// Ecnrypt the string

		int rightShift = 5;
		char chr;

		for (int i = 0; i < textToEncrypt.length(); i++) {
			// Shift one character at a time
			chr = textToEncrypt.charAt(i);

			// if char lies between a and z
			if (chr >= 'a' && chr <= 'z') {
				// shift alphabet
				chr = (char) (chr + rightShift);
				// if shift alphabet greater than 'z'
				if (chr > 'z') {
					// reshift to starting position
					chr = (char) (chr + 'a' - 'z' - 1);
				}
				encryptedString = encryptedString + chr;
			}

			// if alphabet lies between 'A'and 'Z'
			else if (chr >= 'A' && chr <= 'Z') {
				// shift alphabet
				chr = (char) (chr + rightShift);

				// if shift alphabet greater than 'Z'
				if (chr > 'Z') {
					// re-shift to starting position
					chr = (char) (chr + 'A' - 'Z' - 1);
				}
				encryptedString = encryptedString + chr;
			} else {
				encryptedString = encryptedString + chr;
			}
		}
		
		System.out.println("encryptedString: " + encryptedString);
		
		return encryptedString;
	}
}
