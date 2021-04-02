package Lab2;

import java.util.Objects;

public class EntityPlayer extends Entity{

    private String nickname;

    public EntityPlayer(World world, String title, double posX, double posZ,  int maxHealth, int health, int attackDamage, String nickname) {
        super(world, title, posX, posZ, false, maxHealth, health, attackDamage);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void update() {
        super.update();
        if(GameServer.getInstance().getServerTicks()%2 == 1)
        {
            if(this.health < this.maxHealth)
            {
                this.health++;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityPlayer that = (EntityPlayer) o;
        return Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nickname);
    }

    @Override
    public String toString() {
        return "EntityPlayer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", agressive=" + agressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                ", target=" + target +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
