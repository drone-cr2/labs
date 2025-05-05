import java.util.ArrayList;
import java.util.List;

public class trial {
    
    static class Machine {
        String name;
        int time;
        int offset = 0;

        Machine(String name, int time){
            this.time = time;
            this.name = name;
        }

        public void setOffset(int avgTime){
            this.offset = avgTime - time;
        }

        public void syncTime(){
            time += offset;
        }

        public String print(){
            return name + "time is " + time + "( offset : " + offset + ")";
        }
        
    }

    public static void sync(List<Machine> machines){

        int totalTime = 0;
        int totalMachines = machines.size();

        for (Machine machine : machines) {
            totalTime += machine.time;
        }

        int avgTime = totalTime / totalMachines;

        for (Machine machine : machines) {
            machine.setOffset(avgTime);
        }
    }

    public static void main(String[] args) {
        
        List<Machine> machines = new ArrayList<>();
        machines.add(new Machine("Server", 87));
        machines.add(new Machine("C1", 85));
        machines.add(new Machine("C2", 89));
        machines.add(new Machine("C3", 84));

        System.out.println("before sync");
        for (Machine machine : machines) {
            System.out.println(machine.print());
        }

        sync(machines);

        System.out.println("after sync");
        for (Machine machine : machines) {
            machine.syncTime();
            System.out.println(machine.print());
        }
    }
}
