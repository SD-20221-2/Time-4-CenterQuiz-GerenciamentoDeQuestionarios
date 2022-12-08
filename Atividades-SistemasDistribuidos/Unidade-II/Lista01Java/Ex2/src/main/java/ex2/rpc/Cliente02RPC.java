/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.rpc;

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

    public boolean verificar(String sexo, int idade) throws Exception {
        Object[] parametros = new Object[]{new String(sexo), new Integer(idade)};
        Boolean resultado = (Boolean) cliente.execute("Maioridade.verificar", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente02RPC x = new Cliente02RPC();

        try {

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );
            String[] opcoes = {"M", "F"};
            String sexo = (String) JOptionPane.showInputDialog(
                    null,
                    "Digite o sexo",
                    "Sexo",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[1]
            );

            int idade;
            idade = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite a idade",
                    "Idade",
                    JOptionPane.QUESTION_MESSAGE
            ));

            boolean maioridade = x.verificar(sexo, idade);
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + (maioridade
                            ? "\nvocê atingiu a maioridade!"
                            : "\nvocê não atingiu a maioridade!")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
