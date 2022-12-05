/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex9.rmi;

import home09.BaralhoInterfaceRMI;
import home09.NaipeCarta;
import home09.ValorCarta;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mathe
 */
public class Servidor09RMI implements BaralhoInterfaceRMI {
    private int valor;
    private int naipe;

    public String nomeCarta(int valor, int naipe) {
        setNaipe(naipe);
        setValor(valor);

        return obterValorCarta() + " de " + obterNaipeCarta();
    }

    public String obterNaipeCarta() {
        String strNaipeCarta = "";
        switch (getNaipe()) {
            case 1:
                strNaipeCarta += NaipeCarta.OUROS;
                break;
            case 2:
                strNaipeCarta += NaipeCarta.PAUS;
                break;
            case 3:
                strNaipeCarta += NaipeCarta.COPAS;
                break;
            case 4:
                strNaipeCarta += NaipeCarta.ESPADAS;
                break;
            default:
                break;

        }
        return strNaipeCarta;
    }

    public String obterValorCarta() {
        String strValorCarta = "";
        switch (getValor()) {
            case 1:
                strValorCarta += ValorCarta.AS;
                break;
            case 2:
                strValorCarta += ValorCarta.DOIS;
                break;
            case 3:
                strValorCarta += ValorCarta.TRES;
                break;
            case 4:
                strValorCarta += ValorCarta.QUATRO;
                break;
            case 5:
                strValorCarta += ValorCarta.CINCO;
                break;
            case 6:
                strValorCarta += ValorCarta.SEIS;
                break;
            case 7:
                strValorCarta += ValorCarta.SETE;
                break;
            case 8:
                strValorCarta += ValorCarta.OITO;
                break;
            case 9:
                strValorCarta += ValorCarta.NOVE;
                break;
            case 10:
                strValorCarta += ValorCarta.DEZ;
                break;
            case 11:
                strValorCarta += ValorCarta.VALETE;
                break;
            case 12:
                strValorCarta += ValorCarta.DAMA;
                break;
            case 13:
                strValorCarta += ValorCarta.REI;
                break;
            default:
                break;
        }

        return strValorCarta;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getNaipe() {
        return naipe;
    }

    public void setNaipe(int naipe) {
        this.naipe = naipe;
    }
    
    public static void main(String[] args) {
        try {
            Servidor09RMI obj = new Servidor09RMI();
            BaralhoInterfaceRMI stub = (BaralhoInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("InterfaceRMI", stub);

            System.out.println("ServidorRMI vinculado no registro");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
