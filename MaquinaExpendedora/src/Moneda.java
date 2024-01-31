public enum Moneda {
    VenticincoCTV(0.25),CincuentaCTV(0.50),DOLAR(1.00);

    private final double valor;

    Moneda(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
