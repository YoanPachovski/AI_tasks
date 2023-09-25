import java.util.ArrayList;


public class Solver {
        private final State root;
        Solver(State root)
        {
            this.root = root;
        }
        void theIDAStarSearch(){
        if (this.root.isSolvable()) {
            int threshold = this.root.getScoreOfF();
            while (true) {
                int temp = this.search(this.root,0, threshold);
                if (temp == -1) {
                    System.out.println("SOLVED");
                    return;
                }
                threshold = temp;
            }
        }
        else
            {
            System.out.println("Not Solvable");
            System.exit(0);
            }

    }
    int search(State node,int g,int threshold){
           int f = node.getScoreOfF();
           if(f > threshold){
                return f;
            }
            if(node.isGoal()){
                System.out.println("Number of moves for optimal path " + g );
                return -1;
            }
            int min = Integer.MAX_VALUE;
            for (State child:nextNodes(node)) {
                int temp = this.search(child,g+1,threshold);
                if(temp == -1){
                    System.out.println(child.getDirection());
                    return -1;
                }
                if(temp < min){
                    min = temp;
                }
            }
            return min;
        }
    ArrayList<State> nextNodes(State node)
    {
       return node.ReturnSolvableChildren();
    }
}
