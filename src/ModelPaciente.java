

public class ModelPaciente {
	public String nombre;
	public String apellidoPaterno;
	public String apellidoMaterno;
	public String correo;
	public ModelPaciente(String nuevoNombre, String nuevoAPaterno, String nuevoAMaterno, String nuevoCorreo) {
		this.nombre = nuevoNombre;
		this.apellidoPaterno = nuevoAPaterno;
		this.apellidoMaterno = nuevoAMaterno;
		this.correo = nuevoCorreo;
	}
	
}
