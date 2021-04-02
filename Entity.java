package Lab2;

import java.util.List;
import java.util.Objects;

public class Entity {
    private static int idCounter = 1;
    protected final long id;
    protected World world;
    protected String title;
    protected double posX;
    protected double posZ;
    protected boolean agressive;
    protected int maxHealth;
    protected int health;
    protected int attackDamage;
    protected Entity target;
    protected boolean deth = false;

    public Entity(World world, String title, double posX, double posZ, boolean agressive, int maxHealth, int health, int attackDamage) {
        this.world = world;
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.agressive = agressive;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attackDamage = attackDamage;
        this.id = idCounter++;
    }

    public void update() {
        if(agressive)
        {
            if(target == null)
                searchTarget();
            if(target != null)
            {
                double distance = (Math.pow((target.posX-this.posX),2)+Math.pow((target.posZ-this.posZ),2));
                if(distance >= 4)
                {
                    if(this.posX != target.posX)
                    {
                        this.posX += (target.posX-this.posX)/(Math.abs(target.posX-this.posX));
                    }
                    if(this.posZ != target.posZ)
                    {
                        this.posZ += (target.posZ-this.posZ)/(Math.abs(target.posZ-this.posZ));
                    }
                }
                else
                    attack(target);
            }
        }
    }

    public void searchTarget() {
        List<Entity> targetEntity = world.getEntitiesNearEntity(this,20);
        for(int i = targetEntity.size() - 1; i >= 0; i--)
        {
            if(!targetEntity.get(i).agressive)
            {
                target = targetEntity.get(i);
                break;
            }
        }
    }

    public void attack(Entity entity){
        if(entity instanceof EntityPlayer)
        {
            if(!entity.deth)
            {
                entity.health -= this.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();
                if(entity.health > 0)
                {
                    this.health -= entity.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();
                }
                else
                {
                    System.out.println("Player " + ((EntityPlayer) entity).getNickname() + " was killed by " + this.title + " on server update " + GameServer.getInstance().getServerTicks());
                    entity.deth = true;
                    this.target = null;
                }
                if(this.health <= 0)
                {
                    this.deth = true;
                    System.out.println("Player " + ((EntityPlayer) entity).getNickname() + " killed entity " + this.title + " on server update " + GameServer.getInstance().getServerTicks());
                }
            }

        }
        else
        {
            if(!entity.deth)
            {
                entity.health -= this.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();
                if(entity.health <= 0)
                {
                    entity.deth = true;
                    this.target = null;
                    System.out.println("Entity " + this.title + " killed entity " + entity.title + " on server update " + GameServer.getInstance().getServerTicks());
                }
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id && Double.compare(entity.posX, posX) == 0 && Double.compare(entity.posZ, posZ) == 0 && agressive == entity.agressive && maxHealth == entity.maxHealth && health == entity.health && attackDamage == entity.attackDamage && Objects.equals(world, entity.world) && Objects.equals(title, entity.title) && Objects.equals(target, entity.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, world, title, posX, posZ, agressive, maxHealth, health, attackDamage, target);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", agressive=" + agressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                ", target=" + target +
                '}';
    }
}

