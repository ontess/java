import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import org.example.Complex;
import org.example.Polynomial;
import org.example.PolynomialSum;
public class PolynomialTest {
    @Test
    public void testPolynomialAddition() {
        Complex c1 = new Complex(1, 2);
        Complex c2 = new Complex(3, 4);
        Complex c3 = new Complex(5, 6);

        Polynomial p1 = new Polynomial(Arrays.asList(c1, c2));
        Polynomial p2 = new Polynomial(Arrays.asList(c3, c1));

        Polynomial sum = p1.add(p2);

        // Проверяем коэффициент для x^0: (1+5) + (2+6)i = 6 + 8i
        Assert.assertEquals(sum.getCoefficients().get(0).getReal(), 6.0, 0.001);
        Assert.assertEquals(sum.getCoefficients().get(0).getImaginary(), 8.0, 0.001);

        // Проверяем коэффициент для x^1: (3+1) + (4+2)i = 4 + 6i
        Assert.assertEquals(sum.getCoefficients().get(1).getReal(), 4.0, 0.001);
        Assert.assertEquals(sum.getCoefficients().get(1).getImaginary(), 6.0, 0.001);
    }

    @Test
    public void testSumPolynomials() {
        Complex c1 = new Complex(1, 2);
        Complex c2 = new Complex(3, 4);
        Complex c3 = new Complex(5, 6);

        Polynomial p1 = new Polynomial(Arrays.asList(c1, c2));
        Polynomial p2 = new Polynomial(Arrays.asList(c3, c1));
        Polynomial p3 = new Polynomial(Arrays.asList(c2, c3));

        List<Polynomial> polynomials = Arrays.asList(p1, p2, p3);

        Polynomial result = PolynomialSum.sumPolynomials(polynomials);

        // Ожидаемый результат:
        // x^0: (1+5+3) + (2+6+4)i = 9 + 12i
        // x^1: (3+1+4) + (4+2+6)i = 8 + 12i
        Assert.assertEquals(result.getCoefficients().get(0).getReal(), 9.0, 0.001);
        Assert.assertEquals(result.getCoefficients().get(0).getImaginary(), 12.0, 0.001);
        Assert.assertEquals(result.getCoefficients().get(1).getReal(), 9.0, 0.001);
        Assert.assertEquals(result.getCoefficients().get(1).getImaginary(), 12.0, 0.001);
    }
}