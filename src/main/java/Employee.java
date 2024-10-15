import org.json.simple.JSONObject;

public class Employee {
    private String name;
    private String designation;
    private String phone;

    public Employee(String name, String designation, String phone) {
        this.name = name;
        this.designation = designation;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("designation", this.designation);
        jsonObject.put("phone", this.phone);
        return jsonObject;
    }
}
