/*
 * An Intcode program is a list of integers separated by commas (like 1,0,0,3,99). To run one, start by looking at the first integer (called position 0). Here, you will find an opcode - either 1, 2, or 99. The opcode indicates what to do; for example, 99 means that the program is finished and should immediately halt. Encountering an unknown opcode means something went wrong.

Opcode 1 adds together numbers read from two positions and stores the result in a third position. The three integers immediately after the opcode tell you these three positions - the first two indicate the positions from which you should read the input values, and the third indicates the position at which the output should be stored.

For example, if your Intcode computer encounters 1,10,20,30, it should read the values at positions 10 and 20, add those values, and then overwrite the value at position 30 with their sum.

Opcode 2 works exactly like opcode 1, except it multiplies the two inputs instead of adding them. Again, the three integers after the opcode indicate where the inputs and outputs are, not their values.

Once you're done processing an opcode, move to the next one by stepping forward 4 positions.

For example, suppose you have the following program:

1,9,10,3,2,3,11,0,99,30,40,50
For the purposes of illustration, here is the same program split into multiple lines:

1,9,10,3,
2,3,11,0,
99,
30,40,50
The first four integers, 1,9,10,3, are at positions 0, 1, 2, and 3. Together, they represent the first opcode (1, addition), the positions of the two inputs (9 and 10), and the position of the output (3). To handle this opcode, you first need to get the values at the input positions: position 9 contains 30, and position 10 contains 40. Add these numbers together to get 70. Then, store this value at the output position; here, the output position (3) is at position 3, so it overwrites itself. Afterward, the program looks like this:

1,9,10,70,
2,3,11,0,
99,
30,40,50
Step forward 4 positions to reach the next opcode, 2. This opcode works just like the previous, but it multiplies instead of adding. The inputs are at positions 3 and 11; these positions contain 70 and 50 respectively. Multiplying these produces 3500; this is stored at position 0:

3500,9,10,70,
2,3,11,0,
99,
30,40,50
Stepping forward 4 more positions arrives at opcode 99, halting the program.

Here are the initial and final states of a few more small programs:

1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).
2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
Once you have a working computer, the first step is to restore the gravity assist program (your puzzle input) to the "1202 program alarm" state it had just before the last computer caught fire. To do this, before running the program, replace position 1 with the value 12 and replace position 2 with the value 2. What value is left at position 0 after the program halts?
 */
package adventofcodeday2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventOfCodeDay2 {

    public static void main(String[] args) {

        //part1();
        part2();

    }

    private static void run(ArrayList<Integer> program) {
        int opcode = 0; //the command to execute
        int index = 0;//the current position in the program
        int sum, product;
        //keep running the program until we reach the end command
        while (opcode != 99) {
            opcode = program.get(index);
            if (opcode == 1) {
                /*
                Opcode 1 adds together numbers read from two positions 
                and stores the result in a third position. The three 
                integers immediately after the opcode tell you these 
                three positions - the first two indicate the positions 
                from which you should read the input values, and the 
                third indicates the position at which the output should be stored.
                 */
                sum = program.get(program.get(index + 1)) + program.get(program.get(index + 2));

                program.set(program.get(index + 3), sum);

            } else if (opcode == 2) {
                /*
                Opcode 2 works exactly like opcode 1, except it multiplies 
                the two inputs instead of adding them. Again, the three 
                integers after the opcode indicate where the inputs and 
                outputs are, not their values.
                 */
                product = program.get(program.get(index + 1)) * program.get(program.get(index + 2));
                program.set(program.get(index + 3), product);

            }
            //System.out.println("Index: " + index + " Opcode:" + opcode);
            //print(program);
            //move to next opcode
            index += 4;

        }
    }

    private static void print(ArrayList<Integer> program) {
        for (int i = 0; i < program.size(); i++) {
            System.out.print(program.get(i) + ",");
        }
    }

    private static void part1() {
        ArrayList<Integer> program = new ArrayList();

        try {
            File f = new File("src\\adventofcodeday2\\input");
            Scanner s = new Scanner(f);
            //the whole program is on one line separated by commas
            //read it in and split into String array
            String[] sProgram = s.nextLine().split(",");
            //test programs to see if my computer works
            //String[] sProgram = "1,1,1,4,99,5,6,0,99".split(",");
            //turn the String array into an ArrayList of Integers
            for (int i = 0; i < sProgram.length; i++) {
                program.add(Integer.parseInt(sProgram[i]));
            }
            //make modifications to program
            //replace position 1 with the value 12 and replace position 2 with the value 2.
            program.set(1, 12);
            program.set(2, 2);
            run(program);

            print(program);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }

    private static void part2() {
        ArrayList<Integer> program = new ArrayList();

        try {
            File f = new File("src\\adventofcodeday2\\input");
            Scanner s = new Scanner(f);
            //the whole program is on one line separated by commas
            //read it in and split into String array
            String[] sProgram = s.nextLine().split(",");
            //test programs to see if my computer works
            //String[] sProgram = "1,1,1,4,99,5,6,0,99".split(",");
            //turn the String array into an ArrayList of Integers
            reset(program, sProgram);
            //make modifications to program
            //try all potential noun's and verbs until we find the output 19690720
            for (int noun = 0; noun < 100; noun++) {
                for (int verb = 0; verb < 100; verb++) {
                    program.set(1, noun);
                    program.set(2, verb);
                    run(program);
                    if (program.get(0) == 19690720) { //winner winner
                        System.out.println(100 * noun + verb); //print the answer
                        //stop the loops
                        noun = 100;
                        verb = 100;
                    } else {
                        reset(program, sProgram);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }

    
    private static void reset(ArrayList<Integer> program, String[] sProgram) {
        program.clear();
        //turn the String array into an ArrayList of Integers
        for (int i = 0; i < sProgram.length; i++) {
            program.add(Integer.parseInt(sProgram[i]));
        }
    }

}
