import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> myProducts = new ArrayList<>();
        Product a = new Product("A", Math.random() * 100);
        myProducts.add(a);
        Product b = new Product("B", Math.random() * 100);
        myProducts.add(b);
        Product c = new Product("C", Math.random() * 100);
        myProducts.add(c);
        Product d = new Product("D", Math.random() * 100);
        myProducts.add(d);
        Product e = new Product("E", Math.random() * 100);
        myProducts.add(e);
        Product pdummy = new Product("PDummy", 1000);
        myProducts.add(1, pdummy);

        // sorting myProducts using Collections's sort
        Collections.sort(myProducts);

        // printing all products from myProducts arraylist
        myProducts.forEach((p) -> System.out.println(p));
    }
}
