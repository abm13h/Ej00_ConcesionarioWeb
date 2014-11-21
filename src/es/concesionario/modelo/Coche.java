package es.concesionario.modelo;

public class Coche 
{
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private int numcaballos;
    private boolean marchas;
	
    // Borramos/asteriscamos el método que se ha creado por defecto 
    // public Coche(String nombre, int habitantes){}
    
    // Para crear los métodos con Eclipse:
    // 1) constructor vacío
    // 2) constructor con todos los campos
    // 3) Métodos getter and setter de todos los campos...
    
    public Coche() {super();}

	public Coche(String matricula, String marca, String modelo,
			String color, int numcaballos, boolean marchas) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.numcaballos = numcaballos;
		this.marchas = marchas;
	}

	public int getId() {return id;}
	public void setId(int id) {
		this.id = id;}

	public String getMatricula() {return matricula;}
	public void setMatricula(String matricula) {
		this.matricula = matricula;}

	public String getMarca() {return marca;}
	public void setMarca(String marca) {
		this.marca = marca;}

	public String getModelo() {return modelo;}
	public void setModelo(String modelo) {
		this.modelo = modelo;}

	public String getColor() {return color;}
	public void setColor(String color) {
		this.color = color;}

	public int getNumcaballos() {return numcaballos;}
	public void setNumcaballos(int numcaballos) {
		this.numcaballos = numcaballos;}

	public boolean isMarchas() {return marchas;}
	public void setMarchas(boolean marchas) {
		this.marchas = marchas;}
	
}
