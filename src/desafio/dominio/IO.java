package desafio.dominio;

import java.io.*;
import java.util.List;

public class IO {
    public static void salvarConteudo(List<?> listas, String arquivo) throws IOException {
        File f = new File(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f.getName()));
        oos.writeObject(listas);
        oos.close();
    }

    public static List<?> lerConteudo(String arquivo) throws IOException, ClassNotFoundException {
        File f = new File(arquivo);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f.getName()));
        List<?> l = (List<?>) ois.readObject();
        ois.close();
        return l;
    }
}
