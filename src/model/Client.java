/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author GEORGE GitHub: https://github.com/16george
 */
public class Client {

    private String firstName;
    private String lastName;
    private String address;
    private int years;
    private Double[] payments;

    public Client(String firstName, String lastName, String address, int years) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.years = years;
        this.payments = generate(40.0, 220.80, 20);
    }

    private Double[] generate(Double from, Double to, int size) {
        Double[] data = new Double[size];
        for (int i = 0; i < size; i++) {
            data[i] = (Math.random() * (to - from + 1) + from);
        }
        return data;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public Double[] getPayments() {
        return payments;
    }

    public void setPayments(Double[] payments) {
        this.payments = payments;
    }

    public String report() {
        Double first = payments[0];
        Double last = payments[payments.length - 1];
        Double difference = Math.abs(first - last);

        return (difference > 45.0) ? "Revisión prioritaria" : ((difference > 30.0) ? "Visita técnica" : "Omitir anuncio");
    }

    public Double discount() {
        Double dsc = 0.0;
        if (Math.toIntExact(Arrays.asList(payments).stream().filter((payment -> payment > 202.12)).count()) > 15) {
            dsc = payments[payments.length - 1] * 0.20;
            payments[payments.length - 1] = payments[payments.length - 1] - dsc;
        }
        return dsc;
    }

    public Double bonus(String report) {
        if (!report.equals("Revisión prioritaria") && !report.equals("Visita técnica")) {
            return 100.0;
        } else if (averagePayments() < 125.0 && report.equals("Visita técnica")) {
            return 75.0;
        }
        return 0.0;
    }

    public String alternativeReport(String report) {
        return (years > 10 && report.equals("Revisión prioritaria")) ? "Cancelar servicio" : ((report.equals("Visita técnica") || report.equals("Omitir anuncio")) ? "Suspender servicio" : "Renovar servicio");
    }

    private Double averagePayments() {
        return Arrays.asList(payments).stream().mapToDouble((payment -> payment)).average().getAsDouble();
    }

    private Double averageNoMin() {
        Double min = Arrays.asList(payments).stream().min(Comparator.naturalOrder()).get();
        return Arrays.asList(payments).stream().filter((payment -> payment != min)).mapToDouble((payment -> payment)).average().getAsDouble();
    }

    public Double forecast(Double average) {
        return (average > 200.15) ? (average * 0.85) : ((average > 160.80) ? (average * 0.9) : ((average > 100.30) ? (average * 0.98) : (average * 0.99)));
    }

    //Suma recursiva de los 12 primeros pagos
    public Double amount(Double payments[], int index) {
        return (index < 12) ? (payments[index] + amount(payments, index + 1)) : 0;
    }

    public String getDiscount() {
        return (discount() != 0) ? "Se aplico un descuento del 20% en su ultimo pago: S/." + discount() : "No se aplico descuento";
    }

    @Override
    public String toString() {
        return "Nombres: " + getFirstName() + "\n"
                + "Apellidos: " + getLastName() + "\n"
                + "Direccion: " + getAddress() + "\n"
                + "N° Años: " + getYears() + "\n"
                + "Pagos: \n " + Arrays.toString(payments) + "\n \n"
                + "Promedio de pagos: S/." + String.valueOf(averagePayments()) + "\n \n"
                + "Reporte: " + report() + "\n"
                + "Descuento: " + getDiscount() + "\n"
                + "Bono: S/." + String.valueOf(bonus(report())) + "\n"
                + "Reporte Alternativo: " + alternativeReport(report()) + "\n"
                + "Pronostico del Siguiente pago: \n" + "\t \t Promedio de pagos excepto el menor: S/. " + String.valueOf(averageNoMin()) + "\n" + "\t \t Pronostico: S/." + forecast(averageNoMin()) + "\n"
                + "Suma de los 12 primeros pagos: S/." + amount(payments, 0);

    }
}
