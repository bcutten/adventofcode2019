/*
 * uel required to launch a given module is based on its mass. 
Specifically, to find the fuel required for a module, 
take its mass, divide by three, round down, and subtract 2.

For example:

For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
For a mass of 1969, the fuel required is 654.
For a mass of 100756, the fuel required is 33583.
The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed for the mass of each module (your puzzle input), then add together all the fuel values.

What is the sum of the fuel requirements for all of the modules on your spacecraft?
 */
package adventofcodeday1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdventOfCodeDay1 {

    public static void main(String[] args) {
        try {
            File f = new File("src\\adventofcodeday1\\input");
            Scanner s = new Scanner(f);
            int sum = 0;
            int mass;
            //read in all masses from the file
            while (s.hasNextLine()) {
                mass = Integer.parseInt(s.nextLine());
                //calculate the fuel required for this module and add it to the total
                sum += (mass / 3) - 2;
            }
            //final amount of fuel required
            System.out.println("Total: " + sum);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }

}
