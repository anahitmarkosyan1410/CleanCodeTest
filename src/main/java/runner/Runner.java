package runner;

import airoport.Airport;
import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Arrays;
import java.util.List;

public class Runner {

    private static PassengerPlane passengerPlaneBoeing_737 = new PassengerPlane.PassengerPlaneBuilder(850).
            setMaxFlightDistance(630).setMaxSpeed(5500).setModel("Boeing-737").setMaxLoadCapacity(700).build();

    private static PassengerPlane passengerPlaneBoeing_737_800 = new PassengerPlane.PassengerPlaneBuilder(900).
            setMaxFlightDistance(400).setMaxSpeed(5900).setModel("Boeing-737-800").setMaxLoadCapacity(550).build();

    private static PassengerPlane passengerPlaneBoeing_747 = new PassengerPlane.PassengerPlaneBuilder(830).
            setMaxFlightDistance(430).setMaxSpeed(6100).setModel("Boeing-747").setMaxLoadCapacity(540).build();

    private static PassengerPlane passengerPlaneAirbus_A330 = new PassengerPlane.PassengerPlaneBuilder(820).
            setMaxFlightDistance(510).setMaxSpeed(5200).setModel("Airbus A330").setMaxLoadCapacity(700).build();

    private static MilitaryPlane militaryPlaneB_1B_Lancer = new MilitaryPlane.MilitaryPlaneBuilder(MilitaryType.BOMBER).
            setMaxFlightDistance(1050).setMaxSpeed(21000).setModel("B-1B Lance").setMaxLoadCapacity(80000).build();

    private static MilitaryPlane militaryPlane1B_2_Spirit = new MilitaryPlane.MilitaryPlaneBuilder(MilitaryType.BOMBER).
            setMaxFlightDistance(1030).setMaxSpeed(22000).setModel("B-2 Spirit").setMaxLoadCapacity(70000).build();

    private static MilitaryPlane militaryPlaneB_52_Stratofortress = new MilitaryPlane.MilitaryPlaneBuilder(MilitaryType.BOMBER).
            setMaxFlightDistance(1000).setMaxSpeed(20000).setModel("B-52 Stratofortress").setMaxLoadCapacity(8000).build();

    private static MilitaryPlane militaryPlaneF_15 = new MilitaryPlane.MilitaryPlaneBuilder(MilitaryType.FIGHTER).
            setMaxFlightDistance(1500).setMaxSpeed(12000).setModel("F-15").setMaxLoadCapacity(10000).build();

    private static MilitaryPlane militaryPlane130_HerculesV = new MilitaryPlane.MilitaryPlaneBuilder(MilitaryType.TRANSPORT).
            setMaxFlightDistance(650).setMaxSpeed(5000).setModel("C-130 Hercules").setMaxLoadCapacity(11000).build();


    static List<Plane> planes = Arrays.asList(
            passengerPlaneBoeing_737,
            passengerPlaneBoeing_737_800,
            passengerPlaneBoeing_747,
            passengerPlaneAirbus_A330,
            militaryPlane1B_2_Spirit,
            militaryPlane130_HerculesV,
            militaryPlaneB_1B_Lancer,
            militaryPlaneB_52_Stratofortress,
            militaryPlaneF_15
    );

    public static void main(String[] args) {
        Airport airport = new Airport(planes);
        Airport militaryAirport = new Airport(airport.getMilitaryPlanes());
        Airport passengerAirport = new Airport(airport.getPassangerPlanes());
        System.out.println("Military airport sorted by max distance: " + militaryAirport
                .sortByMaxDistance()
                .toString());
        System.out.println("Passenger airport sorted by max speed: " + passengerAirport
                .sortByMaxSpeed()
                .toString());
        System.out.println("Plane with max passenger capacity: " + passengerAirport.getPassengerPlaneWithMaxPassengersCapacity());
    }
}
