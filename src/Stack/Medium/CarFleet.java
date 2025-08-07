package Stack.Medium;
//There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
//
//        You are given two integer array position and speed, both of length n, where position[i] is the starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.
//
//        A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.
//
//        A car fleet is a car or cars driving next to each other. The speed of the car fleet is the minimum speed of any car in the fleet.
//
//        If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.
//
//        Return the number of car fleets that will arrive at the destination.
//
//
//        Example 1:
//
//        Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
//
//        Output: 3
//
//        Explanation:
//
//        The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms at target.
//        The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
//        The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
//
//        Example 2:
//
//        Input: target = 10, position = [3], speed = [3]
//
//        Output: 1
//
//        Explanation:
//        There is only one car, hence there is only one fleet.
//
//        Example 3:
//
//        Input: target = 100, position = [0,2,4], speed = [4,2,1]
//
//        Output: 1
//
//        Explanation:
//
//        The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The car starting at 4 (speed 1) travels to 5.
//        Then, the fleet at 4 (speed 2) and the car at position 5 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
//
//
//
//        Constraints:
//
//        n == position.length == speed.length
//        1 <= n <= 105
//        0 < target <= 106
//        0 <= position[i] < target
//    All the values of position are unique.
//            0 < speed[i] <= 106


import java.util.*;

public class CarFleet {

    public static void main(String[] args) {

        CarFleet start = new CarFleet();
        int[] position = {6,8};
        int[] speed = {3,2};
        System.out.println(start.carFleet(10, position, speed));

    }

//    Conditions for car fleet merge
//	1. If Car A position > Car B position && Car B speed > Car A && arrival time of Car B <= arrival time of Car A then merge will occur
//    a. More simply: If Car A position > Car B position && Car A arrival time  >= Car B arrival time then merge will occur
//    b. The same can be said vice-versa: Merge is true If Car A position < Car B position && Car A arrival time <= Car B arrival time.
//  2. Car A position = Car B position.

    // Watch neetcode vid for clear explanation

    //RESULT: 70ms beat 68.56% - not bad
    public int carFleet(int target, int[] position, int[] speed) {

        int n = position.length;
        // Grouping the position and speed together in a class called Car
        // Then create an array of cars
        Car[] cars = new Car[n];

        for (int i = 0; i < n; i++) {
            Car car = new Car(position[i], speed[i]);
            car.arrivalTime = (target - car.position) / (double) car.speed;
            cars[i] = car;
        }

        // sort car array
        Arrays.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o2.position - o1.position;           // sorts the array from largest position to smallest - reverse order
                                                            // the Comparator will swap if compare() returns > 1, else it won't swap.
            }
        });

        Stack<Car> carFleet = new Stack<>();

        for (Car currCar : cars) {

            if (carFleet.isEmpty()) {
                carFleet.add(currCar);
                continue;
            }

            // compare current car to last car - NOTE if car fleet merges DO NOTHING
            Car lastCar = carFleet.peek();
            if (currCar.arrivalTime > lastCar.arrivalTime) {
                // car fleet doesn't merge - new car fleet is born
                carFleet.push(currCar);
            }
        }

        return carFleet.size();
    }

    public static class Car {
        public int position;
        public int speed;
        public double arrivalTime;      // NOTE: double because it could be in fractions

        public Car() {
            position = 0;
            speed = 0;
            arrivalTime = 0;
        }

        public Car(int position, int speed) {
            this.position = position;
            this.speed = speed;
        }
    }

    // Conditions for joining carfleet:
    // 1. car arrival time (assuming no obstruction) <= carfleet's arrival time.
    // 2. car position < carfleet's position, then car will join the car fleet.
    // NOTE: The only position that matters in a carfleet is the car with the highest initial position in that fleet.

    public int carFleet2(int target, int[] position, int[] speed) {
        int len = position.length;
        double[] arrivalTime = new double[len];

        Stack<double[]> carFleet = new Stack<>();

        for (int i = 0; i < len; i++)  {
            double[] car = {        // position, time
                    position[i],
                    (target - position[i]) / ((double) speed[i])
            };

            if (i == 0) {
                carFleet.push(car);
                continue;
            }

            double[] prevCarFleet = carFleet.peek();

            if (car[0] < prevCarFleet[0] && car[1] <= prevCarFleet[1]) {
                // both cars become same fleet and prev car's position matter.
                continue;
            }
            else if (car[0] > prevCarFleet[0] && car[1] >= prevCarFleet[1]) {
                // current car starts closer to target but arrives slower, both car still joins same fleet
                // but position and arrival time of fleet is changed

                carFleet.pop();
                carFleet.push(car);
            }
            else if (car[0] < prevCarFleet[0] && car[1] > prevCarFleet[1]) {
                // new car fleet
                carFleet.push(car);
            }

            //TODO: Sort the position[] array in order and still retaining the respective speed array for this method to work.
            // Probly need to create a car object and add those objects in a list and sort it in descending order.
        }

        return 0;
    }
}
