package CAS.GUI.Login;

/*
 Tauseef Pirzada
 06122013
 Xor class containing three static methods
 Handling Encryption, hex padding, and decryption
 */
import java.util.ArrayList;
public class Xor
{
  public static final byte[] KEY = {0xD,0xE,0xA,0xD,0xB,0xE,0xE,0xF};
  /*
   * encrypt
   * Will encrypt a string using Xor Encryption
   * Key will be DEADBEEF
   * Will format the encrypted string to hex with two byte padding
   * @argument String to be encrypted
   * @return Encrypted String prepared to be written to file
   * */
  public static String  encrypt(String szUser)
  {
    byte[] bUser = szUser.getBytes();
    String szEncrypt = "";
    for(int i = 0; i < bUser.length; ++i)
    {
      bUser[i] ^= KEY[i%KEY.length];
      szEncrypt+= String.format("%02X",bUser[i]);
    }
    return szEncrypt;
  }
  /*
   * undoHex
   * private, will only be used by this class
   * Will remove the hex two byte padding from the encrypted string
   * @argument String that is encrypted in hex format
   * @return String that is encrypted without hex format
   * */
  private static String undoHex(String str)
  {
    
    StringBuilder sb = new StringBuilder();
    for( int i=0; i<str.length()-1; i+=2 )
      sb.append((char)Byte.parseByte(str.substring(i, (i + 2)), 16));
    return sb.toString();
  }
  /*
   * decrypt
   * Will call undoHex then decrypt the string using Xor Decryption
   * Key will be DEADBEEF
   * @argument Encrypted String (Read from file)
   * @return Decrypted String ready to be parsed
   * */
  public static String decrypt(String szUser)
  {
    byte[] bUser = undoHex(szUser).getBytes();
    for(int i = 0; i < bUser.length;++i)
      bUser[i] ^= KEY[i%KEY.length];
    return new String(bUser);
  }
}