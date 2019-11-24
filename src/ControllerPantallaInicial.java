import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
public class ControllerPantallaInicial implements ActionListener {

	
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == FramePantallaInicial.btnRegistrarPaciente) {
	        Component component = (Component) evento.getSource();
	        JFrame framePantallaInicial = (JFrame) SwingUtilities.getRoot(component);
	        framePantallaInicial.setVisible(false);
	        FrameRegistraPaciente frameRegistraPaciente = new FrameRegistraPaciente();
	        frameRegistraPaciente.setVisible(true);
		}else if (evento.getSource() == FramePantallaInicial.btnRegistrarCita) {
	        Component component = (Component) evento.getSource();
	        JFrame framePantallaInicial = (JFrame) SwingUtilities.getRoot(component);
	        framePantallaInicial.setVisible(false);
	        FrameRegistraCita frameRegistraCita = new FrameRegistraCita();
	        frameRegistraCita.setVisible(true);
		}
	}
 
}
