import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by sho on 2017/09/14.
 */
public class main {
    static Socket socket = null;
    public static void main(String[] args) throws IOException {
        ServerSocket ServerSocket = new ServerSocket(800);
        socket = ServerSocket.accept();
        Client client = new Client(socket);
        client.start();
        System.out.print("Connected");

    }
}

class Client extends Thread
{
    private Socket sc;
    private BufferedReader br;
    private PrintWriter pw;

    public Client(Socket s)
    {
        sc = s;
    }
    public void run()
    {
        try{
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        while(true){
            try{
                String str = br.readLine();
                System.out.println(str);
                pw.println(str);
                pw.flush();
            }
            catch(Exception e){
                try{
                    br.close();
                    pw.close();
                    sc.close();
                    System.out.println("Good Bye !!");
                    break;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }
}
