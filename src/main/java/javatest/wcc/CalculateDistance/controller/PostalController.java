package javatest.wcc.CalculateDistance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javatest.wcc.CalculateDistance.model.Postal;
import javatest.wcc.CalculateDistance.repo.PostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostalController {

    private final static double EARTH_RADIUS = 6371; // radius in kilometers

    @Autowired
    private PostalRepository postalRepository;

    @GetMapping("/")
    public String index(Model model) {

        List<String> postcodeList = getAllPostcodes1();
        model.addAttribute("postcodeList", postcodeList);
        model.addAttribute("postcode1", "AB10");
        return "index"; // This corresponds to src/main/resources/templates/index.html
    }

    @PostMapping("/calculate")
    public String calculateDistance(@Valid @ModelAttribute Postal postal, Model model) throws JsonProcessingException {
        System.out.println(postal.toString());
        // validate
        //System.out.println(postal.toString());

        if(postal.getPostcode1() == "" || postal.getPostcode2() == "") {
            String msg = "Enter the valid postcode.";
            model.addAttribute("error", msg);
            return "error";
        }

        String postcode1 = postal.getPostcode1();
        String postcode2 = postal.getPostcode2();

        System.out.println("Postcode1 = " + postcode1);
        System.out.println("Postcode2 = " + postcode2);

        Postal p1 = postalRepository.findByPostcode1(postcode1);
        Postal p2 = postalRepository.findByPostcode1(postcode2);

        double latitude = p1.getLatitude();
        double latitude2 = p2.getLatitude();
        double longitude = p1.getLongitude();
        double longitude2 = p2.getLongitude();

        double distance = calculateDistance(latitude, longitude, latitude2, longitude2);


        String str1 = "Postcode1= " + postcode1 + "; Longitude1 = " + p1.getLongitude() + "; Latitude1 = " + p1.getLatitude();
        String str2 = "Postcode2= " + postcode2 + "; Longitude2 = " + p2.getLongitude() + "; Latitude2 = " + p2.getLatitude();
        String distanceStr = "Distance = "  + Math.round(distance * 100.0) / 100.0 + " KM ";

        postal.setLatitude(latitude);
        postal.setLongitude(longitude);
        postal.setLongitude2(longitude2);
        postal.setLatitude2(latitude2);
        postal.setDistance(distance);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println("Calculate distance=" + distance);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(postal);

        model.addAttribute("postcode1", str1);
        model.addAttribute("postcode2", str2);
        model.addAttribute("distance", distanceStr);
        model.addAttribute("postal", json);

        return "result";
    }

    @GetMapping("/postcode")
    public Postal getByPostcode(
            @RequestParam(value = "postcode1") String postcode1) {
        Postal p = postalRepository.findByPostcode1(postcode1);
        System.out.println("Long = " + p.getLongitude());
        System.out.println("Lat = " + p.getLatitude());
        return p;
    }

    @GetMapping("/all")
    public List<Postal> getPostal() {
       // List<Postal> postalList = new ArrayList<>();

        System.out.println("Postal size = " + postalRepository.findAll().size());
        return postalRepository.findAll();

    }

    public List<String> getAllPostcodes1() {
        return postalRepository.findAllPostcode1();
    }

    private double calculateDistance(double latitude, double longitude, double latitude2, double
            longitude2) {
        // Using Haversine formula! See Wikipedia;
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS * c);
    }

    private double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }
    private double square(double x) {
        return x * x;
    }

}
