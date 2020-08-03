package com.salesman.configuration;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import com.salesman.service.SalesmanMetricService;
import com.salesman.service.SalesmanReaderService;
import com.salesman.service.analytics.SalesmanDataAnalyticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.transformer.GenericTransformer;

import java.nio.file.Paths;
import java.util.List;

@Configuration
@EnableIntegration
public class IntegrationFlowConfig {

	private final Properties properties;
	private final SalesmanReaderService salesmanReaderService;
	private final SalesmanDataAnalyticsService salesmanDataAnalyticsService;
	private final SalesmanMetricService salesmanMetricService;

	public IntegrationFlowConfig(Properties properties, SalesmanReaderService salesmanReaderService, SalesmanDataAnalyticsService salesmanDataAnalyticsService, SalesmanMetricService salesmanMetricService) {
		this.properties = properties;
        this.salesmanReaderService = salesmanReaderService;
		this.salesmanDataAnalyticsService = salesmanDataAnalyticsService;
		this.salesmanMetricService = salesmanMetricService;
	}

	@Bean
	public IntegrationFlow flow() {
		return IntegrationFlows
				.from(fileReadingMessageSource(), spec -> spec.poller(pollerMetada()))
				.filter(filter())
				.log(LoggingHandler.Level.INFO, "Processing file")
				.transform(Files.toStringTransformer())
				.split(splitter -> splitter.delimiters(System.lineSeparator()))
				.transform(readLine())
				.aggregate()
                .handle(generateMetricHandler())
				.handle(metricsToRawTextHandler())
				.log(LoggingHandler.Level.INFO, "Metrics processed")
				.handle(fileWritingMessageHandler())
				.get();
	}

	public FileReadingMessageSource fileReadingMessageSource() {
		return Files.inboundAdapter(Paths.get(properties.getBasePath(), properties.getFileInputFolder()).toFile())
				.useWatchService(true)
				.get();
	}

	public PollerMetadata pollerMetada() {
		return Pollers
				.fixedRate(properties.getPollerRate())
				.get();
	}

	public SimplePatternFileListFilter filter() {
		return new SimplePatternFileListFilter(properties.getFileInputMatcher());
	}

	private GenericTransformer<String, ModelData> readLine() {
        return line -> salesmanReaderService.readLine(line);
    }

    public GenericHandler<List<ModelData>> generateMetricHandler() {
        return (message, headers) -> salesmanDataAnalyticsService.process(message);
    }

	public GenericHandler<List<Metric>> metricsToRawTextHandler() {
		return (message, headers) -> salesmanMetricService.metricsToRawText(message);
	}

	public FileWritingMessageHandler fileWritingMessageHandler() {
		return Files.outboundAdapter(Paths.get(properties.getBasePath(), properties.getFileOutputFolder()).toFile())
				.deleteSourceFiles(true)
				.fileExistsMode(FileExistsMode.REPLACE)
				.fileNameGenerator(fileNameGenerator())
				.get();
	}

	private FileNameGenerator fileNameGenerator() {
		return message -> message.getHeaders()
				.get(FileHeaders.FILENAME, String.class)
				.replaceFirst(properties.getFileOutputRegex(), properties.getFileOutputReplace());
	}

}
