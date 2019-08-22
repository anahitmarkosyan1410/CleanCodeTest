package airoport;
import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;

/**
 * version: 1.1
 * made by Vitali Shulha
 * 4-Jan-2019
 */

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassangerPlanes() {
        return getListOfClass(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getListOfClass(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getListOfClass(ExperimentalPlane.class);
    }

    public <K> List<K> getListOfClass(Class<?> clas) {
        List<? extends Plane> listPlanes = this.planes;
        List<K> classTypePlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (clas.isInstance(plane)) {
                classTypePlanes.add((K) plane);
            }
        }
        return classTypePlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassangerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanesByMilitaryType(MilitaryType.TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
       return getMilitaryPlanesByMilitaryType(MilitaryType.BOMBER);
    }

    public List<MilitaryPlane> getMilitaryPlanesByMilitaryType(MilitaryType militaryType) {
        List<MilitaryPlane> militaryPlanesByType = new ArrayList<MilitaryPlane>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getMilitaryType() == militaryType) {
                militaryPlanesByType.add(plane);
            }
        }
        return militaryPlanesByType;
    }

    public Airport sortByMaxDistance() {
        planes.sort(Comparator.comparing(Plane::getMaxFlightDistance));
        return this;
    }

   public Airport sortByMaxSpeed() {
        planes.sort(Comparator.comparing(Plane::getMaxSpeed));
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparing(Plane::getMaxLoadCapacity));
        return this;
    }

    private void print(Collection<? extends Plane> collection) {
        Iterator<? extends Plane> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
