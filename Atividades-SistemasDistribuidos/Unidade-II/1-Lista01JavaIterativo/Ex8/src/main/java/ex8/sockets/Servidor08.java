package ex8.sockets;

import home08.Banco;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor08 {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("Servidor esperando por conexoes...");
        Socket socket = ss.accept();
        System.out.println("Conexao de " + socket + "!");

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(
                outputStream
        );

        double saldo = dataInputStream.readDouble();

        Banco banco = new Banco();
        dataOutputStream.writeUTF(banco.credito(saldo));
        dataOutputStream.flush();

        dataOutputStream.close();

        System.out.println("Fechando Sockets.");
        ss.close();
        socket.close();
    }
}
