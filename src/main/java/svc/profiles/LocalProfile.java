package svc.profiles;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@Profile("local")
public class LocalProfile {
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
		return dbBuilder.setType(EmbeddedDatabaseType.HSQL)
				.addScript("hsql/schema-tables.sql")
				.addScript("hsql/clients.sql")
				.addScript("hsql/disabilities.sql")
				.addScript("hsql/shelters.sql")
				.addScript("hsql/shelter-beds.sql")
				.addScript("hsql/shelter-bed-assignments.sql")
				.build();
	}

	//default username : sa, password : ''
	@PostConstruct
	public void getDbManager(){
	   DatabaseManagerSwing.main(
		new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
	}
}
