
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.sql.SQLException;

@EnableJpaRepositories("com.senla.repos")
@EntityScan ("com.senla.entity")
@SpringBootApplication(scanBasePackages = {"com.senla","com.senla.repos",
		"com.senla.controllers", "com.senla.dto", "com.senla.services"})
public class ScooterRentalApplication {

	public ScooterRentalApplication() throws SQLException {
	}

	public static void main(String[] args) {
		SpringApplication.run(ScooterRentalApplication.class, args);
	}






}
