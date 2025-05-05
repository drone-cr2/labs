import java.util.*;

public class RingAlgorithmSimplified {
    static int n;
    static int[] processes;
    static int failedProcess;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        processes = new int[n];

        System.out.println("Enter process IDs in ring order:");
        for (int i = 0; i < n; i++) {
            processes[i] = sc.nextInt();
        }

        System.out.print("Enter the ID of the failed coordinator: ");
        failedProcess = sc.nextInt();

        System.out.print("Enter the ID of the process that detects failure: ");
        int initiatorID = sc.nextInt();
        int initiator = -1;

        sc.close();

        for (int i = 0; i < n; i++) {
            if (processes[i] == initiatorID) {
                initiator = i;
                break;
            }
        }
        if (initiator == -1) {
            System.out.println("Error: Process ID not found.");
            return;
        }
        
        startElection(initiator);
    }

    static void startElection(int initiator) {
        System.out.println("\nProcess " + processes[initiator] + " starts election.");
        int maxId = processes[initiator];
        int i = (initiator + 1) % n;

        while (i != initiator) {
            if (processes[i] != failedProcess) {
                System.out.println("Message passed to process " + processes[i]);
                if (processes[i] > maxId) {
                    maxId = processes[i];
                }
            } else {
                System.out.println("Process " + processes[i] + " is down. Skipped.");
            }
            i = (i + 1) % n;
        }

        System.out.println("\nProcess " + maxId + " is elected as the new coordinator.");
    }
}
