
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author mathe
 */
public class Servidor04SocketConcorrente {

    /**
     * Executa o servidor. Quando um cliente se conecta, o servidor gera um novo
     * thread para fazer a manutenção e imediatamente volta a ouvir. Os limites
     * de aplicação o número de threads por meio de um pool de threads (caso
     * contrário, milhões de clientes poderiam faz com que o servidor fique sem
     * recursos ao alocar muitos threads).
     */
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(7777)) {
            System.out.println("O servidor está em execução...");

            /**
             * Cria um pool de threads que reutiliza um número fixo de threads
             * operando a partir de uma fila compartilhada ilimitada.
             */
            ExecutorService pool = Executors.newFixedThreadPool(1000);

            while (true) {
                pool.execute(new Servidor01(listener.accept()));
            }
        }
    }

    private static class Servidor01 implements Runnable {

        private Socket socket;

        Servidor01(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Conexao de " + socket + "!");

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream
                        = new DataInputStream(inputStream);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(
                        outputStream
                );

                //Entrada de dados do cliente
                double altura = dataInputStream.readDouble();
                String sexo = dataInputStream.readUTF();

                // Chamada ao método
                PesoIdeal pes = new PesoIdeal();
                double pesoIdeal = pes.calcular(altura, sexo);
                // Saída de dados
                dataOutputStream.writeDouble(pesoIdeal);
                dataOutputStream.flush();
                dataOutputStream.close();

                System.out.println("Fechando Sockets.");

                socket.close();
            } catch (Exception e) {
                System.out.println("err: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Finalizado: " + socket);
            }
        }
    }
}
