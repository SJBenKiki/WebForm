package CAS.GUI.Login;
/*
 *Tauseef Pirzada
 *06122013
 *Panel provided specifically for three buttons
 */
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
public class LoginButtonPanel extends JPanel
{
  private JButton jbLogin;
  private JButton jbRecover;
  private JButton jbRegister;
  /*
   * Constructor calls super with instance of GridBaghLayout
   * Calls the private buildLoginButtonPanel method
   * */
  public LoginButtonPanel()
  {
    super(new GridBagLayout());
    buildLoginButtonPanel();
  }
  /*
   * Private method buildLoginButtonPanel
   * Intantiates Recovery button
   * Instantiates Login button
   * Instantiates Registry button
   * Aligns the buttons using GridBagConstraints
   * */
  private void buildLoginButtonPanel()
  {
    jbLogin = new JButton("Login");
    jbRecover = new JButton("Recovery");
    jbRegister = new JButton("Register");
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    add(jbLogin,gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    add(jbRegister,gbc);
    
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    add(jbRecover,gbc);
  }
  /*
   * getLoginButton
   * @return instance of jbLogin as final
   * */
  public final JButton getLoginButton()
  {
    return jbLogin;
  }
  /*
   * getRegisterButton
   * @return instance of jbRegister as final
   * */
  public final JButton getRegisterButton()
  {
    return jbRegister;
  }
  /*
   * getRecoveryButton
   * @return instance of jbRecover as final
   * */
  public final JButton getRecoveryButton()
  {
    return jbRecover;
  }
}