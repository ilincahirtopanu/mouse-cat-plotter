import java.util.Random;

public class Cat extends Creature {
    private int timer; // till eats mouse, 50 moves till it dies
                       // if cat eats mouse, timer resets

    public Cat(int x, int y, City cty, Random rnd) {
        super(x, y, cty, rnd);
        stepLen = 2;
        lab = 'y';
        timer = 0;
    }

    @Override
    public void takeAction() {
        lab = 'y';
        // loops through the list of creatures in the city
        for (Creature c : city.creatures) {

            // if the distance of the mouse is less 20/less than the closest
            if (dist(c) < 20 && c instanceof Mouse && dist(c) != 0) {
                // set it equal to the closest int
                chase(c);
            }
            //if cat is on the location
            else if(dist(c) == 0 && c instanceof Mouse) {
                c.dead = true;
                timer = 0;
                lab = 'c';
                return;
            }
        }
    }

    public void step() {
        if (timer == 50) {
            this.dead = true;
        }

        timer++;
        
        // Cats randomly turn, change direction, 5% of the time.
        if (rand.nextInt(100) < 5) {
            randomTurn();
        }
        
        super.step();
        // Cats jump two spaces at a time. They do not traverse the grid point they jump
        // over. That is, if they are on space (1,2) they would move to (1,4).

    }

    private void chase(Creature c) {
        lab = LAB_CYAN;
        // setDir to the direction that the creature is going
        findDirection(c);
    }

    private void findDirection(Creature c) {
        //if it's closer vertically than horizontally
        if (Math.abs(this.getY() - c.getY()) > Math.abs(this.getX() - c.getX())) {
            
            //if the creature's Y is going south
            if (c.getY() > this.getY()) {
                this.setDir(2);
            }
            else {
                //creatures Y is north
                this.setDir(0);
            }
        }
        //if it's closer horizontally than vertically
        else {
            if (c.getX() > this.getX()) {
                //the creature's X is going east
                this.setDir(1);
            }
            else {
                this.setDir(3);
            }
        }
    } 

}
