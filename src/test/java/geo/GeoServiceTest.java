package geo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GeoServiceTest {
    GeoServiceImpl sut;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests started");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test start");
        sut = new GeoServiceImpl();
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
    public void parameterizedTestByIp(String ip, Location expected) {
        Location result = sut.byIp(ip);

        Assertions.assertEquals(expected.getCity(), result.getCity());
        Assertions.assertEquals(expected.getCountry(), result.getCountry());
        Assertions.assertEquals(expected.getStreet(), result.getStreet());
        Assertions.assertEquals(expected.getBuiling(), result.getBuiling());
    }

    public Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("96.0.25.111", new Location("New York", Country.USA, null, 0)),
                Arguments.of("172.0.31.111", new Location("Moscow", Country.RUSSIA, null, 0))
        );
    }

    @Test
    public void testByCoordinates() {
        double latitude = 55.7522;
        double longitude = 37.6156;
        Class<RuntimeException> expected = RuntimeException.class;

        Executable exception = () -> sut.byCoordinates(latitude, longitude);

        Assertions.assertThrows(expected, exception);
    }

}
