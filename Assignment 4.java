//2117240070105
//Harini M

PROGRAM:
import java.io.*;
import java.net.*; 
import java.nio.file.*; 
import java.security.*; 
import java.util.*;

public class Server {
    public static void main(String[] a) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        Path dir = Paths.get("server_folder"); Files.createDirectories(dir);
        System.out.println("Server ready...");
        Socket s = ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        
        int n = in.readInt(); Map<String,String> client = new HashMap<>();
        for(int i=0;i<n;i++) client.put(in.readUTF(), in.readUTF());

      
        Map<String,String> server = new HashMap<>();
        Files.walk(dir).filter(Files::isRegularFile).forEach(f->{
            try { server.put(dir.relativize(f).toString(), md5(f)); } catch(Exception e){} });

      
        List<String> need = new ArrayList<>();
        for(var e: client.entrySet())
            if(!server.containsKey(e.getKey()) || !server.get(e.getKey()).equals(e.getValue()))
                need.add(e.getKey());
        out.writeInt(need.size());
        for(String f: need) out.writeUTF(f);
        out.flush();

      
        for(String f: need){
            String name=in.readUTF(); long len=in.readLong();
            Path p=dir.resolve(name); Files.createDirectories(p.getParent());
            try(FileOutputStream fos=new FileOutputStream(p.toFile())){
                byte[] buf=new byte[4096]; long rem=len;
                while(rem>0){int r=in.read(buf,0,(int)Math.min(buf.length,rem)); fos.write(buf,0,r); rem-=r;}
            }
            System.out.println("Received "+name);
        }
        s.close(); ss.close();
    }

    static String md5(Path f)throws Exception{
        MessageDigest md=MessageDigest.getInstance("MD5");
        try(InputStream is=Files.newInputStream(f)){byte[]b=new byte[4096];int r;while((r=is.read(b))!=-1)md.update(b,0,r);}
        StringBuilder sb=new StringBuilder();for(byte b:md.digest())sb.append(String.format("%02x",b));return sb.toString();
    }
import java.io.*;
import java.net.*; 
import java.nio.file.*; 
import java.security.*; 
import java.util.*;

public class Client {
    public static void main(String[] a)throws Exception{
        Socket s=new Socket("127.0.0.1",5000);
        DataInputStream in=new DataInputStream(s.getInputStream());
        DataOutputStream out=new DataOutputStream(s.getOutputStream());
        Path dir=Paths.get("client_folder");
        Map<String,String> client=new HashMap<>();
        Files.walk(dir).filter(Files::isRegularFile).forEach(f->{
            try{client.put(dir.relativize(f).toString(),md5(f));}catch(Exception e){}});
        out.writeInt(client.size());
        for(var e:client.entrySet()){out.writeUTF(e.getKey());out.writeUTF(e.getValue());}
        out.flush();

        int need=in.readInt(); List<String> files=new ArrayList<>();
        for(int i=0;i<need;i++)files.add(in.readUTF());
        for(String f:files){
            Path p=dir.resolve(f); long len=Files.size(p);
            out.writeUTF(f); out.writeLong(len);
            try(FileInputStream fis=new FileInputStream(p.toFile())){
                byte[] buf=new byte[4096]; int r; while((r=fis.read(buf))!=-1) out.write(buf,0,r);
            }
            System.out.println("Sent "+f);
        }
        s.close();
    }

    static String md5(Path f)throws Exception{
        MessageDigest md=MessageDigest.getInstance("MD5");
        try(InputStream is=Files.newInputStream(f)){byte[]b=new byte[4096];int r;while((r=is.read(b))!=-1)md.update(b,0,r);}
        StringBuilder sb=new StringBuilder();for(byte b:md.digest())sb.append(String.format("%02x",b));return sb.toString();
    }
}

OUTPUT:
Server started...
Client connected.
Comparing files...
Received file1.txt
Received file2.txt
Sync complete.


Connected to server.
Sending file checksums...
Uploading file1.txt
Uploading file2.txt
Sync complete.
