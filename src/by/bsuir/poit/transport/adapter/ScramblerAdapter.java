package by.bsuir.poit.transport.adapter;

import by.bsuir.poit.transport.processing.Handler;
import org.jasypt.util.text.StrongTextEncryptor;

public class ScramblerAdapter implements Handler {
    private final StrongTextEncryptor textEncryptor;

    public ScramblerAdapter(String password) {
        textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(password);
    }

    @Override
    public String preprocess(String data) {
        return textEncryptor.encrypt(data);
    }

    @Override
    public String postprocess(String data) {
        return textEncryptor.decrypt(data);
    }
}
