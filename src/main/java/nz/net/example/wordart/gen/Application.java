package nz.net.example.wordart.gen;

import nz.net.example.wordart.gen.domain.Job;
import nz.net.example.wordart.gen.service.WordArtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.EnumSet;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private WordArtService wordArtService;

	/**
	 * This runs once the application has fully started. It's purely here
	 * as an sanity check to make sure {@link WordArtService#generateWordArtImage(Job)}}
	 * is working as it should.
	 * Note: This would be removed for production
	 *
	 * @param args there are no args required
	 */
	@Override
	public void run(String... args) {
		LOGGER.info("CommandLineRunner Starting.....");

		Job job = new Job(Job.Format.PNG, "Hello World!",
				EnumSet.of(Job.Effect.SHADOW_REFLECT), Job.Colour.RED, Job.Font.ARIAL);

		wordArtService.generateWordArtImage(job);

		LOGGER.info("CommandLineRunner Complete.");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}