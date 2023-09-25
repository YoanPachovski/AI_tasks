import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        int a;
        ArrayList<Integer> initial = new ArrayList<Integer>();
        Scanner S=new Scanner(System.in);
           a=S.nextInt();

        for (int i = 0; i < a+1; ++i) {
        }
        initial.add(2);
        initial.add(3);
        initial.add(6);
        initial.add(1);
        initial.add(5);
        initial.add(8);
        initial.add(4);
        initial.add(7);
        initial.add(0);
        //Collections.shuffle(initial);
        ArrayList<Integer> goal = new ArrayList<Integer>();
        int b;
        b=S.nextInt();
        if (b==-1) {
            for (int i = 1; i < a + 1; ++i) {
                goal.add(i);
            }
            goal.add(0);
        }

        else if (b>=0&&b<a+2)
        {
            for (int i = 0; i < a + 1; ++i) {
                if (i==b){
                    goal.add(0);
                    if (b!=0)
                    {
                    goal.add(i);
                }
                }
                else {
                goal.add(i);
            }
            }
        }
        else if (b>=a+2)
        {
            System.out.println("Invalid Number");
            System.exit(0);
        }


         System.out.println(initial.size());
         System.out.println(goal.size());
        State state;
        state = new State(initial.size(), 0, "beginning", initial,goal);
        System.out.println(state.toString());
        Solver solver = new Solver(state);
        solver.theIDAStarSearch();
        //за 3на3 дъска намира някво решение но пътя който намира винаге започва с up
        //down
        //up
        //up
        //up
        //left
        //up и тн.
        //за 4н4 чаках дс намери решение 5 мин нищо не намери
        //за 5на5 само ще бучи компютъра докато не гръмне
        //о то тоя сайт гледах за имплементация
        //https://algorithmsinsight.wordpress.com/graph-theory-2/ida-star-algorithm-in-general/
        //горе долу почти същотото е
        //не знам защо не става

    }
}
