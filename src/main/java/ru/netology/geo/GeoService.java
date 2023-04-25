package ru.netology.geo;

import ru.netology.entity.Location;

public interface GeoService {

    Location byIp(String ip);

    RuntimeException byCoordinates(double latitude, double longitude);
}
