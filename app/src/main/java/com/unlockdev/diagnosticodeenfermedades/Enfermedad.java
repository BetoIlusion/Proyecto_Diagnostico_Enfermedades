package com.unlockdev.diagnosticodeenfermedades;

class Enfermedad {
    private static int MAX = 15+1;  //maximo de sintomas a ingresar al vector 'sintomas[]'
                                    //Si se desea aumentar mas sintomas se modifica MAX
    private Enfermedad siguiente;   //Es el puntero de la enfermedad
    private String enfermedad;      //Nombre de la enfermedad
    private String[] sintomas;      //vector de sintomas
                                    //Cada enfermedad tiene su vector de sintomas
    private int[] marcado;          //todos los sintomas empiezan en 0(sintomas no preguntados)
                                    //si es 1-> es que SI presenta el sintoma
                                    //si es 2-< es que NO presenta el sintoma
    private int[] valor;            //se guarda el valor de cada sintoma 
                                    //del 1(min) al 5(max)
    private int n;                  //cantidad de sintomas ingresadas en una enfermedad

    
    //metodo para crear la lista de las enfermedades con sus sintomas
    public Enfermedad(String Enfermedad,String Sintoma, int valor_sintoma){
        siguiente = null;
        enfermedad = Enfermedad;
        sintomas = new String[MAX];
        for(int i=0;i<sintomas.length;i++){
            sintomas[i]="";
        }
        valor = new int[MAX];
        for(int i=0;i<valor.length;i++){
            valor[i]=0;
        }
        marcado = new int[MAX];
        for(int i=0;i<marcado.length;i++){
            marcado[i]=0;
        }
        n = 0;
        sintomas[0]=Sintoma;
        valor[0]=valor_sintoma;
        n++;
    }

    public int getMarcado(int i) {
        return marcado[i];
    }

    public void setMarcado(int i, int marca) {
        this.marcado[i] = marca;
    }

    public void insertarMarca(int i,int marca){
        marcado[i]=marca;
    }

    public Enfermedad getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Enfermedad sig_enfermedad) {
        this.siguiente = sig_enfermedad;
    }

    public static int getMAX() {
        return MAX;
    }

    public static void setMAX(int MAX) {
        Enfermedad.MAX = MAX;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String[] getSintomas() {
        return sintomas;
    }

    public int getValor(int i) {
        return valor[i];
    }

    public void setValor(int[] valor) {
        this.valor = valor;
    }

    public int[] getValores(){
        return this.valor;
    }
    
    //insertamos el sintoma en el vector y su valor en otro vector, dentro la lista "enfermedad"
    //que le corresponde
    public void insertarSintoma(String nombre_sintoma, int valor_sintoma){
        for(int i=0;i<sintomas.length;i++){
            if(valor[i]==0){
                sintomas[i]=nombre_sintoma;
                valor[i]=valor_sintoma;
                n++;
                return;
            }
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
    //devuelve el total de los valores de los sintomas
    public int TotalValores(){
        int suma = 0;
        int i=0;
        while(i<=valor.length && valor[i]>0){
            suma = suma + valor[i];
            i++;
        }
        return suma;
    }
    
    public String getSintoma(int i){
        return sintomas[i];
    }
}
