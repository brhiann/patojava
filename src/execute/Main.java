/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package execute;

import java.util.Arrays;
import javax.swing.JOptionPane;
import model.Client;

/**
 *
 * @author GEORGE
 * GitHub: https://github.com/16george
 */
public class Main {
    
    public static void main(String[] args) {
        
        Client george = new Client("George", "Namoc", "Cajamarca", 5);
        
        //Todos los metodos se ejecutan al invocar .toString()
        
        JOptionPane.showMessageDialog(null, george.toString());
        
        
//        System.out.println(george.toString());
        
//Descomentar si se desea verificar el funcionamiento del metodo recursivo
//        Double suma12 = 0.0;
//        for (int i = 0; i < 12; i++) {
//            suma12 += (george.getPayments()[i]);
//        }
//        System.out.println(suma12); 
    }
}
