package sender;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSendTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests started");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test start");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Tests ended");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test ended");
    }
    @Test
    public void testSendRussia() {
        String expected = "Добро пожаловать";
        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSendUSA() {
        String expected = "Welcome";
        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String actual = messageSender.send(headers);

        Assertions.assertEquals(expected, actual);
    }
}
