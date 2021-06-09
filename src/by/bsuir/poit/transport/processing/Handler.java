package by.bsuir.poit.transport.processing;

public interface Handler {

    String preprocess(String data);

    String postprocess(String data);
}
