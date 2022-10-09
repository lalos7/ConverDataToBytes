

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//AQUI VA LA RUTA DEL ARCHIVO A LEER
		Path path = Paths.get("/Users/mac_user/Pictures/Imagenes.zip");
		byte[] content = null;
		
		 try {
			 content = java.nio.file.Files.readAllBytes(path);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		 String convertString = bytesToHex(content);
		 System.out.println("Bytes a Hexadecimal para poder guardar o visualizar la cadena ="+convertString);

		 System.out.println("Pasar de Hex a Bytes para obtener el archivo original");
		 
		 byte[] contentOriginal =  hexStringToByteArray(convertString);
		 
		 //AQUI SE PONE LA RUTA DE DONDE SE QUIERA RECUPERAR EL ARCHIVO EN FORMATO ORIGINAL
		 try (FileOutputStream fos = new FileOutputStream("ArchivoRecuperado.zip")) {
			   fos.write(contentOriginal);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
	}
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

}
