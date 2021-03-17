package Model.TypesAbstracao;

public class RegistroIdentificador {

	private Object[] identificadores;

	public Object[] getRegistro() {
		return identificadores;
	}
	public RegistroIdentificador(Object[] identificadores) {
		super();
		this.identificadores=identificadores;
		// TODO Auto-generated constructor stub
	}
	public RegistroIdentificador(Object identificador) {
		this.identificadores=new Object[1];
		this.identificadores[0]=identificador;
	}
	public boolean comparaRegistro(RegistroIdentificador identificadores) {
		if(this==identificadores||this.identificadores==identificadores.getRegistro()) {
			return true;
		}if(identificadores.getRegistro().length!=this.identificadores.length){
			return false;
		}
		for(int i=0;i<this.identificadores.length;i++) {
			if(identificadores.getRegistro()[i]!=this.identificadores[i]) {
				return false;
			}
		}
		return true;
	}
	
}
