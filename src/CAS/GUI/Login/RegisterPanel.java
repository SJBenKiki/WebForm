package CAS.GUI.Login;

/*
 Tauseef Pirzada
 06122013
 RegisterPanel
 */
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class RegisterPanel extends JPanel
{
  private JButton jbCancel;
  private JButton jbRegister;
  
  private JLabel jLabelUser;
  private JLabel jLabelPass;
  private JLabel jLabelEmail;
  private JLabel jLabelSecurityAnswer;
  private JLabel jLabelSecurityQuestion;
  
  private JTextField jTextEmail;
  private JTextField jTextUsername;
  private JTextField jTextSecurityAnswer;
  
  private JPasswordField jTextPasscode;
  
  private JComboBox<String> jComboQuestion;
  
  private final int TEXT_LENGTH = 20;
  /*
   * Constructor
   * calls super with instance of GridBagLayout
   * calls buildThatReallyCoolRegisterPanel method for instantiating fields
   * sets visibility to true
   * Will be called when the user hits Register from the main login frame
   * */
  public RegisterPanel()
  {
    super(new GridBagLayout());
    buildThatReallyCoolRegisterPanel();
    setVisible(true);
  }
  /*
   * buildThatReallyCoolRegisterPanel 
   * private will be called from the constructor
   * method will instantiate the fields
   * Uses GridBagConstraints for alighment
   * Contains username field
   * Contains passcode field
   * Contains email field
   * Contains security question field (JComboBox of String array)
   * Contains security answer field
   * */
  private void buildThatReallyCoolRegisterPanel()
  {
    String[] securityQuestions =
    {
      "In what school did you attend sixth grade?",
      "In what city or town was your first job?",
      "How many colleges did you apply to?",
      "What was your childhood nickname?",
      "What was your first payed job?",
      "Who was your first roommate?",
      "Is this a joke?"
    };
    jComboQuestion = new JComboBox<String>(securityQuestions);
    jbCancel = new JButton("Cancel");
    jbRegister = new JButton("Register");
    
    jTextEmail = new JTextField(TEXT_LENGTH);
    jTextUsername = new JTextField(TEXT_LENGTH);
    jTextPasscode = new JPasswordField(TEXT_LENGTH);
    jTextSecurityAnswer = new JTextField(TEXT_LENGTH);
    
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
    
    jLabelEmail = new JLabel("Email: ");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    add(jLabelEmail, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    add(jTextEmail, gbc);
    
    jLabelSecurityQuestion = new JLabel("Question: ");
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(jLabelSecurityQuestion, gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    add(jComboQuestion, gbc);
    
    jLabelSecurityAnswer = new JLabel("Answer: ");
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    add(jLabelSecurityAnswer, gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(jTextSecurityAnswer, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    add(jbCancel, gbc);
    gbc.gridx = 2;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    add(jbRegister, gbc);
    setBorder(new LineBorder(Color.GRAY));
  }
  /*
   * getEmailField
   * Will be called from RegisterFrame
   * @return String form of jTextEmail
   * */
  public String getEmailField()
  {
    return jTextEmail.
      getText();
  }
  /*
   * getUsernameField
   * Will be called from RegisterFrame
   * @return String form of jTextUsername
   * */
  public String getUsernameField()
  {
    return jTextUsername.
      getText();
  }
  /*
   * getPasscodeField
   * Will be called from RegisterFrame
   * @return String representation of email field from jTextPasscode
   * */
  public String getPasscodeField()
  {
    return String.
      valueOf(jTextPasscode.
                getPassword());
  }
  /*
   * getCancelButton
   * Will be called from RegisterFrame to be added to an ActionListener class
   * @return JButton jbCancel as final
   * */
  public final JButton getCancelButton()
  {
    return jbCancel;
  }
  /*
   * getSecurityAnswerField
   * Will be called from RegisterFrame
   * @return String form of text contained in jTextSecurityAnswer
   * */
  public String getSecurityAnswerField()
  {
    return jTextSecurityAnswer.
      getText();
  }
  /*
   * getQuestionBox
   * Contains an array of questions to ask the user for security recovery purpose
   * Will be called from RegisterFrame to be added to an ActionListener class
   * @return JComboBox as final
   * */
  public final JComboBox getQuestionBox()
  {
    return jComboQuestion;
  }
  /*
   * getSecurityQuestionField
   * Will be called from RegisterFrame
   * @return String form of text contained in field chosen by user in JComboBox
   * */
  public String getSecurityQuestionField()
  {
    return (String)jComboQuestion.
      getSelectedItem();
  }
  /*
   * getRegisterButton
   * Will be called from RegisterFrame to be added to an ActionListener class
   * @return JButton jbRegister as final
   * */
  public final JButton getRegisterButton()
  {
    return jbRegister;
  }
  
}