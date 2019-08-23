package planes;

import models.ClassificationLevel;
import models.ExperimentalTypes;

public class ExperimentalPlane extends Plane {

    private ExperimentalTypes experimentalTypes;
    private ClassificationLevel classificationLevel;

    private ExperimentalPlane(ExperimentalPlaneBuilder builder) {
        super(builder.model, builder.maxSpeed, builder.maxFlightDistance, builder.maxLoadCapacity);
        this.experimentalTypes = builder.experimentalTypes;
        this.classificationLevel = builder.classificationLevel;
    }

    public static class ExperimentalPlaneBuilder {
        private ExperimentalTypes experimentalTypes;
        private ClassificationLevel classificationLevel;
        private String model;
        private int maxSpeed;
        private int maxFlightDistance;
        private int maxLoadCapacity;

        public ExperimentalPlaneBuilder(ExperimentalTypes experimentalTypes,ClassificationLevel classificationLevel){
            this.experimentalTypes = experimentalTypes;
            this.classificationLevel = classificationLevel;
        }

        public ExperimentalPlaneBuilder setModel(String model){
            this.model = model;
            return this;
        }

        public ExperimentalPlaneBuilder setMaxSpeed(int maxSpeed){
            this.maxSpeed = maxSpeed;
            return this;
        }

        public ExperimentalPlaneBuilder setMaxFlightDistance(int maxFlightDistance){
            this.maxFlightDistance = maxFlightDistance;
            return this;
        }

        public ExperimentalPlaneBuilder setMaxLoadCapacity(int maxLoadCapacity){
            this.maxLoadCapacity = maxLoadCapacity;
            return this;
        }

        public ExperimentalPlane build(){
            return new ExperimentalPlane(this);
        }
    }

    public ClassificationLevel getClassificationLevel() {
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel) {
        this.classificationLevel = classificationLevel;
    }

    public ExperimentalTypes getExperimentalTypes() {
        return experimentalTypes;
    }

    @Override
    public String toString() {
        return "experimentalPlane{" +
                "model='" + model + '\'' +
                '}';
    }
}
