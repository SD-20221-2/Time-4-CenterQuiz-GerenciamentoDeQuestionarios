/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex6.rpc;

import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author mathe
 */
public class Cliente06RPC {

    //DEFINE A URL DO SERVIDOR
    private static final String URL_SERVIDOR = "http://localhost:8185";
    private XmlRpcClient cliente;

    public Cliente06RPC() {
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

    public double liquido(
            String nivel,
            double salarioBruto,
            int numeroDependentes
    ) throws Exception {
        Object[] parametros = new Object[]{
            new String(nivel),
            new Double(salarioBruto),
            new Integer(numeroDependentes)
        };
        Double resultado = (Double) cliente.execute("Salario.liquido", parametros);
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Cliente06RPC x = new Cliente06RPC();

        try {

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );
            String[] niveis = {"A", "B", "C", "D"};
            String nivel = (String) JOptionPane.showInputDialog(
                    null,
                    "Digite o nível",
                    "Nível",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    niveis,
                    niveis[0]
            );
            double salarioBruto = Double.parseDouble(JOptionPane.showInputDialog(
                    null,
                    "Digite o seu salário bruto",
                    "Salário bruto",
                    JOptionPane.QUESTION_MESSAGE
            ));

            int numeroDependentes = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o número de dependentes",
                    "Número de dependentes",
                    JOptionPane.QUESTION_MESSAGE
            ));

            double salarioLiquido = x.liquido(nivel, salarioBruto, numeroDependentes);
            DecimalFormat df = new DecimalFormat("#.00");
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + "\nVocê pertence ao nível \"" + nivel
                    + "\" e seu salário líquido é = R$ " + df.format(salarioLiquido)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
