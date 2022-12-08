/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex4.rpc;

import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente04RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente04RPC() {
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

    public double calcular(double altura, String sexo) throws Exception {
        Object[] parametros = new Object[]{new Double(altura), new String(sexo)};
        Double resultado = (Double) cliente.execute("PesoIdeal.calcular", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente04RPC x = new Cliente04RPC();

        try {

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );

            double altura = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite sua altura",
                            "Altura",
                            JOptionPane.QUESTION_MESSAGE
                    )
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

            double pesoIdeal = x.calcular(altura, sexo);
            DecimalFormat df = new DecimalFormat("#.000");
            JOptionPane.showMessageDialog(
                    null,
                    "Olá " + nome
                    + "\nSeu peso ideal é " + df.format(pesoIdeal)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
