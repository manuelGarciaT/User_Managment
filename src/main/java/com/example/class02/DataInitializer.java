package com.example.class02;

import com.example.class02.model.AppUser;
import com.example.class02.model.Phone;
import com.example.class02.repo.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final Faker faker;

    @Autowired
    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) {
        createSampleData();
    }

    private void createSampleData() {
        for (int i = 0; i < 5; i++) {
            String name = faker.name().fullName();
            String email = faker.internet().emailAddress();
            String password = faker.internet().password();

            AppUser user = new AppUser(name, email, password);
            Phone phone = createRandomPhone();
            user.addPhone(phone);

            userRepository.save(user);
        }
    }

    private Phone createRandomPhone() {
        String number = faker.phoneNumber().phoneNumber();
        String cityCode = faker.phoneNumber().extension();
        String countryCode = String.format("%03d", faker.random().nextInt(1000));


        return new Phone(number, cityCode, countryCode);
    }
}
