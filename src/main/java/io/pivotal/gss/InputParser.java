package io.pivotal.gss;

import io.pivotal.gss.controller.HomeController;

public class InputParser {

	private InputParser() {
	}

	public static String parse(String input, UserProperties p) {
		if (input.contains(">")) {
			String[] map = input.split(">");
			String userInput = map[1].trim();
			String argument = map[0].trim().toLowerCase();
			switch (argument) {
			case "api endpoint":
				p.setApi(userInput);
				break;
			case "email":
				p.setEmail(userInput);
				break;
			default:
				if (argument.endsWith("password")) {
					p.setPwd(userInput);
				} else if (argument.endsWith("org")) {
					if (!"1".equals(userInput)) {
						userInput = "999";
					} else {
						p.setOrg("emulated-org");
					}
				} else if (argument.endsWith("space")) {
					if (!"1".equals(userInput)) {
						userInput = "999";
					} else {
						p.setSpace("emulated-space");
						userInput = "6";
					}
				}
			}
			return ActualCommandValues.replaceAllWithActual(userInput, p)
					+ "\n";
		}
		// TODO non-interactive commands
		if (input.startsWith("cf push")) {
			String[] map = input.split(" ");
			if (map.length < 3) {
				return input;
			}
			String pushCmdAppName = map[2].trim();
			p.setAppName(pushCmdAppName);
			return "cf push " + pushCmdAppName + " -p "
					+ HomeController.contextRoot + "/resources/sample.war";
		}
		return input;
	}
}
