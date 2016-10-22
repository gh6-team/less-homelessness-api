package svc.profiles;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@Profile("local")
public class LocalProfile {
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
		return dbBuilder.setType(EmbeddedDatabaseType.HSQL)
				.addScript("hsql/schema-tables.sql")
				.addScript("hsql/users.sql")
				.addScript("hsql/clients.sql")
				.addScript("hsql/disabilities.sql")
				.addScript("hsql/employment_education.sql")
				.addScript("hsql/enrollment.sql")
				.build();
	}
	
	//default username : sa, password : ''
	@PostConstruct
	public void getDbManager(){
	   DatabaseManagerSwing.main(
		new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
	}
}
