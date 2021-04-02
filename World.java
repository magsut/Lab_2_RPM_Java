package Lab2;

import org.jetbrains.annotations.NotNull;

import java.util. *;

public class World
{
    private int id;
    private String worldName;
    List<Entity> entities = new ArrayList<Entity>();

    public World(int id, String worldName) {
        this.id = id;
        this.worldName = worldName;
    }


    public void updateWorld()
    {
        for(Entity e : entities)
        {
            e.update();
        }
        for(int i = entities.size() - 1; i >= 0; i--)
        {
            if(entities.get(i).deth)
            {
                entities.remove(i);
            }
        }
    }

    public List<Entity> getEntitiesInRegion(double x, double z, double range)
    {
        List<Entity> sortList = new ArrayList<Entity>();
        double distance = Math.pow(range, 2);

        for(Entity e : entities)
        {
            double distanceToEntity = Math.pow((e.posX - x), 2) + Math.pow((e.posZ - z), 2);
            if (distanceToEntity <= distance)
            {
                sortList.add(e);
            }
        }
        List<SortEntities> sortEntities = new ArrayList<SortEntities>();
        for(Entity e : sortList)
        {
            sortEntities.add(new SortEntities(e,Math.pow((e.posX - x), 2) + Math.pow((e.posZ - z), 2)));
        }

        Collections.sort(sortEntities, new Comparator<SortEntities>() {
            @Override
            public int compare(SortEntities o1, SortEntities o2) {
                return Double.compare(o2.getDistance(), o1.getDistance());
            }
        });
        sortList.clear();

        for(SortEntities e : sortEntities)
        {
            sortList.add(e.getEntity());
        }

        return sortList;
    }


    public List<Entity> getEntitiesNearEntity(@NotNull Entity entity, double range)
    {
        return getEntitiesInRegion(entity.posX,entity.posZ,range);
    }

    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    @Override
    public String toString() {
        return "World{" +
                "id=" + id +
                ", worldName='" + worldName + '\'' +
                ", entities=" + entities +
                '}';
    }
}
