package Lab2;

public class SortEntities {
    private Entity entity;
    private double distance;

    public SortEntities(Entity entity, double distance) {
        this.entity = entity;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public Entity getEntity() {
        return entity;
    }
}
