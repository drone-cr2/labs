package RMI;

import java.rmi.Naming;
// import ServerImpl.*; 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        
        try {
            Registry registry = LocateRegistry.createRegistry(1099); // default port
            // Using LocateRegistry.createRegistry(1099) ensures the registry is started automatically by your server
            ServerImpl serverImpl = new ServerImpl();
            Naming.rebind("server", serverImpl);

            System.out.println("server running ...");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("exeption in sever : " + e.getMessage());
        }
    }
}
