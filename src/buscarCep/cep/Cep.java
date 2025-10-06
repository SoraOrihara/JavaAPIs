package buscarCep.cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox<Object> cboUf;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cep() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/buscarCep/img/home20x20.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(27, 20, 46, 14);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(64, 18, 111, 17);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(27, 70, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(83, 67, 278, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setBounds(27, 123, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtBairro = new JTextField();
		txtBairro.setBounds(83, 120, 278, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setBounds(27, 187, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtCidade = new JTextField();
		txtCidade.setBounds(83, 184, 189, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("UF");
		lblNewLabel_4.setBounds(315, 187, 46, 14);
		contentPane.add(lblNewLabel_4);

		cboUf = new JComboBox<Object>();
		cboUf.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(342, 183, 46, 22);
		contentPane.add(cboUf);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampo();
			}
		});
		btnLimpar.setBounds(24, 238, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					// Mensagem caso cep esteja nulo
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					// Fazer o mouse voltar ao cep
					txtCep.requestFocus();
				} else {
					// buscar CEP
					buscarCep();
				}

			}
		});
		btnCep.setBounds(240, 16, 89, 23);
		contentPane.add(btnCep);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Assim que linka o jFrame com o JDialog
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/buscarCep/img/about20x20.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(392, 11, 40, 40);
		contentPane.add(btnSobre);

		// Uso da biblioteca Atxy2k para validação do campo txtCep
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(184, 20, 48, 48);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	}
	
	private void buscarCep() {
		String logradouro ="";
		String tipoLogradouro="";
		String resultado=null;
		String cep=txtCep.getText();
		
		try {
			@SuppressWarnings("deprecation")
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep="+cep+"&formato=xml");
			//Simple api xml
			SAXReader xml = new SAXReader();
			//Ele vai capturar os dados que vem no xml
			Document documento = xml.read(url);
			//Será usada como apoio para a varredura do documento xml
			Element root=documento.getRootElement();
			
			
			// iterate through child elements of root
		    for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
		        Element element = it.next();
		        if(element.getQualifiedName().equals("cidade")) {
		        	txtCidade.setText(element.getText());
		        }
		        if(element.getQualifiedName().equals("bairro")) {
		        	txtBairro.setText(element.getText());
		        }
		        if(element.getQualifiedName().equals("uf")) {
		        	cboUf.setSelectedItem(element.getText());
		        }
		        if(element.getQualifiedName().equals("tipo_logradouro")) {
		        	tipoLogradouro=element.getText();
		        }
		        if(element.getQualifiedName().equals("logradouro")) {
		        	logradouro=element.getText();
		        }
		        if(element.getQualifiedName().equals("resultado")) {
		        	resultado = element.getText();
		        	if(resultado.equals("1")) {
		        		lblStatus.setIcon(new ImageIcon(getClass().getResource("/buscarCep/img/check48x48.png")));
		        	}else {
		        		JOptionPane.showMessageDialog(null, "CEP não encontrado");
		        	}
		        }
		    }
			//Setar o campo endereço
		    txtEndereco.setText(tipoLogradouro+" "+logradouro);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void limparCampo() {
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
	}
}
