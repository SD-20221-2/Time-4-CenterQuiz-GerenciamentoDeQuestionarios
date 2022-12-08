/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1.rpc;

import java.net.URL;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente01RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente01RPC() {
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

    public double reajustar(String cargo, double salario) throws Exception {
        Object[] parametros = new Object[]{new String(cargo), new Double(salario)};
        Double resultado = (Double) cliente.execute("Salario.reajustar", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente01RPC x = new Cliente01RPC();

        try {

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );
            String cargo = JOptionPane.showInputDialog(
                    null,
                    "Digite o cargo",
                    "Cargo",
                    JOptionPane.QUESTION_MESSAGE
            );
            double salario = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite o salario",
                            "Salário",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );

            double salarioReajustado = x.reajustar(cargo, salario);
            
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + "\nSeu salário reajustado é R$ " + Double.toString(salarioReajustado));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
