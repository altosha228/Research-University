package Models;
import java.io.Serializable;
import java.util.*;

public abstract class Request implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public String short_desc;
    public String message;
    public String rejection_reason;
    public Boolean is_handled;
    public Boolean is_applied;
    public Date created_at;
    public Request(String short_desc, String message) {
        this.short_desc = short_desc;
        this.message = message;
        this.created_at = new Date();
        this.is_handled = false;
        this.is_applied = false;
    }

    public abstract void apply();
}
