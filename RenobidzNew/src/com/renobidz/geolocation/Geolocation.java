package com.renobidz.geolocation;


/*import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.WebServiceClient;*/

/**
 * Created by lmgagne on 15-03-09.
 */
public class Geolocation {
/*    private CityResponse cityResponse;
    private Country country;
    private Subdivision subdivision;
    private City city;
    private Location location;
    private Postal postal;

    public Geolocation(InetAddress ipAddress) throws GeoIp2Exception, IOException{
        WebServiceClient client = new WebServiceClient.Builder(98839, "V46824bOXhxX").build();
        this.cityResponse = client.city(ipAddress);
        this.country = cityResponse.getCountry();
        this.subdivision = cityResponse.getMostSpecificSubdivision();
        this.city = cityResponse.getCity();
        this.location = cityResponse.getLocation();
        this.postal = cityResponse.getPostal();
    }

    public String getCountryIsoCode(){
        return this.country.getIsoCode();
    }

    public String getCountryName(){
        return this.country.getName();
    }

    public String getSubDivisionIsoCode(){
        return this.subdivision.getIsoCode();
    }

    public String getSubDivisionName(){
        return this.subdivision.getName();
    }

    public String getCity(){
        return this.city.getName();
    }

    public Double getLocationLatitude(){
        return this.location.getLatitude();
    }

    public Double getLocationLongitude(){
        return this.location.getLongitude();
    }

    public String getPostal(){
        return this.postal.getCode();
    }*/
}
