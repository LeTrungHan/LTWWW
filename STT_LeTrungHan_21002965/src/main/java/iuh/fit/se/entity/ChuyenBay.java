package iuh.fit.se.entity;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Check;

import java.util.Date;

public class ChuyenBay {
    private int id;
    @NotNull(message = "Airplane name khong duoc de trong")
    private String airplane_name;

    private String departure_airport;
    private String arrival_airport;

    private Date departure_date;

    private Date arrival_date;
    @DecimalMin(value = "0.0", message = "Seat fare phai lon hon hoac bang 0")
    private double seat_fare;
    private int flight_status;


    public ChuyenBay() {
    }

    public ChuyenBay(int id, String airplane_name, String departure_airport, String arrival_airport, Date departure_date, Date arrival_date, Double seat_fare, int flight_status) {
        this.id = id;
        this.airplane_name = airplane_name;
        this.departure_airport = departure_airport;
        this.arrival_airport = arrival_airport;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.seat_fare = seat_fare;
        this.flight_status = flight_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirplane_name() {
        return airplane_name;
    }

    public void setAirplane_name(String airplane_name) {
        this.airplane_name = airplane_name;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public double getSeat_fare() {
        return seat_fare;
    }

    public void setSeat_fare(double seat_fare) {
        this.seat_fare = seat_fare;
    }

    public int getFlight_status() {
        return flight_status;
    }

    public void setFlight_status(int flight_status) {
        this.flight_status = flight_status;
    }

    @Override
    public String toString() {
        return "ChuyenBay{" +
                "id=" + id +
                ", airplane_name='" + airplane_name + '\'' +
                ", departure_airport='" + departure_airport + '\'' +
                ", arrival_airport='" + arrival_airport + '\'' +
                ", departure_date=" + departure_date +
                ", arrival_date=" + arrival_date +
                ", seat_fare=" + seat_fare +
                ", flight_status=" + flight_status +
                '}';
    }
}
