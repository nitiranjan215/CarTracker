package io.egen.entity;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by nitir on 6/28/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Alert.findAll",
                query = "SELECT alert FROM Alert alert ORDER BY alert.vin DESC"),
        @NamedQuery(name = "Alert.findByVin",
                query = "SELECT alert FROM Alert alert WHERE alert.vin=:paramVin")
})
public class Alert {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    private String priority;
    private String Message;
    private String vin;
    public Alert(){
        this.id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
