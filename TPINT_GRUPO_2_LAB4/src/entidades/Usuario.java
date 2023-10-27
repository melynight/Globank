package entidades;

public class Usuario {
	
	private String id;
	private String dni;
	private boolean esAdmin;
	private int idRef;
	private String contrase�a;
	private String nombreUsuario;
	private boolean estado;
	
	public Usuario(String id, String dni, boolean esAdmin, int idRef, String contrase�a, String nombreUsuario,
			boolean estado) {
		super();
		this.id = id;
		this.dni = dni;
		this.esAdmin = esAdmin;
		this.idRef = idRef;
		this.contrase�a = contrase�a;
		this.nombreUsuario = nombreUsuario;
		this.estado = estado;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public boolean isEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	public int getIdRef() {
		return idRef;
	}
	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contrase�a == null) ? 0 : contrase�a.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + (esAdmin ? 1231 : 1237);
		result = prime * result + (estado ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + idRef;
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (contrase�a == null) {
			if (other.contrase�a != null)
				return false;
		} else if (!contrase�a.equals(other.contrase�a))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (esAdmin != other.esAdmin)
			return false;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idRef != other.idRef)
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ID usuario: " + id + ", dni: " + dni + ", es admin: " + esAdmin + ", ID de referencia: " + idRef + ", contrase�a: "
				+ contrase�a + ", nombre de usuario: " + nombreUsuario + ", estado: " + estado;
	}
	
	
}
