# Passport-Automation-System
----------------------------------ADMIN MODULE---------------------------------------------------------------------------

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;

public class Pas {

public class Admin extends JFrame implements ActionListener
{
    
    
    JFrame frame;
    JPanel AdminPanel,HeadingPanel;
    JLabel AdminLabel;
    JButton UpdateStatus,sendForBgVButton,submitButton,Home,Back;
    JTextField usernameTextField;
    Admin()
    {
       frame =new JFrame("Admin");
       frame.setBounds(0, 0, 1400,1400);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       AdminPanel=new JPanel();
       AdminPanel.setVisible(true);
       AdminPanel.setBounds(200,150,900,500);
       AdminPanel.setBackground(Color.BLACK);
       AdminPanel.setLayout(null);

       HeadingPanel=new JPanel();
       HeadingPanel.setBounds(0,0,1400,100);
       HeadingPanel.setBackground(Color.DARK_GRAY);
       HeadingPanel.setLayout(null);
       HeadingPanel.setVisible(true);

       AdminLabel=new JLabel("ADMIN AUTHENTICATION FOR PASSPORT");
       AdminLabel.setBounds(500,30,300,40);
       AdminLabel.setForeground(Color.WHITE);
       AdminLabel.setVisible(true);
       AdminLabel.setLayout(null);

       usernameTextField=new JTextField(30);
       usernameTextField.setBounds(50,430,250,50);

       Home=new JButton("HOME");
       Home.setBounds(50,650,100,40);
       Home.setBackground(Color.DARK_GRAY);
       Home.setForeground(Color.WHITE);
       Home.setFocusable(false);
       Home.addActionListener(this);

       Back=new JButton("BACK");
       Back.setBounds(1200,650,100,40);
       Back.setBackground(Color.DARK_GRAY);
       Back.setForeground(Color.WHITE);
       Back.setFocusable(false);
       Back.addActionListener(this);

       UpdateStatus=new JButton("UpdateStatus");
       UpdateStatus.setBounds(400,430,175,50);
       UpdateStatus.setBackground(Color.GRAY);
       UpdateStatus.setForeground(Color.WHITE);
       UpdateStatus.setFocusable(false);
       UpdateStatus.addActionListener(this);
       sendForBgVButton=new JButton("SendForBGV");
       sendForBgVButton.setBounds(700,430,175,50);
       sendForBgVButton.setBackground(Color.GRAY);
       sendForBgVButton.setForeground(Color.WHITE);
       sendForBgVButton.setFocusable(false);
       sendForBgVButton.addActionListener(this);
       
       display();

       HeadingPanel.add(AdminLabel);
       AdminPanel.add(usernameTextField);
       AdminPanel.add(UpdateStatus);
       AdminPanel.add(sendForBgVButton);
       
       frame.add(Home);
       frame.add(Back);
       frame.add(AdminPanel);
       frame.add(HeadingPanel);
       frame.setLayout(null);
       frame.setVisible(true);
    }
    void display()
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "vishal");
            System.out.println("Connected to database");
            Statement st=connect.createStatement();
            String s="select * from user_pas";
            ResultSet rs=st.executeQuery(s);
            JLabel USER =new JLabel("USERNAME");
            JLabel AADHAR =new JLabel("AADHAR NUMBER");
            JLabel VOTERID =new JLabel("VOTER ID");
            JLabel STATUS =new JLabel("STATUS");
            JLabel POLICEV =new JLabel("POLICE VERIFICATION");
            USER.setForeground(Color.white);
            AADHAR.setForeground(Color.white);
            VOTERID.setForeground(Color.white);
            STATUS.setForeground(Color.white);
            POLICEV.setForeground(Color.white);
            USER.setBounds(10,0,100,100);
            AADHAR.setBounds(200,0,100,100);
            VOTERID.setBounds(400,0,100,100);
            STATUS.setBounds(600,0,100,100);
            POLICEV.setBounds(770,0,150,100);
            AdminPanel.add(USER);
            AdminPanel.add(AADHAR);
            AdminPanel.add(VOTERID);
            AdminPanel.add(STATUS);
            AdminPanel.add(POLICEV);
    
