package io.pivotal.gss;

import java.io.OutputStream;

import org.apache.commons.exec.ExecuteWatchdog;

public class UserProperties {

	private ExecuteWatchdog watchdog;
	private OutputStream os;
	private String email;
	private String org;
	private String space;

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

}
