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
    PassengerPlane plane1 = new PassengerPlane.PassengerPlaneBuilder(500).setMaxFlightDistance(200).
            setMaxLoadCapacity(900).setMaxSpeed(800).setModel("model").build();
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);


    @Test
    public void testGetTransportMilitaryPlanes() {
        Assert.assertEquals(hasTransportMilitaryType(MilitaryType.TRANSPORT), true);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        System.out.println("TEST testGetPassengerPlaneWithMaxCapacity started!");
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(expectedPlaneWithMaxPassengersCapacity.equals(planeWithMaxPassengerCapacity));
    }

    @Test
    public void testNextPlaneMaxLoadCapacityIsHigherThanCurrent() {
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent());
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        if (!hasAtLeastOneBomberInMilitaryPlanes(MilitaryType.BOMBER)){
            Assert.fail("Test failed!");
        }
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
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
        for(ExperimentalPlane experimentalPlane : experimentalPlanes){
            if(experimentalPlane.getClassificationLevel() == classificationLevel){
                hasClassifiedPlanes = true;
                break;
            }
        }
        return hasClassifiedPlanes;
    }

    private boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent(){
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

    private boolean hasAtLeastOneBomberInMilitaryPlanes(MilitaryType type){
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        boolean flag = false;
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if ((militaryPlane.getMilitaryType() == type)) {
                flag = true;
            }
        }
        return flag;
    }
}