            int x=0,y=30;
            while(rs.next())
            {
               JLabel label1=new JLabel(rs.getString("username"));
               JLabel label2=new JLabel(rs.getString("aadhar"));
               JLabel label3=new JLabel(rs.getString("voterid"));
               JLabel label4=new JLabel(rs.getString("status"));
               JLabel label5=new JLabel(rs.getString("POLICESTATUS"));
               label1.setBounds(10,y,100,100);
               label2.setBounds(x+200,y,100,100);
               label3.setBounds(x+400,y,100,100);
               label4.setBounds(x+600,y,100,100);
               label5.setBounds(x+800,y,100,100);
               label1.setForeground(Color.white);
               label2.setForeground(Color.white);
               label3.setForeground(Color.white);
               label4.setForeground(Color.white);
               label5.setForeground(Color.white);
               y+=50;
               AdminPanel.add(label1);
               AdminPanel.add(label2);
               AdminPanel.add(label3);
               AdminPanel.add(label4);
               AdminPanel.add(label5);
            }
           }
           catch(Exception e){System.out.println(e);}
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==Home)
        {
           new Menu();
           frame.setVisible(false);
        }
        else if(e.getSource()==Back)
        {
            new Login();
            frame.setVisible(false);
        }
        else{
            try{
                String name=usernameTextField.getText();
                usernameTextField.setText("");
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "vishal");
                System.out.println("Connected to database");
                Statement st=connect.createStatement();
                String s="select username from user_pas where username='"+name+"'";
                ResultSet rs=st.executeQuery(s);
                int f=0;
                while(rs.next())
                {
                   f=1;
                   break;
                }
                if(f==0)
                {
                    String optionse[]={"Ok","Cancel"};
                    JOptionPane.showOptionDialog(null, "User doesnot exist","Admin",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, optionse,optionse[0]);
                }
                else if(e.getSource()==UpdateStatus)
                {
                    String options[]={"Accepted","Rejected","Cancel"};
                    int out=JOptionPane.showOptionDialog(null, "Passport issue","Admin",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options,options[0]);
                    if(out==0||out==1)
                    {
                        String s1;
                       s1="update user_pas set STATUS='"+options[out]+"' where USERNAME='"+name+"'";
                       st.executeUpdate(s1);
                       display();
                    }
                }
                else
                {
                    int out=JOptionPane.showConfirmDialog(frame,"Send information to Police",
                            "Admin",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(out==JOptionPane.YES_OPTION)
                    { 
                        String s1="update user_pas set sent='YES' where username='"+name+"'";
                        st.execute(s1);
                    }
                }
            }
            catch(Exception exp){System.out.println(exp);} 
        }  
    }
}


-------------------------------------------------LOGIN MODULE -------------------------------------------------------------------------------------

public class Login extends JFrame implements ActionListener {
	
    JFrame frame;
    JButton Submit;
    JPanel loginPanel;
    JLabel usernameLabel,passwordLabel;
    JTextField usernameTextField,passwordTextField;
    JButton submiButton,Back;
    Login()
    {
       frame =new JFrame("Login");
       frame.setBounds(0,0,1400,1400);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       loginPanel=new JPanel();
       loginPanel.setBounds(300,100,700,350);
       loginPanel.setLayout(null);
       loginPanel.setBackground(Color.BLACK);
       
       usernameLabel=new JLabel("USERNAME:");
       usernameLabel.setBounds(70,40,100,100);
       usernameLabel.setForeground(Color.WHITE);
       passwordLabel=new JLabel("PASSWORD:");
       passwordLabel.setForeground(Color.WHITE);
       passwordLabel.setBounds(70,100,100,100);
       
       usernameTextField=new JTextField(20);
       usernameTextField.setBounds(200,70,300,35);
       passwordTextField=new JTextField(20);
       passwordTextField.setBounds(200,140,300,35);

       submiButton=new JButton("SUBMIT");
       submiButton.setFocusable(false);
       submiButton.setBounds(400,250,175,50);
       submiButton.addActionListener(this);
       submiButton.setBackground(Color.RED);
       submiButton.setForeground(Color.WHITE);
       
       Back=new JButton("BACK");
       Back.setBounds(1200,650,100,40);
       Back.setBackground(Color.DARK_GRAY);
       Back.setForeground(Color.WHITE);
       Back.setFocusable(false);
       Back.addActionListener(this);
       
       loginPanel.add(usernameLabel);
       loginPanel.add(usernameTextField);
       loginPanel.add(passwordLabel);
       loginPanel.add(passwordTextField);
       loginPanel.add(submiButton);

       frame.add(loginPanel);
       frame.add(Back);
       frame.setLayout(null);
       frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==submiButton)
        {
            String username=usernameTextField.getText();
            String password=passwordTextField.getText();
            usernameTextField.setText("");
            passwordTextField.setText("");
            if(username.equals("Admin")&&password.equals("Admin")){
               new Admin(); 
               frame.setVisible(false); 
            }
            else if(username.equals("Police")&&password.equals("Police"))
            {
               new Police();
               frame.setVisible(false);
            }
            else
            {
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "vishal");
                    System.out.println("Connected to database");
                    Statement st=connect.createStatement();
                    String s="select username from user_pas where username='"+username+"'and password='"+password+"'";
                    ResultSet rs=st.executeQuery(s);
                    int f=0;
                    while(rs.next())
                    {
                    f=1;
                    break;
                    }
                    if(f==0)
                    {
                        String optionse[]={"Ok","Cancel"};
                        JOptionPane.showOptionDialog(null, "Invalid user credentials..","Admin",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, optionse,optionse[0]);
                    }
                    else
                    {
                        new User();  
                        frame.setVisible(false);                  
                    }
                }
                catch(Exception exp){System.out.println(exp);}
            }
        }
        else
        {
            frame.setVisible(false);
            new Menu();
        }
    }
	

}


