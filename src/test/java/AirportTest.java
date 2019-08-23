import airoport.Airport;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {

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

    private static ExperimentalPlane experimentalPlaneBell_X14 = new ExperimentalPlane.ExperimentalPlaneBuilder(ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET).
            setMaxFlightDistance(277).setMaxSpeed(482).setModel("C-130 Hercules").setMaxLoadCapacity(500).build();

    private static ExperimentalPlane experimentalPlaneRyan_X13_Vertijet = new ExperimentalPlane.ExperimentalPlaneBuilder(ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET).
            setMaxFlightDistance(560).setMaxSpeed(307).setModel("C-130 Hercules").setMaxLoadCapacity(500).build();


    static List<Plane> planes = Arrays.asList(
            passengerPlaneBoeing_737,
            passengerPlaneBoeing_737_800,
            passengerPlaneBoeing_747,
            passengerPlaneAirbus_A330,
            militaryPlane1B_2_Spirit,
            militaryPlane130_HerculesV,
            militaryPlaneB_1B_Lancer,
            militaryPlaneB_52_Stratofortress,
            militaryPlaneF_15,
            experimentalPlaneBell_X14,
            experimentalPlaneRyan_X13_Vertijet
    );

    @Test
    public void testGetTransportMilitaryPlanes() {
        Assert.assertEquals(hasTransportMilitaryType(MilitaryType.TRANSPORT), true);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        System.out.println("TEST testGetPassengerPlaneWithMaxCapacity started!");
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(expectedPlaneWithMaxPassengersCapacity.equals(passengerPlaneBoeing_747));
    }

    @Test
    public void testNextPlaneMaxLoadCapacityIsHigherThanCurrent() {
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent());
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        if (!hasAtLeastOneBomberInMilitaryPlanes(MilitaryType.BOMBER)) {
            Assert.fail("Test failed!");
        }
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified() {
        Assert.assertFalse(hasClassificationLevel(ClassificationLevel.UNCLASSIFIED));
    }

    private boolean hasTransportMilitaryType(MilitaryType militaryType) {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> militaryPlanes = airport.getTransportMilitaryPlanes();
        boolean hasMilitaryType = false;
        for (MilitaryPlane militaryPlane : militaryPlanes) {
            if ((militaryPlane.getMilitaryType() == militaryType)) {
                hasMilitaryType = true;
                break;
            }
        }
        return hasMilitaryType;
    }

    private boolean hasClassificationLevel(ClassificationLevel classificationLevel) {
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();
        boolean hasClassifiedPlanes = false;
        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            if (experimentalPlane.getClassificationLevel() == classificationLevel) {
                hasClassifiedPlanes = true;
                break;
            }
        }
        return hasClassifiedPlanes;
    }

    private boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        return nextPlaneMaxLoadCapacityIsHigherThanCurrent;
    }

    private boolean hasAtLeastOneBomberInMilitaryPlanes(MilitaryType type) {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        boolean flag = false;
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if ((militaryPlane.getMilitaryType() == type)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
