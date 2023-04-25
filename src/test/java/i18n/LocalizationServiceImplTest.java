package i18n;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import org.junit.jupiter.params.provider.Arguments;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalizationServiceImplTest {
    LocalizationServiceImpl sut;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests started");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test start");
        sut = new LocalizationServiceImpl();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Tests ended");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test ended");
    }

    @ParameterizedTest //для всех вариантов
    @MethodSource("methodSource")
    public void testLocale(Country country, String text) {
        String result = sut.locale(country);

        Assertions.assertEquals(text, result);
    }

    public Stream<Arguments> methodSource() {

        return Stream.of(
                Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(USA, "Welcome")
        );
    }
}
