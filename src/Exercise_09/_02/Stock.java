package Exercise_09._02;

public class Stock {
    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;

    public Stock() {
    }

    public Stock(String newSymbol, String newName) {
        this.symbol = newSymbol;
        this.name = newName;
    }

    public double getChangePercent() {
        return (currentPrice - previousClosingPrice) /
                previousClosingPrice;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public void setPreviousClosingPrice(double previousClosingPrice) {
        this.previousClosingPrice = previousClosingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
