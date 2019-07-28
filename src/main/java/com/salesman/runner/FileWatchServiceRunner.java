
package com.salesman.runner;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.salesman.configuration.Properties;
import com.salesman.service.SalesmanProcessorService;

@Component
public class FileWatchServiceRunner implements CommandLineRunner {

	private SalesmanProcessorService salesmanProcessorService;
	private Properties properties;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public FileWatchServiceRunner(SalesmanProcessorService salesmanProcessorService,
			Properties properties) {
		this.salesmanProcessorService = salesmanProcessorService;
		this.properties = properties;
	}

	@Override
	public void run(String... args) throws IOException, InterruptedException {
		PathMatcher matcher = getFilePathMatcher();
		final Path inputFolder = getInputFolder();

		final WatchService watchService = FileSystems.getDefault().newWatchService();
		inputFolder.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);
		
		Files.walk(inputFolder).forEach(filePath -> processPath(matcher, inputFolder, filePath));
		while (true) {
			final WatchKey watchKey = getWatchKey(watchService);
			if( watchKey != null) {
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					final Path filePath = (Path) event.context();
					processPath(matcher, inputFolder, inputFolder.resolve(filePath));
				}
				watchKey.reset();
			} else {
				break;
			}
		}
	}

	private WatchKey getWatchKey(final WatchService watchService) throws InterruptedException {
		Long fileInputMaxWaitTime = properties.getFileInputMaxWaitTime();
		if(fileInputMaxWaitTime < 0) {
			return watchService.take();
		} else {
			return watchService.poll(fileInputMaxWaitTime, TimeUnit.MILLISECONDS);
		}
	}

	private PathMatcher getFilePathMatcher() {
		return FileSystems.getDefault().getPathMatcher(properties.getFileInputMatcher());
	}

	private Path getInputFolder() throws IOException {
		final Path inputFolder = Paths.get(properties.getBasePath(), properties.getFileInputFolder());
		if (Files.notExists(inputFolder)) {
			Files.createDirectories(inputFolder);
		}
		return inputFolder;
	}

	private void processPath(PathMatcher matcher, final Path inputFolder, final Path filePath) {
		if (matcher.matches(filePath.getFileName())) {
			try {
				salesmanProcessorService.process(filePath);
			} catch (Exception e) {
				log.error("Unable to process file: " + filePath, e);
			}
		}
	}
}