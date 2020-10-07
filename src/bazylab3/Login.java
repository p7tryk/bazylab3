package bazylab3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login
	{
		// to tylko struct ktory bedzie przechowywal uzytkownika
		String username;
		String password;
		String ip;
		int port;
		Boolean gonext = false;

		static JFrame okno = null;

		public Login(String username, String password, String ip, int port)
			{
				this.username = username;
				this.password = password;
				this.ip = ip;
				this.port = port;
			}

		public Login()
			{
				okno = new JFrame("Połacz sie do bazy MySQL");
				okno.setSize(1024, 780);

				JPanel jp2 = new JPanel();
				JButton jbconnect = new JButton("polacz");

				final JTextField tfip = new JTextField("127.0.0.1");
				final JTextField tfport = new JTextField("3306");
				final JTextField tfuser = new JTextField("admin");
				final JTextField tfpass = new JTextField("pwsz");

				jp2.add(tfip);
				jp2.add(tfport);
				jp2.add(tfuser);
				jp2.add(tfpass);
				jp2.add(jbconnect);

				okno.getContentPane().add(jp2);

				okno.setVisible(true);

				jbconnect.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
							{
								ip = tfip.getText();
								port = Integer.parseInt(tfport.getText());
								username = tfuser.getText();
								password = tfpass.getText();
								gonext = true;
								System.out.print(ip + port + username + password);
							}
					});
				//TODO czekac na to az uzytkownik kliknie zanim jechac dalej
				while(!gonext)
				{
					
				}
			}

	}
