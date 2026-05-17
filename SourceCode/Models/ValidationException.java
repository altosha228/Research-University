package Models;
import java.io.*;
import java.util.*;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