--------------------------------------------------------MENU-BAR MODULE-----------------------------------------------------------------------------

public class Menu extends JFrame implements ActionListener {
	
	
	JFrame frame;
    JLabel HomeLabel;
    JPanel HomePanel;
    CardLayout card;
    JButton LoginActivateButton,RegisterActivateButton;
    Menu()
    {
      frame =new JFrame("Home");
      frame.setBounds(0,0,1400,1400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      BufferedImage img = null;
      try {
        img = ImageIO.read(new File("C:\\Users\\vishal\\eclipse-workspace\\database\\src\\coursebasedproj\\pms_passport-management-system.png"));
      } catch (IOException e) {
        e.printStackTrace();}
      Image dimg = img.getScaledInstance(700,300,Image.SCALE_SMOOTH);
      HomeLabel=new JLabel("Welcome to Passport automation system...");
      ImageIcon icon3=new ImageIcon(dimg);
      HomeLabel.setIcon(icon3);
      HomeLabel.setVisible(true);
      HomeLabel.setBounds(300,0,710,370);
      HomeLabel.setVerticalTextPosition(JLabel.BOTTOM);
      HomeLabel.setHorizontalTextPosition(JLabel.CENTER);
      HomeLabel.setOpaque(true);
      HomeLabel.setIconTextGap(10);

      HomePanel=new JPanel();
      HomePanel.setLayout(null);
      HomePanel.setBackground(Color.GRAY);
      HomePanel.setSize(1400,1000);

      LoginActivateButton=new JButton("LOGIN");
      LoginActivateButton.addActionListener(this);
      LoginActivateButton.setBounds(200,500,200,75);
      LoginActivateButton.setBackground(Color.BLACK);
      LoginActivateButton.setForeground(Color.WHITE);
      LoginActivateButton.setFocusable(false);

     
      RegisterActivateButton=new JButton("REGISTER");
      RegisterActivateButton.addActionListener(this);
      RegisterActivateButton.setBackground(Color.BLACK);
      RegisterActivateButton.setForeground(Color.WHITE);
      RegisterActivateButton.setFocusable(false);
      RegisterActivateButton.setBounds(900,500,200,75);

      HomePanel.add(HomeLabel);
      HomePanel.add(LoginActivateButton);
      HomePanel.add(RegisterActivateButton);

      frame.setVisible(true);
      frame.add(HomePanel);
      frame.setLayout(null);
    }
   @Override
   public void actionPerformed(ActionEvent e) {
       if(e.getSource()==LoginActivateButton)
          {
            new Login();
            frame.setVisible(false);
          }
        else 
        {
          new Register();
          frame.setVisible(false);
        }
   }

}


-------------------------------------------------POLICE INTERFACE------------------------------------------------------------------------

public class Police extends JFrame implements ActionListener{
	     JFrame frame;
	     JLabel PoliceLabel;
	     JPanel policePanel,HeadingPanel;
	     JButton sendVerification,Home,Back;
	     JTextField usernameTextField;
	    Police()
	    {
	       frame =new JFrame("Police");
	       frame.setBounds(0,0,1400,1400);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	       PoliceLabel=new JLabel("POLICE AUTHENTICATION FOR PASSPORT");
	       PoliceLabel.setBounds(500,30,300,40);
	       PoliceLabel.setForeground(Color.WHITE);
	       PoliceLabel.setVisible(true);
	       PoliceLabel.setLayout(null);
	       
	       sendVerification=new JButton("Verified");
	       sendVerification.setBounds(700,430,175,50);
	       sendVerification.setBackground(Color.GRAY);
	       sendVerification.setForeground(Color.WHITE);
	       sendVerification.setFocusable(false);
	       sendVerification.addActionListener(this);

	       policePanel=new JPanel();
	       policePanel.setVisible(true);
	       policePanel.setBounds(200,150,900,500);
	       policePanel.setBackground(Color.BLACK);
	       policePanel.setLayout(null);

	       usernameTextField=new JTextField(30);
	       usernameTextField.setBounds(50,430,250,50);

	       HeadingPanel=new JPanel();
	       HeadingPanel.setBounds(0,0,1400,100);
	       HeadingPanel.setBackground(Color.DARK_GRAY);
	       HeadingPanel.setLayout(null);
	       HeadingPanel.setVisible(true);

	       Home=new JButton("HOME");
	       Home.setBounds(50,650,100,40);
	       Home.setBackground(Color.DARK_GRAY);
	       Home.setForeground(Color.WHITE);
	       Home.setFocusable(false);
	       Home.addActionListener(this);

	       Back=new JButton("BACK");
	       Back.setBounds(1200,650,100,40);
	       Back.setBackground(Color.DARK_GRAY);
	       Back.setForeground(Color.WHITE);
	       Back.setFocusable(false);
	       Back.addActionListener(this);
	       
	       HeadingPanel.add(PoliceLabel);
	       policePanel.add(sendVerification);
	       policePanel.add(usernameTextField);
	       frame.add(Home);
	       frame.add(Back);
	       display();

	       frame.add(HeadingPanel);
	       frame.add(policePanel);
	       frame.setLayout(null);
	       frame.setVisible(true);
	    }
	    void display()
	    {
	        try{
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "vishal");
	            System.out.println("Connected to database");
	            Statement st=connect.createStatement();
	            String s="select * from user_pas";
	            ResultSet rs=st.executeQuery(s);
	            JLabel USER =new JLabel("USERNAME");
	            JLabel AADHAR =new JLabel("AADHAR NUMBER");
	            JLabel VOTERID =new JLabel("VOTER ID");
	            JLabel STATUS =new JLabel("STATUS");
	            JLabel POLICEV =new JLabel("POLICE VERIFICATION");
	            USER.setForeground(Color.white);
	            AADHAR.setForeground(Color.white);
	            VOTERID.setForeground(Color.white);
	            STATUS.setForeground(Color.white);
	            POLICEV.setForeground(Color.white);
	            USER.setBounds(10,0,100,100);
	            AADHAR.setBounds(200,0,100,100);
	            VOTERID.setBounds(400,0,100,100);
	            STATUS.setBounds(600,0,100,100);
	            POLICEV.setBounds(770,0,150,100);
	            policePanel.add(USER);
	            policePanel.add(AADHAR);
	            policePanel.add(VOTERID);
	            policePanel.add(STATUS);
	            policePanel.add(POLICEV);
	    
	            int x=0,y=30;
	            while(rs.next())
	            {
	                String v=rs.getString("SENT");
	                if(v.equals("YES"))
	                {
	                    JLabel label1=new JLabel(rs.getString("username"));
	                    JLabel label2=new JLabel(rs.getString("aadhar"));
	                    JLabel label3=new JLabel(rs.getString("voterid"));
	                    JLabel label4=new JLabel(rs.getString("status"));
	                    JLabel label5=new JLabel(rs.getString("POLICESTATUS"));
	                    label1.setBounds(10,y,100,100);
	                    label2.setBounds(x+200,y,100,100);
	                    label3.setBounds(x+400,y,100,100);
	                    label4.setBounds(x+600,y,100,100);
	                    label5.setBounds(x+800,y,100,100);
	                    label1.setForeground(Color.white);
	                    label2.setForeground(Color.white);
	                    label3.setForeground(Color.white);
	                    label4.setForeground(Color.white);
	                    label5.setForeground(Color.white);
	                    y+=50;
	                    policePanel.add(label1);
	                    policePanel.add(label2);
	                    policePanel.add(label3);
	                    policePanel.add(label4);
	                    policePanel.add(label5);
	                }
	            }
	           }
	           catch(Exception e){System.out.println(e);}
	    }
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        if(e.getSource()==Home)
	        {
	           new Menu();
	        }
	        else if(e.getSource()==Back)
	        {
	            new Login();
	        }
	        else
	        {
	            try{
	                String name=usernameTextField.getText();
	                usernameTextField.setText("");
	                Class.forName("oracle.jdbc.driver.OracleDriver");
	                Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "vishal");
	                System.out.println("Connected to database");
	                Statement st=connect.createStatement();
	                String s="select username from user_pas where username='"+name+"'";
	                ResultSet rs=st.executeQuery(s);
	                int f=0;
	                while(rs.next())
	                {
	                   f=1;
	                   break;
	                }
	                if(f==0)
	                {
	                    String optionse[]={"Ok","Cancel"};
	                    JOptionPane.showOptionDialog(null, "User doesnot exist","Admin",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, optionse,optionse[0]);
	                }
	                else if(e.getSource()==sendVerification)
	                {
	                  int out=JOptionPane.showConfirmDialog(frame,"Update bg verfication status",
	                "Police",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
	                   if(out==JOptionPane.YES_OPTION)
	                   {
	                    String s1;
	                    s1="update user_pas set POLICESTATUS='Done',sent='NO' where USERNAME='"+name+"'";
	                    st.executeUpdate(s1);
	                   }
	                }
	             }
	             catch(Exception exp){System.out.println(exp);}

	        }
	    }
	}





