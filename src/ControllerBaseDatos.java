import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.ResultSetMetaData;

public class ControllerBaseDatos{

    private static Connection conn;

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost/ControlCita";

    public ControllerBaseDatos(){
        conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null){
                System.out.println("Se conecto");
            }
        }catch(ClassNotFoundException e){
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        }catch(SQLException e){
            System.out.println("No se conecto");
        }
    }
    public Connection getConnection(){
        return conn;
    }
    public void DesConnecton(){
        conn = null;
        System.out.println("Termino conexion");
    }
    public ArrayList<String> buscaCorreoPacientes() {
    	ArrayList<String> correoPacientes = new ArrayList<String>();
    	Statement stBuscaCorreoPacientes;
		try {
			stBuscaCorreoPacientes = conn.createStatement();
		
			String sqlBuscaCorreoPacientes = "select correo from paciente;";
			ResultSet rsBuscaCorreoPacientes = null;
			
		
			rsBuscaCorreoPacientes = stBuscaCorreoPacientes.executeQuery(sqlBuscaCorreoPacientes);
			while (rsBuscaCorreoPacientes.next()) {
				correoPacientes.add(rsBuscaCorreoPacientes.getString("correo"));
			}		
			return correoPacientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correoPacientes;
    }
    static public String buscaPacientePorNombre(String correoPaciente) {
    	String nombrePaciente = "";
    	try {
    		Statement stBuscaNombrePaciente = conn.createStatement();
    		String sqlBuscaNombrePaciente = "select nombre from paciente where correo = '" + correoPaciente + "';";
    		ResultSet rsBuscaNombrePaciente = null;
    		
    	
    		rsBuscaNombrePaciente = stBuscaNombrePaciente.executeQuery(sqlBuscaNombrePaciente);
			if (rsBuscaNombrePaciente.next()) {
				nombrePaciente = rsBuscaNombrePaciente.getString("nombre");
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return nombrePaciente;
    }
    public String buscaCorreoNutrologa(String nombreNutrologa) {
    	String correoNutrologa = "";
    	try {
    		Statement stBuscaCorreoNutrologa = conn.createStatement();
    		String sqlBuscaCorreoNutrologa = "select correo from medico where nombre = '" + nombreNutrologa + "';";
    		ResultSet rsBuscaCorreoNutrologa = null;
    		
    	
    		rsBuscaCorreoNutrologa = stBuscaCorreoNutrologa.executeQuery(sqlBuscaCorreoNutrologa);
			if (rsBuscaCorreoNutrologa.next()) {
				correoNutrologa = rsBuscaCorreoNutrologa.getString("correo");
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return correoNutrologa;
    	
    }
    public void insertaPaciente(ModelPaciente nuevoPaciente) throws SQLException{
    	Statement stBuscaPaciente = conn.createStatement();
    	String sqlBuscaPaciente = "select * from paciente where correo = '" + nuevoPaciente.correo + "';";
    	ResultSet rsBuscaPaciente = stBuscaPaciente.executeQuery(sqlBuscaPaciente);
    	if (rsBuscaPaciente.next()) {
    		JOptionPane.showMessageDialog(null, "Paciente ya está en la base de datos.");
    		return;
    	}
    	
    	
    	Statement statementInsertaPaciente = conn.createStatement();
    	Statement stIdPaciente = conn.createStatement();
    	String sqlIDPaciente = "select max(idPaciente) from Paciente order by 1 asc";
    	ResultSet rsIdPaciente = stIdPaciente.executeQuery(sqlIDPaciente);
    	int idPaciente = 0;
    	if (rsIdPaciente.next()){
    		idPaciente = rsIdPaciente.getInt("max(idPaciente)") + 1;
    	}
    	 String sqlInsertaPaciente = "INSERT INTO PACIENTE " + 
    			 					"VALUES ("+
    			 					"'" + nuevoPaciente.nombre + "'," +
    			 					"'" + nuevoPaciente.apellidoPaterno + "'," +
    			 					"'" + nuevoPaciente.apellidoMaterno + "'," +
    			 					"'" + nuevoPaciente.correo + "'," + 
    			 							idPaciente + ")";
    	 statementInsertaPaciente.executeUpdate(sqlInsertaPaciente);		 					
    	 					
    }
	public void registraCita(ModelCita nuevaCita, String correoPaciente, String nombreMedico) {
		// TODO Auto-generated method stub
		int idPaciente = buscaIdPaciente(correoPaciente);
		int idMedico = buscaIdMedico(nombreMedico);
		System.out.println(idMedico);
		int idCita = buscaMayorIdCita();
		
		
		try {
			Statement statementInsertaCita = conn.createStatement();
 
			String sqlInsertaCita = "INSERT INTO Cita " + 
    			 					"VALUES ("+
    			 					"'" + nuevaCita.fechaCita + "'," +
    			 					"'" + nuevaCita.horarioCita + "'," +
    			 						  idMedico + "," +
    			 						  idPaciente + "," + 
    			 					"'" + nuevaCita.fechaRegistro + "'," +
    			 					"'" + nuevaCita.descripcion + "'," +
    			 						  idCita + ");";
			statementInsertaCita.executeUpdate(sqlInsertaCita);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		
	}
	private int buscaMayorIdCita() {
		int idCita = 0;
		try {
			Statement stIdCita = conn.createStatement();
			String sqlIDCita = "select max(idCita) from Cita order by 1 asc";
			ResultSet rsIdCita = stIdCita.executeQuery(sqlIDCita);
			if (rsIdCita.next()){
				idCita = rsIdCita.getInt("max(idCita)") + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return idCita;
	}
	private int buscaIdPaciente(String correoPaciente) {
		// TODO Auto-generated method stub
		int idPaciente = 0;
		try {
			Statement stIdPaciente = conn.createStatement();
			String sqlIDPaciente = "select idPaciente from Paciente where correo = '" + correoPaciente + "';";
	   		ResultSet rsIdPaciente;
			rsIdPaciente = stIdPaciente.executeQuery(sqlIDPaciente);
	    	if (rsIdPaciente.next()){
	    		idPaciente = rsIdPaciente.getInt("idPaciente");
	    	}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idPaciente;
	}
   

	private int buscaIdMedico(String nombreMedico) {
		// TODO Auto-generated method stub
		int idMedico = 0;
		try {
			Statement stIdMedico = conn.createStatement();
			String sqlIDMedico = "select idMedico from Medico where nombre = '" + nombreMedico + "';";
	   		ResultSet rsIdMedico;
			rsIdMedico = stIdMedico.executeQuery(sqlIDMedico);
	    	if (rsIdMedico.next()){
	    		idMedico = rsIdMedico.getInt("idMedico");
	    	}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idMedico;
	}
   
}
