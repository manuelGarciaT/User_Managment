package com.example.class02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.class02.model.AppUser;
import com.example.class02.model.Phone;
import com.example.class02.repo.UserRepository;
import com.github.javafaker.Faker;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
	private final Faker faker;

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
		String number = generateRandomNumericValue(10); // Genera un número de 10 dígitos
		String cityCode = generateRandomNumericValue(3); // Genera un número de 3 dígitos
		String countryCode = generateRandomNumericValue(3); // Genera un número de 3 dígitos

		return new Phone(number, cityCode, countryCode);
	}

	private String generateRandomNumericValue(int length) {
		StringBuilder numericValue = new StringBuilder();
		for (int i = 0; i < length; i++) {
			numericValue.append(faker.random().nextInt(10)); // Agrega un dígito numérico aleatorio
		}
		return numericValue.toString();
	}
}
