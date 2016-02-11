import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 46066294p on 03/02/16.
 */
public class Cliente {
    public static void main(String[] args) {
        try {

        Scanner scanner = new Scanner(System.in);

            int cont = 0;

            //Creamos un bucle que pregunte Xveces por una operacion que escribamos por pantalla
            while(cont < 5){
                //os.flush();
                Socket clienteSocket = new Socket();
                System.out.println("...creado el socket cliente");
                System.out.println("...estableciendo conexion");

                InetSocketAddress addr = new InetSocketAddress("172.31.83.41", 5555);
                clienteSocket.connect(addr);

                //Declaramos los objetos de escritura y lectura de respuesta
                InputStream is = clienteSocket.getInputStream();
                OutputStream os = clienteSocket.getOutputStream();

                //Introducimos la operacion para la culculadora del servidor
                System.out.println("\nescriba mensaje :");
                String operacion = scanner.nextLine();
                os.write(operacion.getBytes());
                System.out.println("...mensaje enviado\n");
                System.out.println("Resultado de la operacion:");

                byte[] k = new byte[150];
                is.read(k);

                //Imprimimos la respuesta por pantalla
                System.out.println(new String(k));
                byte[] msgEntrada = new byte[50];
                is.read(msgEntrada);
                System.out.println(new String(msgEntrada));

                //Cerramos sockets y objetos abiertos
                is.close();
                os.close();
                clienteSocket.close();


                cont++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
