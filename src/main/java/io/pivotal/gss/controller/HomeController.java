package io.pivotal.gss.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final String NEW_LINE = System.getProperty("line.separator");

	private static String contextRoot;
	private static String runCf;

	@Autowired
	private ServletContext servletContext;

	@PostConstruct
	public void init() {
		contextRoot = servletContext.getRealPath(File.separator);
		String cfBinary = SystemUtils.IS_OS_LINUX ? "cf-linux64" : "cf-mac";
		runCf = contextRoot + File.separator + "resources" + File.separator
				+ "cf" + File.separator + cfBinary;
	}

	@RequestMapping(value = "/")
	public String home(HttpServletRequest request, HttpSession session)
			throws IOException {
		// let us assume the input for now, it should be passed from frontend
		String input = "cf ***";
		// replace user input values to actual run values
		// processInput(input);

		String sessionId = session.getId();
		
		// These logic should be in an ajax call method instead of here

		ProcessBuilder builder = new ProcessBuilder(runCf);
		String cfHome = contextRoot + File.separator + sessionId;
		builder.environment().put("CF_HOME", cfHome);
		builder.redirectErrorStream();
		Process process = builder.start();
		// should be able to stream to the frontend instead of catching here
		BufferedReader br = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String s = null;
		while ((s = br.readLine()) != null) {
			sb.append(s);
			sb.append(NEW_LINE);
		}
		String output = sb.toString();
		
		// Instead of printing to stdout/err, need to reflect to the frontend
		System.out.println(output);

		return "home";
	}

}
