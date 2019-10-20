package org.ironstudios.userssvc.config;


import org.ironstudios.userssvc.repository.UsersRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class MongoConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(UsersRepository usersRepository){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                usersRepository.save(new User("arjunVijayvargiya","Welcome1",
//                        "testemail","testnumber"));
//            }
//        };
//    }
}
