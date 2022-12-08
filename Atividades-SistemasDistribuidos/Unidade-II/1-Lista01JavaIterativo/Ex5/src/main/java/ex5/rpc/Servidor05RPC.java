/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex5.rpc;

import home05.CategoriaNadador;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author mathe
 */
public class Servidor05RPC {
    public static void main(String[] args) {
        try {
			// Cria um servidor web na porta 8185
            WebServer ws = new WebServer(8185); 
            XmlRpcServer servidor = ws.getXmlRpcServer(); 
			// Adiciona um novo "handler" ao PHM
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("CategoriaNadador", CategoriaNadador.class); 
			// Define um handler no servidor
            servidor.setHandlerMapping(phm);
			// inicia o servidor		
            ws.start(); 
			System.out.println("Servidor iniciado com sucesso!");
		
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }
}
