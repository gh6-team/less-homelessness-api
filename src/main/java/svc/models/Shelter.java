package svc.models;

import svc.location.LatLng;

public class Shelter {
    public int id;
    public String name;
    public String address;
    public String city;
    public String state;
    public String zip_code;
    public String phone_number;
    public LatLng location;
    public int allows_men;
    public int allows_women;
    public int allows_children;
}
