package hr.system.entities;

import javax.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Salary {
    private double gross;
    private double net;
    private double insuranceAmount;

    public Salary() {
    }

    public Salary(long gross) {
        this.gross = gross;
        this.insuranceAmount = 500;
        this.net = 0.85 * gross - insuranceAmount;
    }

    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salary salary)) return false;
        return Double.compare(salary.getGross(), getGross()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGross(), getNet(), getInsuranceAmount());
    }

    @Override
    public String toString() {
        return "Salary{" +
                "gross=" + gross +
                ", net=" + net +
                ", insuranceAmount=" + insuranceAmount +
                '}';
    }
}
