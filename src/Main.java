import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        int OpsCount = Integer.parseInt(reader.readLine().trim());
        String[] input = reader.readLine().trim().split(" ");
        List<Integer> list = new ArrayList<>();
        for (String num : input) {
            list.add(Integer.parseInt(num));
        }

        AVL tree = new AVL();
        for (int i = 0; i < list.size(); i++) {
            tree.insert(i, list.get(i));
        }

        int p = 0;
        for (int i = 0; i < OpsCount; i++) {

            int x = tree.getValue(p);
            try {
                if (x % 2 == 1) {

                    tree.updateIndexes(tree.root, p + 1, 1);
                    tree.insert(p + 1, x - 1);
                    p = (p + x) % tree.totalnum;
                } else {

                    x = tree.getValue((p + 1) % tree.totalnum);
                    int temp =(p + 1) % tree.totalnum;
                    tree.delete((p + 1) % tree.totalnum);

                    if ((p + 1) % tree.totalnum != 0)
                        tree.updateIndexes(tree.root, (p + 1) % tree.totalnum, -1);

                    if(temp==0)
                        p--;

                    p = (p + x) % tree.totalnum;
                }
            } catch (ArithmeticException a) {
                //when the tree is empty I just skip anything that requires dividing by length
            }
        }

        int i=p;
        ArrayList<Integer> a = tree.preorderget();
        if(a.size()!=0) {
            do {
                System.out.print(a.get(i) + " ");
                i++;
                i %= a.size();

            } while (i != p);
        }

    }
}