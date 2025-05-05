class TokenRing {
    private final int totalProcesses;
    private int tokenHolder;

    public TokenRing(int totalProcesses) {
        this.totalProcesses = totalProcesses;
        this.tokenHolder = 0; // initially, process 0 has the token
    }

    // Simulate passing the token and entering the critical section
    public synchronized void requestCS(int processId) {
        while (processId != tokenHolder) {
            try {
                wait(); // wait until this process gets the token
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Critical Section
        System.out.println("Process " + processId + " is in the Critical Section.");

        try {
            Thread.sleep(1000); // simulate work in the critical section
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Pass the token to the next process in the ring
        tokenHolder = (tokenHolder + 1) % totalProcesses;
        System.out.println("Process " + processId + " has passed the token to Process " + tokenHolder);

        notifyAll(); // notify others waiting for the token
    }
}

// Process thread
class Process extends Thread {
    private final int id;
    private final TokenRing ring;

    public Process(int id, TokenRing ring) {
        this.id = id;
        this.ring = ring;
    }

    @Override
    public void run() {
        while (true) {
            ring.requestCS(id);

            try {
                Thread.sleep(10000); // delay before trying again
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

// Main class
public class TokenRingSimulation {
    public static void main(String[] args) {
        int numberOfProcesses = 5; // example number
        TokenRing ring = new TokenRing(numberOfProcesses);

        for (int i = 0; i < numberOfProcesses; i++) {
            new Process(i, ring).start();
        }
    }
}
/*
Absolutely! Let’s break down the first response code **line by line** for a better understanding.

### Full Code Overview:

In the original response, I provided a Java program simulating the **Token Ring Mutual Exclusion** algorithm using **shared memory** (synchronization). Here’s the breakdown:

### 1. **TokenRing Class Definition**

```java
class TokenRing {
    private final int totalProcesses;
    private int tokenHolder;
```

* **`class TokenRing`**: This defines a class called `TokenRing`. A class in Java is a blueprint for creating objects.

* **`private final int totalProcesses;`**: This is a private field that holds the total number of processes in the token ring. It is declared `final` to ensure it is not modified once initialized.

* **`private int tokenHolder;`**: This variable keeps track of which process currently holds the token. The token is passed around in the ring, and this variable keeps track of its holder.

---

### 2. **Constructor of TokenRing**

```java
    public TokenRing(int totalProcesses) {
        this.totalProcesses = totalProcesses;
        this.tokenHolder = 0; // initially, process 0 has the token
    }
```

* **`public TokenRing(int totalProcesses)`**: This is the constructor of the `TokenRing` class. It takes an integer parameter `totalProcesses`, which specifies the number of processes in the ring.

* **`this.totalProcesses = totalProcesses;`**: The constructor initializes the `totalProcesses` field of the `TokenRing` object to the value passed in by the constructor.

* **`this.tokenHolder = 0;`**: This initializes the `tokenHolder` to `0`, meaning process `0` initially holds the token. The token will circulate in the ring.

---

### 3. **Requesting Critical Section (requestCS Method)**

```java
    public synchronized void requestCS(int processId) {
```

* **`public synchronized void requestCS(int processId)`**: This method is used by a process to request access to the critical section. The `synchronized` keyword ensures that only one process can execute this method at a time, preventing race conditions (important in concurrent environments).

* **`int processId`**: The method takes `processId` as a parameter, which tells which process is calling this method (i.e., which process is requesting the critical section).

---

### 4. **Waiting for the Token**

```java
        while (processId != tokenHolder) {
            try {
                wait(); // wait until this process gets the token
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
```

* **`while (processId != tokenHolder)`**: This loop ensures that the process waits until it has the token. If the current process is not the token holder, it will wait.

* **`wait();`**: This is a method used to pause the current thread until it is notified. If the process doesn't have the token, it will wait for another process to notify it.

* **`catch (InterruptedException e)`**: If the thread is interrupted while waiting, an `InterruptedException` is caught, and `Thread.currentThread().interrupt();` ensures that the interrupt flag is set on the thread, which is good practice.

---

### 5. **Critical Section**

```java
        // Critical Section
        System.out.println("Process " + processId + " is in the Critical Section.");
        
        try {
            Thread.sleep(1000); // simulate work in the critical section
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
```

* **Critical Section**: This is where the process actually enters the critical section and does its work. Here, it prints a message that it's in the critical section.

* **`Thread.sleep(1000);`**: This simulates the work being done in the critical section by making the thread sleep for 1 second (1000 milliseconds). In a real scenario, this would be the code where actual work is performed.

* **`catch (InterruptedException e)`**: Again, if the thread is interrupted while sleeping, it catches the exception and resets the interrupt flag.

---

### 6. **Passing the Token**

```java
        tokenHolder = (tokenHolder + 1) % totalProcesses;
        System.out.println("Process " + processId + " has passed the token to Process " + tokenHolder);
        
        notifyAll(); // notify others waiting for the token
    }
```

* **`tokenHolder = (tokenHolder + 1) % totalProcesses;`**: This updates the `tokenHolder` to the next process in the ring. The modulus operator (`% totalProcesses`) ensures that once the last process (process `totalProcesses-1`) holds the token, it passes it back to process `0`, maintaining the circular nature of the ring.

* **`System.out.println("Process " + processId + " has passed the token to Process " + tokenHolder);`**: This prints a message indicating that the process has passed the token to the next process.

* **`notifyAll();`**: This method notifies all other processes that may be waiting for the token. Once the current process is done, other processes that are waiting for the token are allowed to proceed.

---

### 7. **Process Thread Class**

```java
class Process extends Thread {
    private final int id;
    private final TokenRing ring;

    public Process(int id, TokenRing ring) {
        this.id = id;
        this.ring = ring;
    }

    @Override
    public void run() {
        while (true) {
            ring.requestCS(id);
            try {
                Thread.sleep(2000); // delay before trying again
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
```

* **`class Process extends Thread`**: This is a class that represents each process as a thread. It extends the `Thread` class to create a new thread of execution for each process.

* **`private final int id;`**: This stores the `id` of the process (i.e., process `0`, process `1`, etc.).

* **`private final TokenRing ring;`**: This holds the reference to the `TokenRing` object, allowing the process to request access to the critical section.

* **`public Process(int id, TokenRing ring)`**: The constructor initializes the `id` of the process and the `ring` to which the process belongs.

* **`@Override public void run()`**: This method is called when the thread starts. It runs in an infinite loop, continuously requesting access to the critical section via `ring.requestCS(id)` and then waiting for 2 seconds (to simulate some delay before the process tries again).

---

### 8. **Main Class**

```java
public class TokenRingSimulation {
    public static void main(String[] args) {
        int numberOfProcesses = 5; // example number
        TokenRing ring = new TokenRing(numberOfProcesses);

        for (int i = 0; i < numberOfProcesses; i++) {
            new Process(i, ring).start();
        }
    }
}
```

* **`public class TokenRingSimulation`**: This is the main class of the program, which contains the `main` method to run the simulation.

* **`int numberOfProcesses = 5;`**: This specifies the number of processes in the ring (5 in this case). You can adjust this number to simulate a different size token ring.

* **`TokenRing ring = new TokenRing(numberOfProcesses);`**: This creates a new `TokenRing` object, initializing it with the number of processes.

* **`for (int i = 0; i < numberOfProcesses; i++) { new Process(i, ring).start(); }`**: This loop creates and starts a new thread for each process. Each `Process` is passed its `id` and the `TokenRing` object. The `start()` method causes the thread to begin execution.

---

### Final Thoughts

The program simulates a **token ring** system in which processes request access to a critical section in a synchronized manner, ensuring only one process accesses the critical section at a time. This is achieved by passing a "token" around the ring, and each process waits for the token before entering the critical section.

Let me know if you need any further clarification!

 */