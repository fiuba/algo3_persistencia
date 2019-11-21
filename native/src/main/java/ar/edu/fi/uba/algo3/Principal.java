package ar.edu.fi.uba.algo3;

import java.io.*;


public class Principal {
    static String fileNameRelativePath = "pablo.dat";

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        /* Create File */
        {
            Persona pablo = new Persona();
            pablo.setNombre("pablo");
            pablo.setApellido("suarez");
            pablo.setDni(22000999);

            try {
                OutputStream fos = new FileOutputStream(fileNameRelativePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(pablo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Reading file
        {

            try {
                InputStream fis = new FileInputStream(fileNameRelativePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Persona otroPablo = (Persona) ois.readObject();

                System.out.print(otroPablo.getNombre());
                System.out.print(otroPablo.getApellido());
                System.out.print(otroPablo.getDni());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


}
