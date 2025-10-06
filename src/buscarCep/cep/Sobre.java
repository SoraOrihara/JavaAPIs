package buscarCep.cep;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/buscarCep/img/home20x20.png")));
		setResizable(false);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP - ver 1.0");
		lblNewLabel.setBounds(10, 11, 141, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Author: Caio de Oliveira");
		lblNewLabel_1.setBounds(10, 57, 141, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Web Service: ");
		lblNewLabel_2.setBounds(10, 104, 83, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblWebService = new JLabel("republicavirtual.com.br");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.republicavirtual.com.br/cep/exemplos.php");
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setForeground(SystemColor.textHighlight);
		lblWebService.setBounds(92, 104, 141, 14);
		getContentPane().add(lblWebService);
		
		JButton btnGitHub = new JButton("");
		btnGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/SoraOrihara/JavaAPIs");  
			}
		});
		
		btnGitHub.setBackground(SystemColor.control);
		btnGitHub.setForeground(SystemColor.control);
		btnGitHub.setToolTipText("Projeto");
		btnGitHub.setBorder(null);
		btnGitHub.setIcon(new ImageIcon(Sobre.class.getResource("/buscarCep/img/github20x20.png")));
		btnGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGitHub.setBounds(10, 129, 72, 48);
		getContentPane().add(btnGitHub);

	}

	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	
	
}
