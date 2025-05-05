package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {

    protected ServerImpl() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public double add(double num1, double num2) throws RemoteException {
        return num1 + num2;
    }

    @Override
    public double sub(double num1, double num2) throws RemoteException {
        return num1 - num2;
    }

    @Override
    public double mul(double num1, double num2) throws RemoteException {
        return num1 * num2;
    }

    @Override
    public double div(double num1, double num2) throws RemoteException {
        return num1 / num2;
    }

}
