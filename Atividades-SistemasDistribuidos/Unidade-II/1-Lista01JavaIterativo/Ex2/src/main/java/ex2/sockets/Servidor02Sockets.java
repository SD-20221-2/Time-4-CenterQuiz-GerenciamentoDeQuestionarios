/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.sockets;

import home02.Maioridade;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class Servidor02Sockets {

    
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket socket;
        try (ServerSocket ss = new ServerSocket(7777)) {
            System.out.println("Servidor esperando por conexoes...");

            socket = ss.accept();
            System.out.println("Conexao de " + socket + "!");

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream
            );
            
            //Entrada de dados do cliente
            String sexo = dataInputStream.readUTF();
            int idade = dataInputStream.readInt();
            
            // Chamada ao método
            Maioridade mal = new Maioridade();
            boolean maioridade = mal.verificar(sexo, idade);
            // Saída de dados
            dataOutputStream.writeBoolean(maioridade);
            dataOutputStream.flush();
            dataOutputStream.close();

            System.out.println("Fechando Sockets.");
            
            ss.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("err: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
