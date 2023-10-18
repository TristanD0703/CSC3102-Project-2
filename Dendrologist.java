package dendrologist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A testbed for an augmented implementation of an AVL tree
 * @author William Duncan, YOUR NAME
 * @see AVLTreeAPI, AVLTree
 * <pre>
 * Date: 99-99-9999
 * Course: csc 3102 
 * Programming Project # 2
 * Instructor: Dr. Duncan 
 * </pre>
 */
public class Dendrologist
{
    public static void main(String[] args) throws FileNotFoundException, AVLTreeException 
    {
        String usage = "Dendrologist <order-code> <command-file>\n";
        usage += "  <order-code>:\n";
        usage += "  0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  -1 for reverse lexicographical order\n";
        usage += "  1 for lexicographical order\n";
        usage += "  -2 ordered by decreasing string\n";
        usage += "  2 ordered by increasing string\n";
        usage += "  -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  3 ordered by increasing string length, primary key, and lexicographical order, secondary key\n";      
        if (args.length != 2)
        {
            System.out.println(usage);
            throw new IllegalArgumentException("There should be 2 command line arguments.");
        }
        Comparator<String> cmp = null;

        switch (Integer.parseInt(args[0])){
            case 1: cmp = (current, other) -> current.compareTo(other); break;
            case -1: cmp = (current, other) -> -1 * current.compareTo(other); break;
            case 2: cmp = (current, other) -> current.length() - other.length(); break;
            case -2: cmp = (current, other) -> -1 * (current.length() - other.length()); break;
            case 3: 
                cmp = (current, other) -> {
                    if(current.length() == other.length())
                        return current.length() - other.length();
                    return current.compareTo(other);
                };
                break;
            case -3: 
                cmp = (current, other) -> {
                    if(current.length() == other.length())
                        return -1 * (current.length() - other.length());
                    return -1 * current.compareTo(other);
                };
                break;
            default: System.out.println(usage);
                    throw new IllegalArgumentException("Invalid sort code given.");
        }

        AVLTree<String> tree = new AVLTree<String>(cmp);
        Scanner input = new Scanner(new File(args[1]));
        while(input.hasNextLine()){
            String[] commands = input.nextLine().split(" ");

            if(commands[0].toLowerCase().equals("insert")){
                if(commands.length >= 2){
                    tree.insert(commands[1]);
                    System.out.println("Inserted " + commands[1]);
                }
                else {
                    throw new IllegalArgumentException("parsing error");
                }
            } else if(commands[0].toLowerCase().equals("remove")){
                if(commands.length >= 2){
                    tree.remove(commands[1]);
                    System.out.println("Removed " + commands[1]);
                }
                else {
                    throw new IllegalArgumentException("parsing error");
                }
            } else if(commands[0].toLowerCase().equals("props")){
                tree.postorderTraverse((node) -> System.out.println(node.data));
            }

        }

        input.close();
        
        //complete the implementation of this method



    }   
}
