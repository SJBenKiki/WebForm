package CAS.GUI.Login;
/*
 Tauseef Pirzada
 06122013
 UserBase class which will be handled from the main login frame
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import java.util.Scanner;

public class UserBase
{
  private File file;
  /*
   * Constructor
   * File will be instantiated with UserLoginData.waldo
   * This file will contain user login information (Encrypted)
   * This file will be read from and written to when appropriate
   * */
  public UserBase()
  {
    file = new File("UserLoginData.waldo");
  }
  /*
   * Constructor
   * File will be instantiated with File passed into the constructor
   * This file will contain user login information (Encrypted)
   * This file will be read and written to when appropriate
   * @argument File instance to be used in this class
   * */
  public UserBase(File file)
  {
    this.file = new File(file.toString());
    file = null;
  }
  /*
   * authenticate
   * Will read user login file line by line until a username and passcode match is found
   * @argument String username to be compared with decrypted username from file
   * @argument String passcode to be compared with decrypted passcode from file
   * @return boolean true if passcode and username match, false otherwise
   * */
  public boolean authenticate(String szUsername,String szPasscode)throws IOException
  {
    Scanner scan = new Scanner(file);
    String[] szDec = null;
    boolean bRet = false;
    while(scan.hasNextLine())
    {
      szDec = Xor.decrypt(scan.nextLine()).split(":");
      if(szDec[0].equals(szUsername)&&szDec[1].equals(szPasscode))
        bRet = true;
    }
    scan.close();
    return bRet;
  }
  /*
   * recover
   * Method will recover a password for the user provided the information matches accordingly
   * WIll be called from the RecoverFrame 
   * @argument String username to be matched with file
   * @argument String passcode to be matched with file
   * @argument String email provided during registration, to be matched with file
   * @argument String security question used during registration, to be matched with file
   * @argument String security answer used during registration, to be matched with file
   * @return String passcode if all information provided matches accordingly with information in file, null otherwise
   * */
  public String recover(String szUsername,String szEmail,String szQuestion,
                        String szAnswer)throws IOException
  {
    Scanner scan = new Scanner(file);
    String[] szDec = null;
    String szPasscode = null;
    while(scan.hasNextLine())
    {
      szDec = Xor.decrypt(scan.nextLine()).split(":");
      if(szDec[0].equalsIgnoreCase(szUsername)&&
         szDec[2].equalsIgnoreCase(szEmail)&&
         szDec[3].equalsIgnoreCase(szQuestion)&&
         szDec[4].equalsIgnoreCase(szAnswer)
        )
        szPasscode = szDec[1];
    }
    scan.close();
    return szPasscode;
  }
  /*
   * buildEncryptedString
   * private helper method, can only be used by this class
   * Will combine arguments using : as delimeter then encrypt the strings
   * @argument String username to be Encrypted and written to file
   * @argument String passcode to be Encrypted and written to file
   * @argument String email to be Encrypted and written to file
   * @argument String security question to be Encrypted and written to file
   * @argument String security answer to be Encrypted and written to file
   * @return String Encrypted form
   * */
  private String buildEncryptedString(String szUsername,String szPasscode,
                                      String szEmail,String szQuestion,
                                      String szAnswer)
  {
    String szUserData = szUsername+":"+szPasscode+":"+szEmail+":"+szQuestion+
      ":"+szAnswer;
    return Xor.encrypt(szUserData);
  }
  /*
   * doesUsernameExist
   * private Method only to be used by this class
   * Will prevent duplicate username accounts
   * @argument String username to be checked
   * @return boolean true if username exists in file, false otherwise
   * */
  private boolean doesUsernameExist(String szUsername)throws IOException
  {
    Scanner scan = new Scanner(file);
    String[] szDec = null;
    boolean bRet = false;
    while(scan.hasNextLine())
    {
      szDec = Xor.decrypt(scan.nextLine()).split(":");
      if(szDec[0].equalsIgnoreCase(szUsername))
        bRet = true;
    }
    scan.close();
    return bRet;
  }
  /*
   * register
   * Will be called from the Registration form
   * Will check if file exists, 
   * If file exists, will check if username exists
   * If username exists, will return false
   * If file does not exist, new file will be created
   * Registration information will be encrypted and written to file
   * @argument Username to be registered
   * @argument password to be registered
   * @argument email to be used for recovery
   * @argument security question to be used for recovery
   * @argument security answer to be used for recovery
   * @return boolean true if account is registered, false otherwise
   * */
  public boolean register(String szUsername,String szPasscode,
                          String szEmail,String szQuestion,
                          String szAnswer)throws IOException
  {
    if(file.exists())
    {
      if(doesUsernameExist(szUsername))
        return false;
    }
    else
      file.createNewFile();
    
    String szEncStr = buildEncryptedString(szUsername,szPasscode,
                                           szEmail,szQuestion,
                                           szAnswer);
    BufferedWriter br = new BufferedWriter(new FileWriter(file,true));
    br.write(szEncStr);
    br.newLine();
    br.close();
    return true;
  }
}