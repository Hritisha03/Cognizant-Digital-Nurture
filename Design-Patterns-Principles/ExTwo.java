interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Word Document Created");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("PDF Document Created");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Excel Document Created");
    }
}

abstract class DocumentFactory {
    abstract Document createDocument();
}

class WordFactory extends DocumentFactory {
    Document createDocument() {
        return new WordDocument();
    }
}

class PdfFactory extends DocumentFactory {
    Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelFactory extends DocumentFactory {
    Document createDocument() {
        return new ExcelDocument();
    }
}

public class ExTwo {

    public static void main(String[] args) {

        DocumentFactory factory;

        factory = new WordFactory();
        factory.createDocument().open();

        factory = new PdfFactory();
        factory.createDocument().open();

        factory = new ExcelFactory();
        factory.createDocument().open();
    }
}