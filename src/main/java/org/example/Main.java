package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    /**
     * main is responsible for gathering and cleaning data then calling the functions necessary to return an output.
     * @param args These are the inputs from console
     */
    public static void main(String[] args) {
        List<String> directions = new ArrayList<>();
        Integer carsInside=0;
        String[] incomingDirections = {"T","R","B","L"};
        if(args.length == 4){
            for (int i=0 ; i<args.length; i++) {
                if (Objects.equals(args[i], "_")) {
                    directions.add("_");
                }else{
                    if(String.valueOf(args[i].charAt(0)).equals("A")){
                        directions.add("_");
                        System.out.print(incomingDirections[i]);  // ambulance , they go in this order:  top, right, bottom , left
                    }
                    if(String.valueOf(args[i].charAt(0)).equals("C")){
                        directions.add(String.valueOf(args[i].charAt(1)));
                        carsInside++;
                    }
                }
            }
            List<Integer> finalResult = ComplexSolver(directions,carsInside);
            for (Integer integer : finalResult) {
                System.out.print(incomingDirections[integer]);
            }
        }else{
            System.out.print("incorrect input");
        }
    }

    /**
     * 
     * @param directions This parameter is used to keep track of the car movements, if a car is good to go then his directions is set to "_".
     * @param carsInside This parameter will iterate the algorithm till all the cars are good to go.
     * @return  Integer List with the result where the appropriate number is assigned to the spot of the car.It is then used to decode it into the output in main.
     */
    private static List<Integer> ComplexSolver( List<String> directions, Integer carsInside){
        List<Integer> result = new ArrayList<>();
        while(carsInside!=0) {
            boolean somebodyMoved = false;
            for (int i = 0; i < directions.size(); i++) {  // if cars can go independently then they go in this order: top, right, bottom , left
                if (Objects.equals(directions.get(i), "R")) {  // car turns right
                    result.add(i);
                    directions.set(i, "_");
                    somebodyMoved= true;
                    carsInside--;
                }
                if (Objects.equals(directions.get(i), "S")) { // car goes straight
                    if (ForwardSolver(directions, i)) {
                        result.add(i);
                        directions.set(i, "_");
                        somebodyMoved= true;
                        carsInside--;
                    }
                }
                if (Objects.equals(directions.get(i), "L")) {   //car turns left
                    if (LeftSolver(directions, i)) {
                        result.add(i);
                        directions.set(i, "_");
                        somebodyMoved= true;
                        carsInside--;
                    }
                }
            }
            if(!somebodyMoved){  // if no one can move then we let go one in this order: top, right, bottom , left
                for (int i = 0; i < directions.size(); i++){
                    if(!Objects.equals(directions.get(i), "_")){
                        result.add(i);
                        directions.set(i, "_");
                        carsInside--;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 
     * @param directions This parameter is used to keep track of the movement of the cars and used to calculate if a car can move.
     * @param spotOfCar This parameter is used to make sure the direction list doesn't go out of bounds.
     * @return a boolean value based on whether the car is free to go forward or not
     */
    private static boolean ForwardSolver(List<String> directions, int spotOfCar){
        if(spotOfCar !=0){   //car goes forward
            return Objects.equals(directions.get(spotOfCar - 1), "_");
        }
        else{
            return Objects.equals(directions.get(directions.size() - 1), "_");
        }
    }

    /**
     *
     * @param directions This parameter is used to keep track of the movement of the cars and used to calculate if a car can move.
     * @param spotOfCar This parameter is used to make sure the direction list doesn't go out of bounds.
     * @return a boolean value based on whether the car is free to go forward or not
     */
    private static boolean LeftSolver(List<String> directions, int spotOfCar){
        if( spotOfCar  >= 2){   //car turns left meets car on right
            return Objects.equals(directions.get(spotOfCar - 1), "_")  && !Objects.equals(directions.get(spotOfCar-2),"S") && !Objects.equals(directions.get(spotOfCar-2),"R");
        }
        if(spotOfCar  == 0){
            return Objects.equals(directions.get(directions.size() - 1), "_") && !Objects.equals(directions.get(2),"S") && !Objects.equals(directions.get(2),"R");
        }
        if(spotOfCar  == 1){
            return Objects.equals(directions.get(0), "_") && !Objects.equals(directions.get(3), "S") && !Objects.equals(directions.get(3), "R");
        }
        return false;
    }
}