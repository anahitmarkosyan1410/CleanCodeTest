package planes;

import java.util.Objects;

public class PassengerPlane extends Plane {
    private int passengersCapacity;

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    private PassengerPlane(PassengerPlaneBuilder builder) {
        super(builder.model, builder.maxSpeed, builder.maxFlightDistance, builder.maxLoadCapacity);
        this.passengersCapacity = builder.passengersCapacity;
    }

    public static class PassengerPlaneBuilder {
        private int passengersCapacity;
        private String model;
        private int maxSpeed;
        private int maxFlightDistance;
        private int maxLoadCapacity;

        public PassengerPlaneBuilder(int passengersCapacity){
            this.passengersCapacity = passengersCapacity;
        }

        public PassengerPlaneBuilder setModel(String model){
            this.model = model;
            return this;
        }

        public PassengerPlaneBuilder setMaxSpeed(int maxSpeed){
            this.maxSpeed = maxSpeed;
            return this;
        }

        public PassengerPlaneBuilder setMaxFlightDistance(int maxFlightDistance){
            this.maxFlightDistance = maxFlightDistance;
            return this;
        }

        public PassengerPlaneBuilder setMaxLoadCapacity(int maxLoadCapacity){
            this.maxLoadCapacity = maxLoadCapacity;
            return this;
        }

        public PassengerPlane build(){
            return new PassengerPlane(this);
        }
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerPlane)) return false;
        if (!super.equals(o)) return false;
        PassengerPlane plane = (PassengerPlane) o;
        return passengersCapacity == plane.passengersCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity=" + passengersCapacity +
                        '}');
    }
}