---------------------------------------------REGISTER MODULE---------------------------------------------------------------------------

public class Register extends JFrame implements ActionListener{
    JFrame frame;
    JLabel usernameLabel,passwordLabel,nameLabel,addressLabel,dateOfBirthLabel,fathernameLabel,voterIdLabel,aadharNumberLabel,phoneLabel,emailLabel;
    JTextField usernameTextField,passwordTextField,nameTextField,addressTextField,dateOfBirthTextField,fatherNameTextField,voterIdTextField,aadharNumberTextField,phoneTextField,emailTextField;
    JPanel RegisterPanel;
    JButton submitButton,Back;
    Register()
    {
        frame =new JFrame("Register");
        frame.setBounds(0,0,1400,1400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       RegisterPanel=new JPanel();
       RegisterPanel.setForeground(Color.RED);
       RegisterPanel.setBackground(Color.GRAY);
       RegisterPanel.setBounds(0,0,1400,1400);

       usernameLabel=new JLabel("USERNAME");
       usernameLabel.setBounds(150,50,100,100);
       usernameLabel.setForeground(Color.WHITE);
       nameLabel=new JLabel("NAME");
       nameLabel.setBounds(150,100,100,100);
       nameLabel.setForeground(Color.WHITE);
       dateOfBirthLabel=new JLabel("DATE OF BIRTH");
       dateOfBirthLabel.setBounds(150,150,100,100);
       dateOfBirthLabel.setForeground(Color.WHITE);
       fathernameLabel=new JLabel("FATHERNAME");
       fathernameLabel.setForeground(Color.WHITE);
       fathernameLabel.setBounds(150,200,100,100);
       phoneLabel=new JLabel("PHONE NO");
       phoneLabel.setForeground(Color.WHITE);
       phoneLabel.setBounds(150,250,100,100);
       emailLabel=new JLabel("EMAIL ID");
       emailLabel.setForeground(Color.WHITE);
       emailLabel.setBounds(150,300,100,100);
       aadharNumberLabel=new JLabel("AADHAR NUMBER");
       aadharNumberLabel.setForeground(Color.WHITE);
       aadharNumberLabel.setBounds(150,350,100,100);
       voterIdLabel=new JLabel("VOTER ID");
       voterIdLabel.setForeground(Color.WHITE);
       voterIdLabel.setBounds(150,400,100,100);
       passwordLabel=new JLabel("PASSWORD");
       passwordLabel.setForeground(Color.WHITE);
       passwordLabel.setBounds(150,450,100,100);

       usernameTextField=new JTextField(20);
       usernameTextField.setBounds(450,75,300,40);
       nameTextField=new JTextField(20);
       nameTextField.setBounds(450,125,300,40);
       dateOfBirthTextField=new JTextField(20);
       dateOfBirthTextField.setBounds(450,175,300,40);
       fatherNameTextField=new JTextField(20);
       fatherNameTextField.setBounds(450,225,300,40);
       phoneTextField=new JTextField(20);
       phoneTextField.setBounds(450,275,300,40);
       emailTextField=new JTextField(20);
       emailTextField.setBounds(450,325,300,40);
       aadharNumberTextField=new JTextField(20);
       aadharNumberTextField.setBounds(450,375,300,40);
       voterIdTextField=new JTextField(20);
       voterIdTextField.setBounds(450,425,300,40);
       passwordTextField=new JTextField(20);
       passwordTextField.setBounds(450,475,300,40);

       submitButton=new JButton("CREATE NEW ACCOUNT");
       submitButton.addActionListener(this);
       submitButton.setBackground(Color.red);
       submitButton.setFocusable(false);
       submitButton.setBounds(800,550, 200,50);
       submitButton.setForeground(Color.WHITE);

       Back=new JButton("BACK");
       Back.setBounds(1200,650,100,40);
       Back.setBackground(Color.DARK_GRAY);
       Back.setForeground(Color.WHITE);
       Back.setFocusable(false);
       Back.addActionListener(this);
       

       RegisterPanel.setLayout(null);
       RegisterPanel.add(usernameLabel);
       RegisterPanel.add(nameLabel);
       RegisterPanel.add(dateOfBirthLabel);
       RegisterPanel.add(fathernameLabel);
       RegisterPanel.add(phoneLabel);
       RegisterPanel.add(emailLabel);
       RegisterPanel.add(aadharNumberLabel);
       RegisterPanel.add(voterIdLabel);
       RegisterPanel.add(passwordLabel);
       RegisterPanel.add(usernameTextField);
       RegisterPanel.add(nameTextField);
       RegisterPanel.add(dateOfBirthTextField);
       RegisterPanel.add(fatherNameTextField);
       RegisterPanel.add(phoneTextField);
       RegisterPanel.add(emailTextField);
       RegisterPanel.add(aadharNumberTextField);
       RegisterPanel.add(voterIdTextField);
       RegisterPanel.add(passwordTextField);
       RegisterPanel.add(submitButton);
       RegisterPanel.add(Back);
       RegisterPanel.setVisible(true);
       
       frame.add(RegisterPanel);
       frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==submitButton)
        {
            int option=JOptionPane.showConfirmDialog(frame,"Are you sure",
            "Admin",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(option==JOptionPane.YES_OPTION)
            {
               String Username,Name,DateofBirth,Phone,Email,Aadhar,VoterId,Password,FatherName,VStatus,PStatus,sent;
               Username=usernameTextField.getText();
               Name=nameTextField.getText();
               FatherName=fatherNameTextField.getText();
               DateofBirth=dateOfBirthTextField.getText();
               Phone=phoneTextField.getText();
               Email=emailTextField.getText();
               Aadhar=aadharNumberTextField.getText();
               VoterId=voterIdTextField.getText();
               Password=passwordTextField.getText();
               VStatus="Pending";
               PStatus="Notdone";
               sent="NO";
               String query="insert into user_pas values('"+Username+"','"+Name+"','"+FatherName+"','"+DateofBirth+"','"+Phone+"','"+Email+"','"+Aadhar+"','"+VoterId+"','"+Password+"','"+VStatus+"','"+PStatus+"','"+sent+"')";
               try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "vishal");
                System.out.println("Connected to database");
                Statement st=connect.createStatement();
                String s="select * from user_pas";
                int f=0;
                ResultSet rs=st.executeQuery(s);
                while(rs.next())
                {
                   if(Username.equals(rs.getString("USERNAME")))
                   {
                       f=1;
                       break;
                   }
                }
                if(f==1){
                    String options[]={"Ok","Cancel"};
JOptionPane.showOptionDialog(null, "User already exist","Admin",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options,options[0]);
                }
                else{
                    st.execute(query);
                    System.out.println("Inserted.........");
                    usernameTextField.setText("");
                    nameTextField.setText("");
                    passwordTextField.setText("");
                    fatherNameTextField.setText("");
                    dateOfBirthTextField.setText("");
                    voterIdTextField.setText("");
                    emailTextField.setText("");
                    aadharNumberTextField.setText("");
                    phoneTextField.setText("");
                    new Login();
                    frame.setVisible(false);
                }
               }
                catch(Exception exc) {System.out.println(exc);}
            }
        }
        else
        {
            frame.setVisible(false);
            new Menu();
        }
    }
}




