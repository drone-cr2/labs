import java.util.*;

public class BullyAlgorithmSimplified {
    static int n;
    static int[] processes;
    static int failedCoordinator;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        processes = new int[n];

        System.out.println("Enter process IDs:");
        for (int i = 0; i < n; i++) {
            processes[i] = sc.nextInt();
        }

        System.out.print("Enter the ID of the failed coordinator: ");
        failedCoordinator = sc.nextInt();

        System.out.print("Enter the ID of the process that detects the failure: ");
        int initiator = sc.nextInt();

        startElection(initiator);

        sc.close();
    }

    static void startElection(int initiator) {
        System.out.println("\nProcess " + initiator + " starts election.");

        int newCoordinator = initiator;
        for (int p : processes) {
            if (p > initiator && p != failedCoordinator) {
                System.out.println("Election message sent from " + initiator + " to " + p);
                System.out.println("Process " + p + " responds.");
                if (p > newCoordinator) {
                    newCoordinator = p;
                }
            }
        }

        System.out.println("\nProcess " + newCoordinator + " becomes the new coordinator.");
    }
}
