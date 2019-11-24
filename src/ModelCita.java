
public class ModelCita {
	public String fechaCita;
	public String horarioCita;
	public String fechaRegistro;
	public String descripcion;
	
	public ModelCita(String nuevaFechaCita, String nuevoHorarioCita, String nuevaFechaRegistro, String nuevaDescripcion) {
		this.fechaCita = nuevaFechaCita;
		this.horarioCita = nuevoHorarioCita;
		this.fechaRegistro = nuevaFechaRegistro;
		this.descripcion = nuevaDescripcion;
	}
}
