package com.campuslands.modules.flightconnections.domain.models;

public class FlightConnection {
    int id;
    String connection_number;
    int id_trip;
    int id_plane;
    String id_airport;

    public FlightConnection(int id, String connection_number, int id_trip, int id_plane, String id_airport) {
        this.id = id;
        this.connection_number = connection_number;
        this.id_trip = id_trip;
        this.id_plane = id_plane;
        this.id_airport = id_airport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConnection_number() {
        return connection_number;
    }

    public void setConnection_number(String connection_number) {
        this.connection_number = connection_number;
    }

    public int getId_trip() {
        return id_trip;
    }

    public void setId_trip(int id_trip) {
        this.id_trip = id_trip;
    }

    public int getId_plane() {
        return id_plane;
    }

    public void setId_plane(int id_plane) {
        this.id_plane = id_plane;
    }

    public String getId_airport() {
        return id_airport;
    }

    public void setId_airport(String id_airport) {
        this.id_airport = id_airport;
    }
}