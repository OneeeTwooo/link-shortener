package by.vasileuski.linkshortener.modules.link.exception;

public class LinkShortenerException extends RuntimeException {

    public LinkShortenerException(final String message) {
        super(message);
    }

    public LinkShortenerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
