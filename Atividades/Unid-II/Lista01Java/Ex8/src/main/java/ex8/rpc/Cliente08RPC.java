/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex8.rpc;

import java.net.URL;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente08RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente08RPC() {
        try {
            //configura o cliente para que ele possa se conectar ao servidor
            XmlRpcClientConfigImpl configuracaoCliente = new XmlRpcClientConfigImpl();
            configuracaoCliente.setServerURL(new URL(URL_SERVIDOR));
            //seta a configuração no cliente
            cliente = new XmlRpcClient();
            cliente.setConfig(configuracaoCliente);
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }

    public String credito(double saldo) throws Exception{
        Object[] parametros = new Object[]{new Double(saldo)};
        String resultado = (String) cliente.execute("Banco.credito", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente08RPC x = new Cliente08RPC();

        try {

            double saldo;
            saldo = Double.parseDouble(
                    JOptionPane.showInputDialog(null,
                            "Digite o Saldo Medio:",
                            "Saldo",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );


            String valor = x.credito(saldo);
            JOptionPane.showMessageDialog(null, valor);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
