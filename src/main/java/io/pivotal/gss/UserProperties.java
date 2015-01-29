package io.pivotal.gss;

import java.io.OutputStream;

import org.apache.commons.exec.ExecuteWatchdog;

public class UserProperties {

	private ExecuteWatchdog watchdog;
	private OutputStream os;
	private String api;
	private String email;
	private String pwd;
	private String org = "emulated-org";
	private String space = "emulated-space";
	private String runCf;
	private String cfBinary;
	private String appName;
	private String currentCommandInput;

	public String getCurrentCommandInput() {
		return currentCommandInput;
	}

	public void setCurrentCommandInput(String currentCommandInput) {
		this.currentCommandInput = currentCommandInput;
	}

	public String getCfBinary() {
		return cfBinary;
	}

	public void setCfBinary(String cfBinary) {
		this.cfBinary = cfBinary;
	}

	public ExecuteWatchdog getWatchdog() {
		return watchdog;
	}

	public void setWatchdog(ExecuteWatchdog watchdog) {
		this.watchdog = watchdog;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getRunCf() {
		return runCf;
	}

	public void setRunCf(String runCf) {
		this.runCf = runCf;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
