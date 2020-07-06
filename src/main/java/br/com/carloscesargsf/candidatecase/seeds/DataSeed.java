package br.com.carloscesargsf.candidatecase.seeds;

import br.com.carloscesargsf.candidatecase.entities.CreditCardType;
import br.com.carloscesargsf.candidatecase.repositories.CreditCardTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class DataSeed implements CommandLineRunner {

    private final CreditCardTypeRepository creditCardTypeRepository;

    public DataSeed(CreditCardTypeRepository creditCardTypeRepository) {
        this.creditCardTypeRepository = creditCardTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (creditCardTypeRepository.count() == 0) {
            creditCardTypeRepository.saveAll(Arrays.asList("Visa", "MasterCard", "Elo").stream()
                    .map(type -> {
                        CreditCardType creditCardType = new CreditCardType();

                        creditCardType.setName(type);

                        return creditCardType;
                    }).collect(Collectors.toList()));
        }
    }

}
