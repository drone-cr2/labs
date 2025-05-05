import java.util.*;

public class BerkeleyAlgorithm {

    static class Node {
        String name;
        int clockTime; // In seconds (or any unit)
        int timeOffset = 0; // Offset to be applied

        Node(String name, int clockTime) {
            this.name = name;
            this.clockTime = clockTime;
        }

        void applyOffset() {
            clockTime += timeOffset;
        }

        @Override
        public String toString() {
            return name + " time: " + clockTime + " (offset: " + timeOffset + ")";
        }
    }

    public static void main(String[] args) {
        // Sample nodes with different clock times (in seconds)
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("Server", 203));
        nodes.add(new Node("Client1", 195));
        nodes.add(new Node("Client2", 197));
        nodes.add(new Node("Client3", 209));

        System.out.println("Before synchronization:");
        for (Node node : nodes) {
            System.out.println(node);
        }

        synchronizeClocks(nodes);

        System.out.println("\nAfter synchronization:");
        for (Node node : nodes) {
            node.applyOffset();
            System.out.println(node);
        }
    }

    public static void synchronizeClocks(List<Node> nodes) {
        int totalTime = 0;
        int n = nodes.size();

        // Step 1: Server collects time from all nodes (including itself)
        for (Node node : nodes) {
            totalTime += node.clockTime;
        }

        // Step 2: Server calculates average
        int averageTime = totalTime / n;

        // Step 3: Calculate offset for each node
        for (Node node : nodes) {
            node.timeOffset = averageTime - node.clockTime;
        }
    }
}

/*
### 🔄 **Berkeley Algorithm (Brief Overview)**

The **Berkeley Algorithm** is a distributed clock synchronization algorithm used in systems where 
no node has a perfectly accurate clock (unlike protocols like NTP which rely on an authoritative 
time source).

#### 🧠 How it works:

1. **Coordinator (server)** polls all participating machines for their local time.
2. Each machine replies with its clock time.
3. The coordinator estimates network delay and calculates the **average** time.
4. It sends each machine the amount of **adjustment** (offset) needed.
5. Each machine (including the server) applies the offset to synchronize clocks.

📝 **Note:** The coordinator usually excludes outliers to reduce the impact of faulty clocks.

---

### ✅ **Benefits**

* **No reliance on external time source** (e.g., GPS or atomic clock).
* Works well in **closed distributed systems** like clusters or data centers.
* **Averages** clock times, so all nodes meet in the middle — promoting fairness.
* Tolerant to individual clock drifts or minor faults.

---

### 📌 **Applications**

* **Distributed databases** and systems requiring time-based coordination (e.g., transactions).
* **Sensor networks** or IoT environments where no central time authority exists.
* **Multiplayer games** and simulations requiring synchronized event timing.
* **Cloud clusters** where nodes must coordinate logs, events, and data consistency.

---

Would you like a visual diagram of how the synchronization process flows?

 */
