import java.util.*;

class Elevator {

    private Scanner scanner = new Scanner(System.in);
    private Deque<Integer> elevator = new ArrayDeque<>(); // set of floors in a queue

    private int MAX_FLOOR = 25; // assign maximum floor

    private int waitDoorsInSeconds = 10;
    private int waitMoveInSeconds = 5;
    private int totalSeconds = 0;
    private int previousFloor = -1;
    private int currentFloor = 1;

    // main method to operate elevator
    void getElevator() {
        elevator.add(1); // the elevator is located on the 1 floor from the beginning
        System.out.printf("Current floor: %d floor\n", elevator.getFirst());

        while (true) {
            System.out.println("Waiting for number: (enter 0 to end)");
            try {
                String input = scanner.nextLine(); // user input
                int floor = Integer.parseInt(input);

                // input validation for count floors
                if (floor < 0 || floor > MAX_FLOOR) {
                    System.out.println("this floor doesn't exist");
                } else if (floor == 0) {
                    System.out.println("Elevator followed: ");

                    for (Integer floors : elevator) {
                        System.out.printf("floor %d -> ", floors);
                    }
                    System.out.printf("floor %d ", floor);

                    // calling a method to count total time for the route
                    countTotalTime();
                    break;
                } else if (floor == 1) {
                    elevator.poll(); // special case to remove first floor not to print it out 2 times
                    elevator.add(floor);
                } else {
                    elevator.add(floor); // saving floors input in list
                }
            } catch (Exception e) {
                System.out.println("wrong input");
            }
        }
    }

    private void countTotalTime() {
        while (!elevator.isEmpty()) { // while the elevator is not empty
            currentFloor = elevator.remove();
            if (previousFloor != -1) {
                totalSeconds += Math.abs(currentFloor - previousFloor) * waitMoveInSeconds;
                totalSeconds += waitDoorsInSeconds;
            }
            previousFloor = currentFloor;
        }
        System.out.println("\nThe time spent by the elevator on the route: " + totalSeconds + " s.");
    }
}