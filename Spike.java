/**
 *
 * @author Krisophobic
 */
import java.io.*;
import java.util.LinkedList;

public class Spike

{
    String[] instructions = new String[50];
    int[] register = new int [10];
    int pointer = 1;

    public void theFile ()

    {

       String[] input_1 = new String[50];
       String path = "C://Users/Administrator.KrisellAbella/Documents/input.in";

       try

       {

           //Extract data from the input file
           File myFile = new File (path);
           FileReader fileReader = new FileReader(myFile);
           BufferedReader reader = new BufferedReader(fileReader);
           String line = null;
           int i = 0;

           while ((line = reader.readLine())!=null)

           {
               input_1[i] = line;
               i=i+1;
           }

           //Save instructions as array of strings
           for (int j = 0; j < input_1.length; j++)

           {
               instructions[j] = input_1[j];
           }

           //Save register as array of integers
           int reg_count = 0;
           for (int x = 0; x < input_1[0].length(); x++)

           {
               if (input_1[0].charAt(x)!= ' ' && input_1[0].charAt(x)!= ',')

               {
                   register[reg_count] = charToInt(input_1[0].charAt(x));
                   reg_count = reg_count +1;
               }
           }

           reader.close();
        }

        catch (Exception ex)

        {
            ex.printStackTrace();
        }

    }

    public void display_reg()

    {
        for (int i = 0; i < register.length; i++)
            System.out.print("| "+register[i]+" |");

        System.out.println();
    }

    public void display_ptr()

    {
        System.out.println("Instruction: "+pointer);
    }

    public void display_ins()

    {
        int i = 0;
        while (instructions[i] != null)

        {
            System.out.println(instructions[i]);
            i=i+1;
        }
    }

    private int charToInt(char a)

    {
        LinkedList p = new LinkedList();
        p.add(a);
        return Integer.parseInt(p.toString().substring(1,2));
    }

    public String[] toStrAr (String s)

    {
        String [] array = new String[s.length()];
        int count = 0;
        int j = 0;
        int i;

        for (i = 0; i < s.length(); i++)

        {
            if (s.charAt(i)==' ' )

            {
                array[count] = s.substring(j, i);
                count++;
                j = i+1;
            }
        }

        array[count]=s.substring(j, s.length());
        return array;
    }


    public void display_sample ()

    {
        String a = instructions[1];
        String[] array = new String[a.length()];
        array = toStrAr(instructions[1]);

        for (int i = 0; i < a.length(); i++)
            System.out.println(array[i]);


        System.out.println();
    }

    public void zero ()

    {
        display_ptr();
        String a = instructions[pointer];
        String b = toStrAr(a)[1];
        register[Integer.parseInt(b)]=0;
        display_reg();
        pointer = pointer + 1;
    }

    public void succeed ()

    {
        display_ptr();
        String a = instructions[pointer];
        String b = toStrAr(a)[1];
        //register[Integer.parseInt(s[1])]=register[Integer.parseInt(s[1])]+1;
        register[Integer.parseInt(b)]=register[Integer.parseInt(b)]+1;
        display_reg();
        pointer = pointer + 1;
    }

    public void copy ()

    {
        display_ptr();
        String a = instructions[pointer];
        String b = toStrAr(a)[1];
        String c = toStrAr(a)[2];
        //register[Integer.parseInt(s[2])] = register[Integer.parseInt(s[1])];
        register[Integer.parseInt(c)]=register[Integer.parseInt(b)];
        display_reg();
        pointer = pointer + 1;
    }

    public void jump ()

    {
        display_ptr();
        String a = instructions[pointer];
        String b = toStrAr(a)[1];
        String c = toStrAr(a)[2];
        String d = toStrAr(a)[3];

            if (register[Integer.parseInt(b)]==register[Integer.parseInt(c)]) {

               pointer = Integer.parseInt(d);
               System.out.println("jump to "+pointer);
               //intoRegistry();
            }

            else {
                System.out.println("no jump");
                pointer = pointer + 1;
            }
            display_reg();
    }

    public void intoRegistry ()

    {

        String a = instructions[pointer];
        String b = toStrAr(a)[0];

        if (b.charAt(0) == 'Z')
            zero();

        else if (b.charAt(0) == 'S')
            succeed();

        else if (b.charAt(0) == 'C')
            copy();

        else if (b.charAt(0) == 'J')
            jump();

    }

    public void commands () throws NullPointerException

    {
        try {

            while (pointer < instructions.length ) {
                intoRegistry();
                System.out.println();
            }
        }

        catch (NullPointerException ex) {

        }

    }

    public static void main(String[] args)

    {
		Spike sample = new Spike();
		sample.theFile();
		sample.commands();
		System.out.println("The system succefully terminated.");

	}
}
