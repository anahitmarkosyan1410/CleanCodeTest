package planes;

import models.MilitaryType;

import java.util.Objects;

public class MilitaryPlane extends Plane {

    private MilitaryType militaryType;

    private MilitaryPlane(MilitaryPlaneBuilder builder) {
        super(builder.model, builder.maxSpeed, builder.maxFlightDistance, builder.maxLoadCapacity);
        this.militaryType = builder.militaryType;
    }

    public static class MilitaryPlaneBuilder {
        private MilitaryType militaryType;
        private String model;
        private int maxSpeed;
        private int maxFlightDistance;
        private int maxLoadCapacity;

        public MilitaryPlaneBuilder(MilitaryType militaryType){
            this.militaryType = militaryType;
        }

        public MilitaryPlaneBuilder setModel(String model){
            this.model = model;
            return this;
        }

        public MilitaryPlaneBuilder setMaxSpeed(int maxSpeed){
            this.maxSpeed = maxSpeed;
            return this;
        }

        public MilitaryPlaneBuilder setMaxFlightDistance(int maxFlightDistance){
            this.maxFlightDistance = maxFlightDistance;
            return this;
        }

        public MilitaryPlaneBuilder setMaxLoadCapacity(int maxLoadCapacity){
            this.maxLoadCapacity = maxLoadCapacity;
            return this;
        }

        public MilitaryPlane build(){
            return new MilitaryPlane(this);
        }
    }

    public MilitaryType getMilitaryType() {
        return militaryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MilitaryPlane)) return false;
        if (!super.equals(o)) return false;
        MilitaryPlane militaryPlane = (MilitaryPlane) o;
        return militaryType == militaryPlane.militaryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), militaryType);
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", type=" + militaryType +
                        '}');
    }
}
