import mpi.*;
import java.util.Arrays;

public class ArraySumMPI {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Example array
        int n = data.length;
        int elementsPerProcessor = n / size;
        int remainder = n % size;

        int[] localData;
        int localSum = 0;

        // Determine the portion of the array for the current processor
        int startIndex = rank * elementsPerProcessor + Math.min(rank, remainder);
        int endIndex = startIndex + elementsPerProcessor + (rank < remainder ? 1 : 0);

        localData = Arrays.copyOfRange(data, startIndex, endIndex);

        // Calculate the local sum
        for (int value : localData) {
            localSum += value;
        }

        System.out.println("Processor " + rank + " calculated local sum: " + localSum + " (elements: " + Arrays.toString(localData) + ")");

        int[] allSums = new int[size];
        MPI.COMM_WORLD.Gather(new int[]{localSum}, 0, 1, MPI.INT, allSums, 0, 1, MPI.INT, 0);

        if (rank == 0) {
            int totalSum = 0;
            for (int sum : allSums) {
                totalSum += sum;
            }
            System.out.println("Total sum calculated by " + size + " processors: " + totalSum);
        }

        MPI.Finalize();
    }
}

// javac -cp "%MPJ_HOME%\lib\mpj.jar" ArraySumMPI.java
// mpjrun -np 4 ArraySumMPI