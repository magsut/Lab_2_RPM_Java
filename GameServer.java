package Lab2;

public class GameServer {
    private String ip;
    private int difficulty;
    private World serverWorld;
    private int serverTicks = 0;
    private static GameServer instance;

    public static void main(String[] args)
    {
        GameServer lab2 = new GameServer();
    }

    public GameServer() {
        instance = this;

        ip = "228.228.228";

        difficulty = 1;

        World world = new World(1,"Lab");

        Entity Zombie1 = new Entity(world,"Zombie1", 0, 0, true, 140, 40, 10);
        Entity Skeleton = new Entity(world,"Skeleton", 55, 55, true, 80, 80, 10);
        Entity Pig = new Entity(world,"Pig1", 3, 3, false, 20, 20, 0);
        Entity Human = new EntityPlayer(world,"Human", 65, 63, 100, 100, 20, "lololoshka");
        Entity Zombie2 = new Entity(world,"Zombie2", -2, 2, true, 40, 2,5);
        Entity Pig1 = new Entity(world,"Pig2",-1,2,false,20,20,0);

        world.addEntity(Zombie1);
        world.addEntity(Skeleton);
        world.addEntity(Pig);
        world.addEntity(Human);
        world.addEntity(Zombie2);
        world.addEntity(Pig1);
        instance.serverWorld = world;

        for(int i = 0; i <= 30;i++)
        {
            System.out.println("Update " + instance.serverTicks + ": ");
            System.out.println(instance.serverWorld);
            updateServer();
            try {
                Thread.sleep(220);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateServer()
    {
        serverWorld.updateWorld();
        serverTicks++;
    }



    public int getDifficulty() { return difficulty; }

    public static GameServer getInstance() { return instance; }

    public int getServerTicks() {
        return serverTicks;
    }
}
