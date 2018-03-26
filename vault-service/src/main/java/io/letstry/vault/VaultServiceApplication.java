package io.letstry.vault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.letstry.vault.properties.PropertyConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(PropertyConfiguration.class)
public class VaultServiceApplication implements CommandLineRunner{
	
	private final PropertyConfiguration configuration;

	public VaultServiceApplication(PropertyConfiguration configuration) {
		this.configuration = configuration;
	}

	public static void main(String[] args) {
		SpringApplication.run(VaultServiceApplication.class, args);
	}

	@Override
    public void run(String... args) {

        Logger logger = LoggerFactory.getLogger(VaultServiceApplication.class);

        logger.info("----------------------------------------");
        logger.info("Configuration properties");
        logger.info("        example.username is {}", configuration.getUsername());
        logger.info("        example.password is {}", configuration.getPassword());
        logger.info("----------------------------------------");
    }
}
