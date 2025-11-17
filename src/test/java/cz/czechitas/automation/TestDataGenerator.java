package cz.czechitas.automation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Test class for custom student tests
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public final class TestDataGenerator {

    private static final DateTimeFormatter CZ_DMY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private TestDataGenerator() {}

    public static String generateBirthdateAtLeastFourYearsOld() {
        LocalDate today = LocalDate.now();
        LocalDate minDate = LocalDate.of(2006, 1, 1);
        LocalDate maxDate = today.minusYears(4).minusDays(1);

        long minEpoch = minDate.toEpochDay();
        long maxEpoch = maxDate.toEpochDay();
        long randomEpoch = ThreadLocalRandom.current().nextLong(minEpoch, maxEpoch + 1);
        LocalDate birthdate = LocalDate.ofEpochDay(randomEpoch);

        if (ChronoUnit.YEARS.between(birthdate, today) < 4) {
            birthdate = today.minusYears(4).minusDays(1);
        }

        return birthdate.format(CZ_DMY);
    }

    public static String generateBirthdateYoungerThanFourYears() {
        LocalDate today = LocalDate.now();
        LocalDate minDate = today.minusYears(3);
        LocalDate maxDate = today;

        long minEpoch = minDate.toEpochDay();
        long maxEpoch = maxDate.toEpochDay();
        long randomEpoch = ThreadLocalRandom.current().nextLong(minEpoch, maxEpoch + 1);
        LocalDate birthdate = LocalDate.ofEpochDay(randomEpoch);

        if (ChronoUnit.YEARS.between(birthdate, today) >= 4) {
            birthdate = today.minusYears(3);
        }

        return birthdate.format(CZ_DMY);
    }

    public static boolean isAtLeastFourYearsOld(String birthdateDmy) {
        LocalDate birth = LocalDate.parse(birthdateDmy, CZ_DMY);
        return ChronoUnit.YEARS.between(birth, LocalDate.now()) >= 4;
    }
}