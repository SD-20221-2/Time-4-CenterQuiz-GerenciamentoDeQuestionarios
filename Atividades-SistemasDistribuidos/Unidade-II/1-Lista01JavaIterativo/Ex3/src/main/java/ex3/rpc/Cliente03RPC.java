/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex3.rpc;

import java.net.URL;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente03RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente03RPC() {
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
    
    public boolean verificarAprovado(double n1, double n2, double n3) throws Exception{
        Object[] parametros = new Object[]{new Double(n1), new Double(n2) , new Double(n3)};
        Boolean resultado = (Boolean) cliente.execute("Nota.verificarAprovado", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente03RPC x = new Cliente03RPC();

        try {

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );
            double n1 = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite a n1",
                            "Nota 1",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );
            double n2 = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite a n2",
                            "Nota 2",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );
            double n3 = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite a n3",
                            "Nota 3",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );

            boolean aprovado = x.verificarAprovado(n1, n2, n3);
            
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + (aprovado
                    ? "\nvocê está aprovado!"
                    : "\nvocê não está aprovado!")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
