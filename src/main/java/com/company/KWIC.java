package com.company;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
public class KWIC {
    /**
     * estructura del KWIC.
     * Necesito un conjunto para guardar las palabras no claves, estas serán:
     * el, la , los , las ..... las que aparecen en el driver, son las que
     * carecen de significado.El conjunto será de TituloKWIC, para que no distinga
     * entre eL y El
     *
     * Luego tendremos para generar las frases una estructura Map, donde por cada
     * palabra con significado, que será nuestro índice, habrá un conjunto de frases.
     * Estas frases relacionadas con el índice, serán aquellas frases en las que
     * aparezca la palabra índice.
     */
    private Set<TituloKWIC> noClaves = new TreeSet<TituloKWIC>();
    private Map<TituloKWIC,Set<String>> glosario = new TreeMap<TituloKWIC, Set<String>>();


    //Constructor
    public KWIC(){
    }
    //setnoclaves carga un nuevo conjunto de claves no significativas
    public void setNoClaves(Set<TituloKWIC> nokeys)
    {
        noClaves=nokeys;
    }
    //setglosario carga un nuevo glosario
    public void setGlosario(Map<TituloKWIC,Set<String>>glosary)
    {
        glosario=glosary;
    }
    //devuelve el glosario
    public Map<TituloKWIC,Set<String >> getGlosario()
    {
        return glosario;
    }
    //devuelve las claves no significativas
    public Set<TituloKWIC>getNoClaves()
    {
        return noClaves;
    }
    /**
     *
     * @param pns entra como parámetro String de palabras no significativas
     *
     * En este método meteré en un conjunto todas las palabras no significativas.
     *
     */
    public void palabrasNoSignificativas(String pns){
        //utilizo el Tokenizer para coger cada palabra no significativa
        // el funcionamiento del metodo es el siguiente. la clase StringTokenizer toma como
        //parametro un string y lo descompone en elementos o tokens, indicando en el constructor cual va
        // a ser el/los caracteres que funcionen como delimitadores de la lista de tokens que se va a
        //crear.
        StringTokenizer st = new StringTokenizer(pns," ,:;");
        //con un bucle while recorremos la lista de tokens creada y creamos un objeto TituloKWIC por cada token,
        // y lo añadimos treeset noClaves
        //
        while (st.hasMoreTokens()){
            try {
                noClaves.add(new TituloKWIC(st.nextToken()));
            }
            catch(KWICException error)
            {
                System.out.println("Error al crear el token y añadirlo a la lista de claves no significativas");
            }
        }

    }

    public void generarFrases(String frase){
        //utilizo Tokenizer para coger las palabras significativas que me valdrán
        //como índice para mi estructura Map
        // Empleamos de nuevo un objeto de la clase StringTokenizer para generar una lista de tokens,
        //esta vez separados por un espacio en blanco
        StringTokenizer st = new StringTokenizer(frase);
        //una vez creada la lista de tokens la recorremos con un bucle while y creamos un objeto TituloKWIC
        //por cada token.
        while (st.hasMoreTokens()){
            try {
                TituloKWIC palabra = (new TituloKWIC(st.nextToken()));
                //si no es una palabra no significativa, significa que voy bien,
                //lo que tengo es una palabra significativa

                if (!noClaves.contains(palabra)) {
                    //me voy a un método privado que llamaré frases, al que le
                    //envio como parámetros el indice y la frase
                    frases(palabra, frase);
                }
            }catch (KWICException error)
            {
                System.out.println("Error detectado al crear el token asociado a un termino significativo");
            }

        }
    }
    /**
     *
     * @param indice --> palabra clave de la estructura Map
     * @param frase --> frase a introducir en el conjunto relacionado con el índice o
     *                  clave.
     *
     * Este método privado añade la frase al conjunto, conjunto relacionado con el
     * índice que le paso como parámetro
     */
    private void frases(TituloKWIC indice,String frase){
        //me creo un conjunto para el caso que ese indice no esté en mi estructura
        Set<String> s = new TreeSet<String>();
        //si mi estructura Map contiene ese indice
        if (glosario.containsKey(indice)){
            //devuelveme el conjunto de frases de ese indice
            s = glosario.get(indice);
        }
        //como tanto si está el indice en la estructura como si no está tengo que
        //añadir la frase al conjunto y luego relacionar ese indice con mi conjunto
        //s actualizado, lo saco del if, y no me hace falta poner un else
        s.add(indice.replace(frase));
        //OJO --> añado replace para que me sustituya en la frase
        //        el indice por ...
        //el método está definido en TituloKWIC

        //añado al glosario el indice y el conjunto de frases asociadas al mismo
        glosario.put(indice,s);

    }



    //----------------------------------------------------------------------------
    /**
     utilizaremos dos métodos privados:
     imprimirNoClaves() --> para imprimir por pantalla el conjunto
     de claves.
     imprimirGlosario() --> para imprimir por pantalla la estructura Map,
     clave (índice) y valor (conjunto de frases relacionadas
     y con el índice sustituido por ...)
     */
    public String toString(){
        String cadFinal = "";

        cadFinal += imprimirNoClaves();
        cadFinal += imprimirGlosario();
        return cadFinal;
    }

    /**
     *
     * @return una cadena con las palabras no significativas a imprimir
     *
     * método privado que me imprime el conjunto de las palabras no significativas.
     * Este método será utilizado por el toString()
     */
    private String imprimirNoClaves(){
        String cadNoClaves = "N O    C L A V E S: "; //inicializo la cadena
//        utilizo el iterador de conjuntos para recorrerlo e imprimirlo

        for (TituloKWIC noClave : this.noClaves) {//mientras halla elementos en el conjunto
            cadNoClaves = cadNoClaves.concat(noClave.getTitulo() + ", ");//imprime elementos
        }
        return cadNoClaves;
    }

    /**
     * método privado que me imprime la estructura Map<Indice,conjunto de frases>.
     Este método será utilizado por el toString()
     */
    private String imprimirGlosario(){
        String cadGlosario = "\n"+"\n"+"         G L O S A R I O    "+"\n";
        //como el Map no tiene iterator tengo que cogerlo de Map.Entry
        for (Map.Entry<TituloKWIC, Set<String>> me : this.glosario.entrySet()) {//mientras halla elementos en mi estructura
            cadGlosario = cadGlosario.concat("\n" + me.getKey() + "\n" + "\n");//meto el indice
            //hago un método privado para recorrer el conjunto para ese índice
            cadGlosario += imprimirGlosario(me.getValue());
        }
        return cadGlosario;
    }

    /**
     *
     * @param s --> conjunto de cadenas, es el valor de mi estructura Map
     * @return  una cadena con los elementos del conjunto para imprimir
     *
     * método que me imprime el conjunto de frases. Este método será utilizado por
    el método imprimirGlosario()
     */

    private String imprimirGlosario(Set<String> s){
        //hago lo mismo que hice con el conjunto formado por palabras no
        //significativas
        String cjFrases = "";//inicializo
        //creo el iterador
        //mientras halla elementos en mi conjunto
        for (String value : s) {
            //imprimo los elementos
            cjFrases = cjFrases.concat("\t" + value + "\n");
        }
        return cjFrases;
    }
}