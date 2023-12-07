package javatest.wcc.CalculateDistance.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "postaloutcode", schema = "postal")
public class Postal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Column(name = "postcode")
    private String postcode1;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;


   @Column(name = "postcode", insertable=false, updatable=false)
   private String postcode2;


    private double longitude2;

    private double latitude2;

    private double distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostcode1() {
        return postcode1;
    }

    public void setPostcode1(String postcode1) {
        this.postcode1 = postcode1;
    }

    public String getPostcode2() {
        return postcode2;
    }

    public void setPostcode2(String postcode2) {
        this.postcode2 = postcode2;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLongitude2() {
        return longitude2;
    }

    public void setLongitude2(double longitude2) {
        this.longitude2 = longitude2;
    }

    public double getLatitude2() {
        return latitude2;
    }

    public void setLatitude2(double latitude2) {
        this.latitude2 = latitude2;
    }

    @Override
    public String toString() {
        return "Postal{" +
                "postcode1='" + postcode1 + '\'' +
                ", postcode2='" + postcode2 + '\'' +
                '}';
    }
}