---------------------------------------------------------------USER INTERFACE------------------------------------------------------------------------------


public class User extends JFrame implements ActionListener
{
    JFrame frame;
    JPanel userPanel,searchPanel,detailsPanel;
    JTextField username;
    JLabel Applicant,PoliceStatus,Status,Fathername,name,policeStatus,status,fathername;
    JButton Search,Home,Back;
    User()
    {
        frame =new JFrame("Police");
        frame.setBounds(0,0,1400,1400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        username=new JTextField(25);
        username.setBounds(350,10,250,50);

        Search=new JButton("SEARCH");
        Search.setBounds(660,10,200,50);
        Search.setBackground(Color.DARK_GRAY);
        Search.setForeground(Color.WHITE);
        Search.setFocusable(false);
        Search.addActionListener(this);

        Applicant=new JLabel("APPLICANT");
        Applicant.setBounds(30,70,200,50);
        PoliceStatus=new JLabel("POLICE STATUS");
        PoliceStatus.setBounds(30,130,200,50);
        Status=new JLabel("APPLICATION STATUS");
        Status.setBounds(30,190,200,50);
        Fathername=new JLabel("FATHER NAME");
        Fathername.setBounds(30,250,200,50);
        name=new JLabel("Dummy");
        name.setBounds(420,70,200,50);
        policeStatus=new JLabel("Dummy");
        policeStatus.setBounds(420,130,200,50);
        status=new JLabel("Dummy");
        status.setBounds(420, 190,100,50);
        fathername=new JLabel("Dummy");
        fathername.setBounds(420,250,100,50);

        userPanel=new JPanel();
        userPanel.setBounds(0,0,1400,1400);
        userPanel.setVisible(true);
        userPanel.setLayout(null);

       Home=new JButton("HOME");
       Home.setBounds(50,650,100,40);
       Home.setBackground(Color.DARK_GRAY);
       Home.setForeground(Color.WHITE);
       Home.setFocusable(false);
       Home.addActionListener(this);

       Back=new JButton("BACK");
       Back.setBounds(1200,650,100,40);
       Back.setBackground(Color.DARK_GRAY);
       Back.setForeground(Color.WHITE);
       Back.setFocusable(false);
       Back.addActionListener(this);

        searchPanel=new JPanel();
        searchPanel.setBounds(50,50,1400,60);
        searchPanel.setVisible(true);
        searchPanel.setLayout(null);

        searchPanel.add(username);
        searchPanel.add(Search);

        detailsPanel=new JPanel();
        detailsPanel.setBounds(380,150,600,400);
        detailsPanel.setVisible(false);
        detailsPanel.setLayout(null);
        detailsPanel.setBackground(Color.gray);
        detailsPanel.setForeground(Color.WHITE);
        
        detailsPanel.add(Applicant);
        detailsPanel.add(Status);
        detailsPanel.add(PoliceStatus);
        detailsPanel.add(Fathername);
        detailsPanel.add(fathername);
        detailsPanel.add(policeStatus);
        detailsPanel.add(status);
        detailsPanel.add(name);
        userPanel.add(searchPanel);
        userPanel.add(detailsPanel);
        frame.add(Home);
        frame.add(Back);
        frame.add(userPanel);

        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Search)
        {
          String str=username.getText();
          username.setText("");
          try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "vishal");
            System.out.println("Connected to database");
            Statement st=connect.createStatement();
            String s="select * from user_pas where username='"+str+"'";
            ResultSet rs=st.executeQuery(s);
            int f=0;
            while(rs.next())
            {
            f=1;
            break;
            }
            if(f==0)
            {
                String optionse[]={"Ok","Cancel"};
                JOptionPane.showOptionDialog(null, "Invalid username","Admin",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, optionse,optionse[0]);
            }
            else
            {
               name.setText(rs.getString("username"));
               status.setText(rs.getString("status"));
               policeStatus.setText(rs.getString("policestatus"));
               fathername.setText(rs.getString("fathername"));
               detailsPanel.setVisible(true);               
            }
        }
        catch(Exception exp){System.out.println(exp);}

        }
        else if(e.getSource()==Home)
        {
            frame.setVisible(false);
            new Menu();
        }
        else{
            frame.setVisible(false);
            new Login();
        }
    }
}



	public static void main(String[] args) {
		new Menu();
		// TODO Auto-generated method stub

	}

}

