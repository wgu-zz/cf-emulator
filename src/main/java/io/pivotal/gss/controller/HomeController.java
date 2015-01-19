package io.pivotal.gss.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String home(HttpServletRequest request) throws IOException {
		String contextRoot = request.getServletContext().getRealPath(
				File.separator);
		String cfBinary = SystemUtils.IS_OS_LINUX ? "cf-linux64" : "cf-mac";
		String cf = contextRoot + File.separator + "resources" + File.separator
				+ "cf" + File.separator + cfBinary;

		Process proc = Runtime.getRuntime().exec(cf);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(
				proc.getErrorStream()));

		// read the output from the command
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		}

		// read any errors from the attempted command
		while ((s = stdError.readLine()) != null) {
		}
		
		return "home";
	}

}
