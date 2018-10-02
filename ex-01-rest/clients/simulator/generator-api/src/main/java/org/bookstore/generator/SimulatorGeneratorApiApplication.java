package org.bookstore.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ConfigurationProperties
public class SimulatorGeneratorApiApplication implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(SimulatorGeneratorApiApplication.class);

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SimulatorGeneratorApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int nbLoops = Integer.parseInt(env.getProperty("simulator.loop"));
		long sleep = Long.parseLong(env.getProperty("simulator.sleep"));

		for (int i = 0; i < nbLoops; i++) {
			try {
				RestTemplate restTemplate = new RestTemplate();
				String generatorApiUrl = "http://localhost:8081/generator/api/numbers";
				ResponseEntity<String> response = restTemplate.getForEntity(generatorApiUrl , String.class);
				log.info(response.getBody());
				Thread.sleep(sleep);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
}
