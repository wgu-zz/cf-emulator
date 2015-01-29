package io.pivotal.gss;

public class ActualCommandValues {

	public static final String EMAIL = "fwang@pivotal.io";
	public static final String PASSWORD = "wang123456";
	public static final String ORG = "gss-apj";
	public static final String SPACE = "fwang";

	private ActualCommandValues() {
	}

	public static String replaceAllWithActual(String string, UserProperties p) {
		String result = string.trim();
		if (p.getEmail() != null && p.getEmail().length() > 0) {
			result = result.replaceAll(p.getEmail(), EMAIL);
		}
		if (p.getOrg() != null && p.getOrg().length() > 0) {
			result = result.replaceAll(p.getOrg(), ORG);
		}
		if (p.getPwd() != null && p.getPwd().length() > 0) {
			result = result.replaceAll(p.getPwd(), PASSWORD);
		}
		if (p.getSpace() != null && p.getSpace().length() > 0) {
			result = result.replaceAll(p.getSpace(), SPACE);
		}
		return result;
	}

	public static String replaceAllWithUser(String string, UserProperties p) {
		return string.replaceAll(EMAIL, p.getEmail())
				.replaceAll(ORG, p.getOrg()).replaceAll(PASSWORD, p.getPwd())
				.replaceAll(p.getRunCf(), "cf").replaceAll(SPACE, p.getSpace());
	}

}
