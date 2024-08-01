package App.Users;

import java.time.LocalDate;

public class User {
    private Integer uid;
    private String username;
    private String password;
    private LocalDate ngayDK;
    private String loaiTK;
    private Integer status;

    public User(Integer uid, String username, String password, LocalDate ngayDK, String loaiTK, Integer status) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.ngayDK = ngayDK;
        this.loaiTK = loaiTK;
        this.status = status;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getNgayDK() {
        return this.ngayDK;
    }

    public void setNgayDK(LocalDate ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getLoaiTK() {
        return this.loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        Integer var10000 = this.uid;
        return "User{uid='" + var10000 + "', username='" + this.username + "', password='" + this.password + "', ngayDK=" + String.valueOf(this.ngayDK) + ", loaiTK='" + this.loaiTK + "', status='" + this.status + "'}";
    }
}
