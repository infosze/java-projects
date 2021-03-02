package fileOperation;

public class Coding {

	final String MASTER_KEY = " a,b.c;d:e+f-g%h0i1j2k3l4m5n6o7p8q9rásétíuóvöwőx@úyüzűABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÖŐÚÜŰ";

	public String coder(String message, int shift) {
		String codedText = "";

		for (int i = 0; i < message.length(); i++) {
			if (shift >= 0) {
				codedText += MASTER_KEY.charAt((shift + MASTER_KEY.indexOf(message.charAt(i))) % MASTER_KEY.length());
			} else if (shift < 0) {
				shift = shift % MASTER_KEY.length();
				codedText += MASTER_KEY.charAt(
						(MASTER_KEY.indexOf(message.charAt(i)) + MASTER_KEY.length() + shift) % MASTER_KEY.length());
			}
		}
		return codedText;
	}

	public String decoder(String message, int shift) {
		return coder(message, -shift);
	}
}
