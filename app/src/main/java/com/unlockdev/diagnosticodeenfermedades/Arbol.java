package com.unlockdev.diagnosticodeenfermedades;

import android.widget.TextView;

class Arbol{
    private Nodo raiz;                  //raiz del arbol
    private Enfermedad enfermedades;    //lista de enfermedades
    private int n;                      //cantidad de enfermedades que tiene la lista

    //metodo para crear el arbol
    public Arbol() {
       raiz = null;
       enfermedades = null;
       n = 0;
    }

    //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
    //CODIGOS DE LAS ENFERMEDADES Y SINTOMAS
    //INICIO DEL CODIGO

    //metodo para insertar los sintomas en el arbol
    public void insertarSintomaArbol(String sintoma, int informacion){
        if(informacion == 0){   //SI    -> inserta a la izquierda
            if(raiz == null){
                Nodo ini = new Nodo(sintoma, 1);
                raiz = ini;
            }else{
                Nodo nuevo = new Nodo(sintoma, 1);
                Nodo aux = ultimoNodo();
                aux.setHI(nuevo);
            }
            //marcamos el sintoma recorrido
            actualizar_marcado_Sintoma(sintoma, 1);
        }else if (informacion == 1){    //NO    -> inserta a la derecha
            if(raiz == null){
                Nodo ini = new Nodo(sintoma, 2);
                raiz = ini;
            }else{
                Nodo nuevo = new Nodo(sintoma, 2);
                Nodo aux = ultimoNodo();
                aux.setHD(nuevo);
            }
            actualizar_marcado_Sintoma(sintoma, 2);
        }
    }

    //devuelve el sintoma
    public String recogerSintoma(){
        Enfermedad aux = enfermedades;
        while(aux!=null){
            for(int j = 0;j<aux.getN();j++){
                if(aux.getMarcado(j)==0){//0=no fue marcado; 1=SI; 2=NO
                    return aux.getSintoma(j);
                }
            }
            aux = aux.getSiguiente();
        }
        return "vacio";
    }
    
    //marcamos el sintoma que fue preguntado
    //  0-->no fue recorrido
    //  1-->el usuario puso que SI
    //  2-->el usuario puso que NO
    public void actualizar_marcado_Sintoma(String Sintoma,int marca){
        Enfermedad aux = enfermedades;
        while(aux!=null){
            for(int j = 0;j<=aux.getN();j++){
                if(aux.getSintoma(j).equals(Sintoma)){
                    aux.setMarcado(j, marca);
                }
            }
            aux = aux.getSiguiente();
        }
    }

    //verifica si un nodo es hoja
    private boolean esHoja(Nodo pr) {
        return pr.getHI() == null && pr.getHD() == null;
    }

    //devuelve el ultimo nodo(sintoma) insertado en el arbol
    private Nodo ultimoNodo(){
        return ultimoNodo(raiz);
    }    
    
    private Nodo ultimoNodo(Nodo P){
        if(P==null)
            return null;
        if(esHoja(P)){
            return P;
        }else{
            Nodo h1 = ultimoNodo(P.getHI());
            Nodo h2 = ultimoNodo(P.getHD());
            if(h1!=null)
                return h1;
            if(h2!=null)
                return h2;

        }
        return null;
    }
    
    //devuelve el nodo de la enfermedad con un 100% de probabilidad
    public Nodo PosibleEnfermedad(){
        Enfermedad aux = enfermedades;
        int j = 0;
        while(aux!=null){
            while(j<=aux.getN()){
                //verifica que sus todos sus sintomas esten confirmados
                //por el usuario (eso daria el 100% de una enfermedad)
                if(aux.getMarcado(j)==0 || aux.getMarcado(j)==2)
                    break;
                j++;
            }
            if(j==aux.getN()){
                Nodo nuevo = new Nodo(aux.getEnfermedad(),1);
                return nuevo;
            }
            j = 0;
            aux = aux.getSiguiente();
        }
        return null;
    }

    //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
    //metodos para recoger la informacion de los sintomas y las enfermedades
    
    //metodo para insertar los sintomas a sus enfermedades en la lista de enfermedades
    public void insertarInformacion(String Enfermedad,String Sintoma,int valor){
        Enfermedad nuevaEnfermedad = new Enfermedad(Enfermedad,Sintoma,valor);
        Enfermedad Enfermedades = enfermedades;
        Enfermedad anteriorEnfermedad = null;    //obtiene el nodo de la enfermedad anterior
        while(Enfermedades!=null){
            if(Enfermedades.getEnfermedad().equals(Enfermedad)){//si encontro la enfermedad en la lista
                                                                //inserta el sintoma con su valor
                Enfermedades.insertarSintoma(Sintoma, valor);
                return;
            }
            anteriorEnfermedad = Enfermedades;
            Enfermedades = Enfermedades.getSiguiente();
        }
        if(anteriorEnfermedad==null){  
            enfermedades = nuevaEnfermedad;
        }
        else{
            anteriorEnfermedad.setSiguiente(nuevaEnfermedad);
        }
        n++; //cantidad de enfermedades de la lista
    }


