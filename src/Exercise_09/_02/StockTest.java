package Exercise_09._02;

public class StockTest {
    public static void main(String[] args) {
        Stock stock = new Stock( "ORCL","Oracle Corporation" );
        stock.setPreviousClosingPrice( 34.5 );

        // Set current price
        stock.setCurrentPrice( 34.35 );

        // Display stock info
        System.out.println("Previous Closing Price: " +
                stock.getPreviousClosingPrice());
        System.out.println("Current Price: " +
                stock.getCurrentPrice());
        System.out.println("Price Change: " +
                stock.getChangePercent() * 100 + "%");
    }
}
