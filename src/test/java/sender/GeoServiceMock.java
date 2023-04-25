package sender;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

public class GeoServiceMock implements GeoService {

    private String city;

    private Country country;

    private String street;

    private int builing;
    private String exeption;

    @Override
    public Location byIp(String ip) {
        return new Location(city, country, street, builing);
    }

    public void setIp(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
        ;
    }

    @Override
    public RuntimeException byCoordinates(double latitude, double longitude) {
        return new RuntimeException(exeption);
    }

    public void setException(String exeption) {
        this.exeption = exeption;
    }
}
