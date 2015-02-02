package io.pivotal.gss;

public class ActualCommandValues {

	public static final String CFBINARY = "cf";
	public static final String EMAIL = "fwang@pivotal.io";
	public static final String PASSWORD = "wang123456";
	public static final String ORG = "gss-apj";
	public static final String SPACE = "fwang";
	public static final String SPACEINPUT = "Select a space([\\s\\S]*)";
	public static final String SPACEOUTPUTPREIX = "Select a space (or press enter to skip):\n1. ";
	public static final String SPACEOUTPUTPOST = "\n";

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
		// hack here, need more "else" clause...
		if (string.contains("Select a space (or press enter to skip):\n")) {
			p.setCurrentCommandInput("space");
		} else if (string.contains("(API version:")) {
			p.setCurrentCommandInput("result");
		}
		String result = string
				.replaceAll(EMAIL, p.getEmail())
				.replaceAll(ORG, p.getOrg())
				.replaceAll(PASSWORD, p.getPwd())
				.replaceAll(p.getRunCf(), "cf")
				.replaceAll(p.getCfBinary(), "cf")
				.replaceAll(SPACE, p.getSpace())
				.replaceAll("\r", "\n")
				.replaceAll("stty: stdin isn't a terminal\n", "")
				.replaceAll(
						"stty: standard input: Inappropriate ioctl for device\n",
						"");
		if ("space".equals(p.getCurrentCommandInput())) {
			result = result.replaceAll("\\d.*\n*", "").replaceAll("Space> ",
					"1. " + p.getSpace() + "\n\nSpace> ");
		}
		return result;
	}

}
