package RMI;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) {
        
        try {
            
            ServerIntf serverIntf = (ServerIntf) Naming.lookup("rmi://localhost/server");
            System.out.println("addition is : " + serverIntf.add(23, 32));

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("exception in client" + e.getMessage()   );
        }
    }
}
