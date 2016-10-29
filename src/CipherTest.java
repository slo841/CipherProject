import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CipherTest {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String NEW_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;

//	@Test
//	public void rotationCipher1() {
//		String cipherText = CipherCracker.slowReadFileAsString("CipherBlankTemplate//cipher1.txt");
//		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
//		String testCipherText = CipherCracker.crackRotationCipher(cipherText);
//		assertEquals(testCipherText, correctCipherText);
//	}
//	
//	@Test
//	public void rotationCipherTest() {
//		String answer = "the quick brown fox jumped over the lazy dogs";
//		String cipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
//		String testCipherText = CipherCracker.crackRotationCipher(cipherText);
//		assertEquals(testCipherText, answer);
//	}
	
	@Test
	public void rotationCipherEncrypt() {
		String plaintext = "hello";
		String correctCipherText = "khoor";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3, 
				"abcdefghijklmnopqrstuvwxyz");
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherEncryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3, DEFAULT_ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3, DEFAULT_ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100, DEFAULT_ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100, DEFAULT_ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherEncryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3, DEFAULT_ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3, DEFAULT_ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100, DEFAULT_ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100, DEFAULT_ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
}
