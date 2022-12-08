/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex7.rpc;

import java.net.URL;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente07RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente07RPC() {
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

    public boolean verificar(int idade, int tempoServiço) throws Exception {
        Object[] parametros = new Object[]{new Integer(idade), new Integer(tempoServiço)};
        Boolean resultado = (Boolean) cliente.execute("Aposentadoria.verificar", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente07RPC x = new Cliente07RPC();

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

            int tempoServico = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o tempo de serviço",
                    "Tempo de Serviço",
                    JOptionPane.QUESTION_MESSAGE
            ));

            boolean aposentar = x.verificar(idade, tempoServico);
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + (aposentar
                            ? "\nvocê pode se aponsentar!"
                            : "\nvocê ainda não pode se aposentar!")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
