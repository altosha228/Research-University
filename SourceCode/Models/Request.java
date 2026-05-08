package Models;
import java.io.Serializable;
import java.util.*;

public abstract class Request implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public String message;
    public String rejection_reason;
    public Boolean is_handled;
    public Boolean is_applied;
    public Date created_at;

    public abstract void apply();
}
