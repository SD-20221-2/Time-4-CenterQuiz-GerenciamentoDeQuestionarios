/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex9.rpc;

import java.net.URL;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente02RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente02RPC() {
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

    public String nomeCarta(int valor, int naipe) throws Exception {
        Object[] parametros = new Object[]{new Integer(valor), new Integer(naipe)};
        String resultado = (String) cliente.execute("Baralho.nomeCarta", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente02RPC x = new Cliente02RPC();

        try {

            int valor;
            valor = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o valor da carta",
                    "Valor",
                    JOptionPane.QUESTION_MESSAGE
            ));

            int naipe;
            naipe = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o naipe da carta",
                    "Naipe",
                    JOptionPane.QUESTION_MESSAGE
            ));

            String nomeCarta = x.nomeCarta(valor, naipe);
            JOptionPane.showMessageDialog(null,
                    "Nome da carta: " + nomeCarta
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