    //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
    //CALCULAR LAS PROBABILIDADES

    //metodo para encontrar la enfermedad mas probable FLOAT (%)
    public float enfMasProbableFLOAT(){
        String[] sintomas = new String[50]; //vector de sintomas confirmados
        int n = 0;  //cantidad de sintomas confirmados que registra el usuario
        Enfermedad Enfermedad = enfermedades;
        float probabilidad = 0;
        float MayorProbabilidad = 0;
        Nodo P = raiz;
        int i = 0;
        while(P!=null){//recorre el arbol para almacenar los sintomas positivos del usuario
            if(P.getMarca()==1){//si esta positivo en el sintoma(1) lo guarda en el vector
                sintomas[i]=P.getSintoma();
                n++;
                i++;
            }
            if(P.getHI()!=null){
                P=P.getHI();
            }else {
                P = P.getHD();
            }
        }
        //recorre las enfermedades para sacar la mayor probabilidad
        while(Enfermedad!=null){
            //enviamos enfermedad por enfermedad a que calcule su probabilidad
            //comparando con los sintomas confirmados del arbol
            probabilidad = calcularProbabilidad(Enfermedad.getSintomas(),Enfermedad.getValores(),sintomas,Enfermedad.getN(),n);
            if(probabilidad>MayorProbabilidad){
                MayorProbabilidad = probabilidad;
            }
            Enfermedad = Enfermedad.getSiguiente();
        }
        return MayorProbabilidad;
    }

    //metodo para devolver la enfermedad mas probable STRING
    public String EnfermedadMasProbableSTRING(){
        String EnfermadadFinal = "";
        String[] sintomas = new String[50];
        int n = 0;
        Enfermedad auxEnf = enfermedades;
        float probabilidad = 0;
        float MaxProbable = 0;
        Nodo P = raiz;
        int i = 0;
        while(P!=null){
            if(P.getMarca()==1){
                sintomas[i]=P.getSintoma();
                n++;
                i++;
            }
            if(P.getHI()!=null){
                P=P.getHI();
            }else {
                P = P.getHD();
            }
        }
        while(auxEnf!=null){
            probabilidad = calcularProbabilidad(auxEnf.getSintomas(),auxEnf.getValores(),sintomas,auxEnf.getN(),n);
            if(probabilidad>MaxProbable){
                MaxProbable = probabilidad;
                EnfermadadFinal = auxEnf.getEnfermedad();
            }
            auxEnf = auxEnf.getSiguiente();
        }
        return EnfermadadFinal;
    }

    //metodo que devuelve la probabilidad de una enfermedad
    private float calcularProbabilidad(String[] sinto,int[] data, String[] sintoConf,int nFin,int nFinConf) {
        int j = 0;
        float totalSintoma = 0;
        float totalSintomaConfirm = 0;
        for (int i = 0; i < nFin; i++) {
            String s1 = sinto[i];
            while (j < nFinConf) {
                String s2 = sintoConf[j];
                if (sinto[i].equals(sintoConf[j])) {
                    totalSintomaConfirm = totalSintomaConfirm + data[i];
                    j = sintoConf.length;
                }
                j++;
            }
            totalSintoma = totalSintoma + data[i];
            j = 0;
        }

        float Probabilidad = (totalSintomaConfirm / totalSintoma) * 100;
        return Probabilidad;

     }
     //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
     public String mostrarTodasLasEnfermedades(){
         String[] sintomas = new String[50];
         int n = 0;
         Enfermedad Enf = enfermedades;
         float probabilidadRes = 0;
         String nombreEnf="",salida = "";
         Nodo P = raiz;
         while(P!=null){
             if(P.getMarca()==1){
                 sintomas[n] = P.getSintoma();
                 n++;
             }
             if(P.getHI()!=null){
                 P = P.getHI();
             }else {
                 P = P.getHD();
             }
         }
         while(Enf!=null){
             probabilidadRes = calcularProbabilidad(Enf.getSintomas(),Enf.getValores(),sintomas,Enf.getN(),n);
             nombreEnf = Enf.getEnfermedad();
             salida = salida + " " + nombreEnf + " --> " + probabilidadRes +" % \n";
             Enf = Enf.getSiguiente();
         }
         return salida;
     }
    }//end class


        