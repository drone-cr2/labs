import mpi.*;

public class HelloWorldMPI {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        System.out.println("Hello from rank " + rank + " of " + size);
        MPI.Finalize();
    }
}

/*  Enter MPJ_HOME as the Variable name.
    Enter the path to your MPJ Express installation directory (e.g., C:\MPJExpress) as the Variable value.
    the System variables section, find the Path variable and select it.
    Click Edit....
    Click New and add %MPJ_HOME%\bin. */

// javac -cp "%MPJ_HOME%\lib\mpj.jar" HelloWorldMPI.java
// mpjrun -np 4 HelloWorldMPI