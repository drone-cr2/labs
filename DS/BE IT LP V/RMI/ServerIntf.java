package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIntf extends Remote {

    public double add(double num1,double num2) throws RemoteException;
    public double sub(double num1,double num2) throws RemoteException;
    public double mul(double num1,double num2) throws RemoteException;
    public double div(double num1,double num2) throws RemoteException;
}