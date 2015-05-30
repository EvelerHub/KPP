package Lab6.Ex2;

public class AddMetroCardOperation extends CardOperation {
    private MetroCard crd = null;

    public AddMetroCardOperation() {
        crd = new MetroCard();
    }

    public MetroCard getCrd() {
        return crd;
    }
}
