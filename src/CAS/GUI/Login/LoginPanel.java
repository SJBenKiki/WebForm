package CAS.GUI.Login;

/*
 Tauseef Pirzada
 06122013
 LoginPanel class
 Will be the main panel visible when the application launches
 */
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel
{
  
  private JLabel jLabelUser;
  private JLabel jLabelPass;
  
  private final int TEXT_LENGTH = 20;
  
  private JTextField jTextUsername;
  private JPasswordField jTextPasscode;
  
  private LoginButtonPanel lbPanel;
  /*
   * LoginPanel Constructor
   * Calls super with instance of GridBagLayout
   * Calls buildThatAwesomeLoginPanelYo method to instantiate the fields
   * */
  public LoginPanel()
  {
    super(new GridBagLayout());
    buildThatAwesomeLoginPanelYo();
    setVisible(true);
  }
  /*
   * buildThatAwesomeLoginPanelYo method
   * Instantiates user fields
   * Instantiates passcode fields
   * Instantiates LoginButtonPanel for buttons
   * Uses GridBagConstraints for alighnment
   * */
  private void buildThatAwesomeLoginPanelYo()
  {
    jTextUsername = new JTextField(TEXT_LENGTH);
    jTextPasscode = new JPasswordField(TEXT_LENGTH);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    jLabelUser = new JLabel("Username: ");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    add(jLabelUser, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(jTextUsername, gbc);
    
    jLabelPass = new JLabel("Passcode: ");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    add(jLabelPass, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    add(jTextPasscode, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    lbPanel = new LoginButtonPanel();
    add(lbPanel,gbc);
    setBorder(new LineBorder(Color.GRAY));
  }
  /*
   * getPasscodeField
   * Will be used by the frame when signing in
   * @return String form of the text contained in the passcode field
   * */
  public String getPasscodeField()
  {
    return String.
      valueOf(jTextPasscode.
                getPassword());
  }
  /*
   * getUsernameField
   * Will be used bz the frame when signing in
   * @return String form of the text contained in the username field
   * */
  public String getUsernameField()
  {
    return jTextUsername.
      getText();
  }
  /*
   * getLoginButton
   * Retrieves the loginButton instance from LoginPanel
   * Will be used by the frame when implementing ActionListener
   * @return instance of loginButton from LoginPanel as final
   * */
  public final JButton getLoginButton()
  {
    return lbPanel.getLoginButton();
  }
  /*
   * getRegisterButton
   * Retrieves the registerButton instance from LoginPanel
   * Will be used by the frame when implementing ActionListener
   * @return instance of registerButton from LoginPanel as final
   * */
  public final JButton getRegisterButton()
  {
    return lbPanel.getRegisterButton();
  }
  /*
   * getRecoveryButton
   * Retrieves the recoveryButton instance from LoginPanel
   * Will be used by the frame when implementing ActionListener
   * @return instance of recoveryButton from LoginPanel as final
   * */
  public final JButton getRecoveryButton()
  {
    return lbPanel.getRecoveryButton();
  }
}