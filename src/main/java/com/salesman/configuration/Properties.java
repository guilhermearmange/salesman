package com.salesman.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Properties {
	@Value("${separator}")
	private String separator;
	@Value("${file.output.regex}")
	private String fileOutputRegex;
	@Value("${file.output.replace}")
	private String fileOutputReplace;
	@Value("${file.output.folder}")
	private String fileOutputFolder;
	@Value("${file.input.folder}")
	private String fileInputFolder;
	@Value("${file.input.matcher}")
	private String fileInputMatcher;
	
	public String getBasePath() {
		return System.getProperty("user.home");
	}
	
	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getFileOutputRegex() {
		return fileOutputRegex;
	}

	public void setFileOutputRegex(String fileOutputRegex) {
		this.fileOutputRegex = fileOutputRegex;
	}

	public String getFileOutputReplace() {
		return fileOutputReplace;
	}

	public void setFileOutputReplace(String fileOutputReplace) {
		this.fileOutputReplace = fileOutputReplace;
	}

	public String getFileOutputFolder() {
		return fileOutputFolder;
	}

	public void setFileOutputFolder(String fileOutputFolder) {
		this.fileOutputFolder = fileOutputFolder;
	}

	public String getFileInputFolder() {
		return fileInputFolder;
	}

	public void setFileInputFolder(String fileInputFolder) {
		this.fileInputFolder = fileInputFolder;
	}

	public String getFileInputMatcher() {
		return fileInputMatcher;
	}

	public void setFileInputMatcher(String fileInputMatcher) {
		this.fileInputMatcher = fileInputMatcher;
	}

}
