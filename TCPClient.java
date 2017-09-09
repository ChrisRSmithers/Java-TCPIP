import java.io.*; 
import java.net.*; 


public class TCPClient { 

    public static void main(String argv[]) throws Exception 
    { 
        String hostname = InetAddress.getLocalHost().getHostName();
        String sentence; 
        String modifiedSentence; 

        BufferedReader inFromUser = 
            new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket(hostname, 6791); 

        DataOutputStream outToServer = 
            new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = 
            new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream())); 

        System.out.println("Enter request :");
        
        while(true)
        {   
            sentence = inFromUser.readLine();
            String[] things = new String[1];
            things=sentence.split(":");
             if(!sentence.equals("end"))
             {
                  System.out.println("Enter request:"+sentence);
             }
            else if (things[0].equals("CHECK"))
            {
              String getNum;
              getNum= inFromServer.readLine(); 
              System.out.println("Name'number is: " + getNum); 
            }

            outToServer.writeBytes(sentence + '\n');
            
            if(sentence.equals("end"))
            {
              System.out.println("Disconnect with server ");
              break;
            }

            modifiedSentence = inFromServer.readLine(); 

            System.out.println("FROM SERVER: " + modifiedSentence); 

            

        }
        clientSocket.close(); 

    } 
}
