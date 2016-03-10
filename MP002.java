
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class MP002 {

    boolean pattern (ArrayList a) {
        for (int k = 0; k < a.size(); k++){
            if (a.get(0)==a.get(k))
                return true;
        }
        return false;
    }

    boolean chekMod (ArrayList a) {
        Object[] array = a.toArray();
        int i = array.length;

        if (i % 2 == 1)
            return true;
        else return false;
    }

    char[] exy (String s) {
        char[] some = new char[s.length()];
        for (int i =0; i< s.length(); i++)
            some[i] = s.charAt(i);
        return some;
    }

    public static void main(String[] args) {

        MP002 ax = new MP002();
        char [] array;
        String input = null;

        String acc_array_1 = "RNCRLNR";
        String acc_array_2 = "RNLRCNR";

        String path;

        try {


			System.out.println("Enter the path(root) of your mp2.in");
			Scanner scan = new Scanner(System.in);
			path = scan.nextLine();
            File myFile = new File (path);
            FileReader fileReader = new FileReader(myFile);

            BufferedReader reader = new BufferedReader(fileReader);
            int i = 0;

            while ((input = reader.readLine())!=null) {
                array = ax.exy(input);

                //Eliminate multiple loops
                ArrayList ar = new ArrayList();
                for (int k = 0; k < array.length; k++)
                    ar.add(array[k]);

                Stack er = new Stack();
                er.add(ar.get(0));
                try {

                    for (int v = 1; v < ar.size(); v++) {
                    if (er.peek() == ar.get(v)) {
                        //int x = i+1;
                        er.pop();
                         er.push(ar.get(v++));
                    }
                    else
                        er.push(ar.get(v));
                    }
                }
                    catch (EmptyStackException ex){
                            //ex.printStackTrace();
                }

                //Eliminate multiple edges
                Object[] array1 = er.toArray();
                ArrayList a = new ArrayList();
                ArrayList b = new ArrayList();

                for (int k = 1; k < array1.length; k++){
                    if (array1[k]== (Object)'C') {
                        a.add(k);
                    }
                    else if (array1[k]==(Object)'L') {
                        b.add(k);
                    }
                }

                    //System.out.println(" "+ar.toString());
                    if (er.toString() == acc_array_1 || er.toString() == acc_array_2)
                        System.out.println("OK");

                    else if (a.size()==1 && b.size()==1)
                        System.out.println("NG");

                    else if (ax.pattern(a)&&ax.pattern(b)&&ax.chekMod(a)&&ax.chekMod(b))
                        System.out.println("OK");
                    else
                        System.out.println("NG");
                    i++;
            }

                reader.close();
            }

        catch (Exception ex) {
            ex.printStackTrace();
        }
   }

}

