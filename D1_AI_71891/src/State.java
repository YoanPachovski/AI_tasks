import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collections;


public class State {
    private final int size;
    private final int depth;
    private final String direction;
    private final int scoreOfF;
    private final int emptySpace;
    private ArrayList<Integer> state = new ArrayList<Integer>();
    private ArrayList<Integer> goal = new ArrayList<Integer>();


    State(int size, int depth, String direction, ArrayList<Integer> state, ArrayList<Integer> goal){
        this.size = size;
        this.depth = depth;
        this.direction = direction;
        this.state = state;
        this.goal = goal;
        this.scoreOfF = this.getScoreOfF();
        this.emptySpace = this.state.indexOf(0);
    }
    public int getDepth() { return depth; }
    public String getDirection() {
        return direction;
    }
    int getScoreOfF(){ return this.depth + this.getManhattanDistance(); }
    boolean isGoal() {
        return this.state.equals(this.goal);
    }


    int getManhattanDistance(){
        int manhattan = 0;
        int sizeOfN=(int)Math.sqrt(this.size);
        for(int i = 0 ; i < this.size; i++){
            if(this.state.get(i) != 0){
                int goal_index = this.goal.indexOf(this.state.get(i));
                manhattan = manhattan + Math.abs((i / sizeOfN) - (goal_index / sizeOfN)) + Math.abs((i % sizeOfN)-(goal_index % sizeOfN)) ;
            }
        }
        return manhattan;
    }
    int getInversions()
    {
        int inv_count = 0;
        for (int i = 0; i < state.size() - 1; i++) {
            for (int j = i + 1; j < state.size(); j++) {
                if (state.get(i) > state.get(j) && state.get(i) != 0 && state.get(j)!=0)
                    inv_count++;
            }
        }
        return inv_count;
    }
    boolean isSolvable(){
        int inversionsCount = this.getInversions();
        int sizeOfN=(int)Math.sqrt(this.size);
        if(sizeOfN % 2 == 1 && inversionsCount % 2 == 0)//check if size is odd and inversion is even
        {
            return true;
        }
        int rowOfEmptySpace=this.emptySpace/sizeOfN+1;
        if(sizeOfN % 2 == 0 && (inversionsCount+rowOfEmptySpace) % 2 == 1)//check if size is even and inversion+row of empty tile is odd
        {
            return true;
        }
        return false;
    }
    ArrayList<Integer> move(int x, int y){
        int sizeOfN=(int)Math.sqrt(this.size);
        int real_size = this.size - 1;
        ArrayList<Integer> state = this.state;
        if(((this.emptySpace + x*sizeOfN + y) > real_size) || ((this.emptySpace + x*sizeOfN+y) < 0) || ((this.emptySpace%sizeOfN + y) < 0) || ((this.emptySpace%sizeOfN + y >= sizeOfN))){
            return new ArrayList<Integer>();
        }
        int firstPositon = this.emptySpace + x*sizeOfN + y;
        Collections.swap(state, firstPositon, this.emptySpace);
        return state;
    }
    ArrayList<State> ReturnSolvableChildren(){
        ArrayList<State> children = new ArrayList<>();
        ArrayList<Integer> up = this.move(+1, 0);
        ArrayList<Integer> down = this.move(-1, 0);
        ArrayList<Integer> left = this.move(0, +1);
        ArrayList<Integer> right = this.move(0, -1);
        if(up.size() != 0){
            children.add(new State(this.size, this.depth + 1, "up", up, this.goal));
        }
        if(down.size() != 0){
            children.add(new State(this.size, this.depth + 1, "down", down, this.goal));

        }
        if(left.size() != 0){
            children.add(new State(this.size, this.depth + 1, "left", left, this.goal));

        }
        if(right.size() != 0){
            children.add(new State(this.size, this.depth + 1, "right", right, this.goal));
        }
        return children;
    }

    @Override
    public String toString() {
        for (int j = 0; j < state.size(); j++)
        {
            System.out.print(state.get(j) + " ");
            if ((j+1)%Math.sqrt(state.size())==0)
            {
                System.out.println();
            }
        }
        System.out.println();
        for (int j = 0; j < goal.size(); j++)
        {
            System.out.print(goal.get(j) + " ");
            if ((j+1)%Math.sqrt(goal.size())==0)
            {
                System.out.println();
            }
        }
        return "These are the two boards";
    }
}
