import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * JumperBug can jump two spaces at a time, but it can jump only over a rock,
 * a flower, or a blank space and only onto a flower or blank space. Otherwise,
 * it acts like a normal bug and moves one space or turns until it can move.
 * 
 * @author Alex Yu
 * @version September 22, 2016
 */
public class JumperBug extends Bug
{
    /**
     * Construcs a JumperBug.
     */
    public JumperBug()
    {
    }

    /**
     * Jumps if able; if not, then acts like a normal bug until then. 
     */
    public void act()
    {
        if(canJump())
        {
            jump();
        }
        else
        {
            super.act();
        }
    }

    /**
     * Jumps two spaces in front without leaving a trail of flowers.
     * 
     * @precondition    the jump is a legal jump
     */
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
        {
            return;
        }
        Location loc = getLocation();
        Location adj = loc.getAdjacentLocation(getDirection()); 
        Location twoAway = adj.getAdjacentLocation(getDirection());
        moveTo(twoAway);
    }

    /**
     * Moves one space forward without leaving a trail of flowers.
     * 
     * @precondition    the move is a legal move
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
        {
            return;
        }
        Location loc = getLocation();
        Location adj = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(adj))
        {
            moveTo(adj);
        }
    }

    /**
     * Checks if there is an empty space, flower, or rock directly in front
     *  and if there is an empty space or flower two spaces ahead.
     * 
     * @return  true if the spaces in front are valid to jump; otherwise,
     *          false 
     */
    public boolean canJump()
    {
        Grid gr = getGrid();
        int dir = getDirection();
        Location loc = getLocation();

        Location adj = loc.getAdjacentLocation(dir);
        Location twoAway = adj.getAdjacentLocation(dir);

        if(gr.isValid(adj)&&gr.isValid(twoAway))
        {
            Actor neighbor = (Actor) gr.get(adj);
            if(neighbor == null || neighbor instanceof Flower || neighbor instanceof Rock)
            {
                Actor neighborsAdj = (Actor) gr.get(twoAway);
                if(neighborsAdj == null || neighborsAdj instanceof Flower)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
