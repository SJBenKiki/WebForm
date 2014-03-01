package CAS.GUI.Login;
/*
 Tauseef Pirzada
 06122013
 RegisterFrame will be instantiated when the user wants to create a new account
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

public class RegisterFrame extends JFrame
{
  private UserBase ub;
  private JFrame jLoginFrame;
  private RegisterPanel jRegisterPanel;
  
  
  private final int WIDTH = 400;
  private final int HEIGHT = 300;
  /*
   * Constructor
   * Instantiates RegisterPanel and fields through builtThatAwesomeRegisterFrame
   * @argument JFrame instance to be used for restoring the LoginFrame
   * @argument UserBase instance to be used for checking account existance and creating new accounts
   * */
  public RegisterFrame(JFrame jLoginFrame,UserBase ub)
  {
    super("Register");
    this.jLoginFrame = jLoginFrame;
    this.ub = ub;
    builtThatAwesomeRegisterFrame();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(WIDTH,HEIGHT);
    setVisible(true);
  }
  /*
   * builtThatAwesomeRegisterFrame
   * Adds ActionListener to Register button
   * Adds ActionListener to Combo Question Box
   * Adds ActionListenered to Cancel Button
   * Adds Register panel to frame
   * */
  private void builtThatAwesomeRegisterFrame()
  {
    OnAction onAction = new OnAction();
    jRegisterPanel = new RegisterPanel();
    
    jRegisterPanel.getQuestionBox().addActionListener(onAction);
    jRegisterPanel.getCancelButton().addActionListener(onAction);
    jRegisterPanel.getRegisterButton().addActionListener(onAction);
    
    add(jRegisterPanel);
  }
  /*
   * ActionListener implementation
   * Will check three buttons
   * Cancel, and Register
   * */
  private class OnAction implements ActionListener
  {
    /*
     * actionPerformed implementation
     * Will handle messages passed by Java's Message Loop
     * Method will be called when an event occurs
     * ae.getSource() == getCancelButton
     *   LoginFrame visibity is set to true
     *   RegisterFrame is destroyed
     * ae.getSource() == getRegisterButton
     *   checks if fields are not empty
     *     If Account does not exist
     *       Account is created
     *       LoginFrame visibility is to true
     *       RegisterFrame is destroyed
     *     else
     *       Account creation failed
     *   else
     *     Asks to completely fill out Registration fields
     * @argument ActionEvent holds information about the event
     * */
    public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource() == jRegisterPanel.getCancelButton())
      {
        setVisible(false);
        jLoginFrame.setVisible(true);
        dispose();
      }
      else if (ae.getSource() == jRegisterPanel.getRegisterButton())
      {
        String szEmail = jRegisterPanel.getEmailField();
        String szUsername = jRegisterPanel.getUsernameField();
        String szPasscode = jRegisterPanel.getPasscodeField();
        String szAnswer = jRegisterPanel.getSecurityAnswerField();
        String szQuestion = jRegisterPanel.getSecurityQuestionField();
        if(! (szEmail.isEmpty() || szUsername.isEmpty() || szPasscode.isEmpty() ||
              szAnswer.isEmpty() || szQuestion.isEmpty()) )
        {
          try
          {
            if(!ub.register(szUsername,szPasscode,
                            szEmail,szQuestion,
                            szAnswer))
              JOptionPane.showMessageDialog(null,"Account: "+szUsername+" already exists.","Duplicate Username",JOptionPane.INFORMATION_MESSAGE);
            else
            {
              JOptionPane.showMessageDialog(null,"Account: "+
                                            szUsername+" created successfully!");
              setVisible(false);
              jLoginFrame.setVisible(true);
              dispose();
            }
          }
          catch(IOException ioe)
          {
            JOptionPane.showMessageDialog(null,ioe.getMessage());
          }
        }
        else
          JOptionPane.showMessageDialog(null,"Registration fields can not be empty");
      }
    }
  }
}