import java.util.Random;

public class Mouse extends Creature {
    int roundCount;

    public Mouse(int x, int y, City cty, Random rnd) {
        super(x, y, cty, rnd);
        roundCount = 0;
        lab = 'b';
        stepLen = 1;
    }

    @Override
    public void step() {
        // randomly changing direction 20% of the time
        if (roundCount == 22) {
            this.dead = true;
            return;
        }

        roundCount++;

        if (rand.nextInt(10) < 2) {
            randomTurn();
        }
        super.step();
    }

    @Override
    public void takeAction() {
        // produces a baby after 20 rounds
        if (roundCount == 20) {
            // initializes a mouse in the same place as the mother
            Mouse child = new Mouse(getX(), getY(), city, rand);
            // adds the child to the queue
            city.creaturesToAdd.add(child);
        }
    }
}
