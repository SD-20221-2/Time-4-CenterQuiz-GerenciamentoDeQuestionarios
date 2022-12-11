/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author mathe
 */
public class CapitalizeClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 59898)) {
            System.out.println("Insira linhas de texto");
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter outSocket = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );
            while (scanner.hasNextLine()) {
                outSocket.println(scanner.nextLine());

                System.out.println(in.nextLine());
            }
        }
    }
}
