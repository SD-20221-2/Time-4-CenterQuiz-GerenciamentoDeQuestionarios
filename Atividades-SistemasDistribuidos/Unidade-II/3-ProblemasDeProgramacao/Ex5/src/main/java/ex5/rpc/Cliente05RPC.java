/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex5.rpc;

import java.net.URL;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente05RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente05RPC() {
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

    public String obterCategoria(int idade) throws Exception {
        Object[] parametros = new Object[]{new Integer(idade)};
        String resultado = (String) cliente.execute("CategoriaNadador.obterCategoria", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente05RPC x = new Cliente05RPC();

        try {

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );

            int idade;
            idade = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite a idade",
                    "Idade",
                    JOptionPane.QUESTION_MESSAGE
            ));

            String categoriaNadador = x.obterCategoria(idade);
            JOptionPane.showMessageDialog(
                    null,
                    "Olá " + nome
                    + "\nVocê se enquadra na categoria \"" + categoriaNadador
                    + "\" de nadadores"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
