package io.pivotal.gss;

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
					if (!"6".equals(userInput)) {
						userInput = "999";
					} else {
						p.setSpace("emulated-space");
					}
				} else {
					throw new RuntimeException("Unhandled argument: "
							+ argument);
				}
			}
			return ActualCommandValues.replaceAllWithActual(userInput, p)
					+ "\n";
		}
		// TODO non-interactive commands
		if (input.contains("cf push")) {
			String[] map = input.split(" ");			
			if(map.length != 3) {
				return input;
			}
			else {
				String pushCmdAppName = map[2].trim();
				p.setAppName(pushCmdAppName);				
				return "cf push " + ActualCommandValues.replaceAllWithActual(pushCmdAppName, p) + "\n";
			}
		}
		return input;
	}
}
