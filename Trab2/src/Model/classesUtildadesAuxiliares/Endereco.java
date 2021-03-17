package Model.classesUtildadesAuxiliares;

public class Endereco {

	private String rua;
	private String bairro;
	private String cidade;
	private String pais;
	private int numero;
	private long CEP;
	public long getCEP() {
		return CEP;
	}
	public void setCEP(long cEP) {
		CEP = cEP;
	}
	public String getRua() {
		return rua;
	}
	public Endereco(int CEP) {
		super();
		this.CEP=CEP;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return new String(pais+" "+cidade+" "+bairro+" "+rua+" "+numero);
		return new String(CEP+"");
	}
	public boolean isEnderecoValido() {
		return true;
	}
	/*public void buscarCep(String cep) 
    {
        String json;        

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();
            
            // JOptionPane.showMessageDialog(null, json);
            
            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");                       
            String array[] = new String[30];
            array = json.split("\n");
            
            // JOptionPane.showMessageDialog(null, array);
                             
            logradouro = array[7];            
            bairro = array[15];
            cidade = array[19]; 
            uf = array[23];
            
            jTxtLogradouro.setText(logradouro);
            jTxtBairro.setText(bairro);
            jTxtCidade.setText(cidade);
            jTxtEstado.setText(uf);
            //JOptionPane.showMessageDialog(null, logradouro + " " + bairro + " " + cidade + " " + uf);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}
