package io.pivotal.gss.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class HomeController {

	private static final String NEW_LINE = System.getProperty("line.separator");

	private static String contextRoot;
	private static String runCf;

	private Map<String, Process> cfProcesses;

	@Autowired
	private ServletContext servletContext;

	@PostConstruct
	public void init() {
		contextRoot = servletContext.getRealPath(File.separator);
		String cfBinary = SystemUtils.IS_OS_LINUX ? "cf-linux64" : "cf-mac";
		runCf = contextRoot + File.separator + "resources" + File.separator
				+ "cf" + File.separator + cfBinary;
		cfProcesses = Collections.emptyMap();
	}

	@RequestMapping(value = "/")
	public String home(HttpServletRequest request, HttpSession session)
			throws IOException {
		return "home";
	}

	@RequestMapping(value = "/run")
	@ResponseBody
	public DeferredResult<String> run(
			@RequestParam(required = false) final String[] arguments,
			HttpSession session) throws IOException {

		final String sessionId = session.getId();

		DeferredResult<String> result = new DeferredResult<String>(null,
				Collections.emptyList());

		String cfHome = contextRoot + File.separator + sessionId;
		// Maybe more environment variables parsed from the inputs
		Map<String, String> env = new HashMap<String, String>();
		env.put("CF_HOME", cfHome);

		CommandLine cmdLine = new CommandLine(runCf);
		cmdLine.addArguments(arguments);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		Executor executor = new DefaultExecutor();

		ByteArrayOutputStream cmdOutput = new ByteArrayOutputStream();
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream(pos);
		// TODO This is wrong, cannot use a universal system.in stream for all
		// the threads. But haven't got a better way to do this in apache
		// commons exec lib
		System.setIn(pis);
		executor.setStreamHandler(new PumpStreamHandler(cmdOutput, cmdOutput,
				System.in));
		executor.execute(cmdLine, env, resultHandler);

		// cfProcesses.put(sessionId, process);
		//
		// result.onCompletion(new Runnable() {
		// public void run() {
		// cfProcesses.remove(sessionId);
		// }
		// });

		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setResult(cmdOutput.toString());

		return result;
	}

	// TODO remove the test code
	public static void main(String[] args) throws Exception {
		CommandLine cmdLine = new CommandLine("/usr/local/bin/cf");
		cmdLine.addArgument("login");

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

		Executor executor = new DefaultExecutor();

		final PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream(pos);
		System.setIn(pis);
		executor.setStreamHandler(new PumpStreamHandler(System.out, System.err,
				System.in));
		executor.execute(cmdLine, resultHandler);

		System.out.println("non blocking");

		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					pos.write("sgu@pivotal.io\n".getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		r.run();

	}

}
