package io.pivotal.gss;

public class ActualCommandValues {

	public static final String EMAIL = "fwang@pivotal.io";
	public static final String PASSWORD = "wang123456";
	public static final String ORG = "gss-apj";
	public static final String SPACE = "fwang";
	public static final String APPNAME = "thisisappname";
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
		if (p.getAppName() != null && p.getAppName().length() > 0) {
			result = result.replaceAll(p.getAppName() , APPNAME);
		}
		return result;
	}

	public static String replaceAllWithUser(String string, UserProperties p) {
		return string.replaceAll(EMAIL, p.getEmail())
				.replaceAll(ORG, p.getOrg()).replaceAll(PASSWORD, p.getPwd())
				.replaceAll(p.getRunCf(), "cf").replaceAll(SPACE, p.getSpace())
				//.replaceAll(APPNAME, p.getAppName()).replaceAll("Select a space[\\s\\S]*\\d.*\n*", "Select a space (or press enter to skip):\n" + "1."+ p.getSpace() + SPACEOUTPUTPOST);
				.replaceAll("\\d.*\n*","")
				.replaceAll(APPNAME, p.getAppName()).replaceAll("Select a space([\\s\\S]*)", "Select a space (or press enter to skip):\n"+ "1."+ p.getSpace() + SPACEOUTPUTPOST);
	}

}
