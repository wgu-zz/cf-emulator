package io.pivotal.gss.controller;

import io.pivotal.gss.UserProperties;
import io.pivotal.gss.WebSocketPumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static String contextRoot;
	private static String runCf;
	private static Map<String, UserProperties> userSessions = new HashMap<String, UserProperties>();

	@Autowired
	private SimpMessagingTemplate outputSender;

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
		return "home";
	}

	@MessageMapping("/run")
	@SuppressWarnings("unchecked")
	public void run(String command, MessageHeaders messageHeaders)
			throws IOException {

		final Map<String, String> attributes = messageHeaders.get(
				"simpSessionAttributes", Map.class);
		final String httpSessionId = attributes.get("HTTPSESSIONID");
		UserProperties userProperties = userSessions.get(httpSessionId);
		// Temporary solution... but we can remove the entry when the web socket
		// connection ends, TODO
		if (userProperties != null) {
			if (userProperties.getWatchdog().isWatching()) {
				OutputStream os = userProperties.getOs();
				os.write(command.getBytes());
				os.flush();
				return;
			}
		} else {
			userProperties = new UserProperties();
			userSessions.put(httpSessionId, userProperties);
		}

		String cfHome = contextRoot + File.separator + httpSessionId;
		// Maybe more environment variables parsed from the inputs
		Map<String, String> env = new HashMap<String, String>();
		env.put("CF_HOME", cfHome);

		CommandLine cmdLine = new CommandLine(runCf);
		cmdLine.addArgument("login");
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		Executor executor = new DefaultExecutor();
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
		executor.setWatchdog(watchdog);

		// TODO with WebSocketPumpStreamHandler we actually do not need an
		// output stream anymore, command output will be directly sent out
		ByteArrayOutputStream cmdOutput = new ByteArrayOutputStream();
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream(pos);
		// TODO This is wrong, cannot use a universal system.in stream for all
		// the threads. But haven't got a better way to do this in apache
		// commons exec lib
		System.setIn(pis);
		outputSender.setDefaultDestination("/broker/out");
		executor.setStreamHandler(new WebSocketPumpStreamHandler<String>(
				cmdOutput, cmdOutput, System.in, outputSender));
		executor.execute(cmdLine, env, resultHandler);

		userProperties.setWatchdog(watchdog);
		userProperties.setOs(pos);
		System.out.println("*** executed ***");
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
