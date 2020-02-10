package xyz.ther.boot.pojo;

import xyz.ther.boot.annotation.PhoneNumber;
import xyz.ther.boot.annotation.Region;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {

    @NotNull(message = "classId 不能为空")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "(^Man$|^Woman$|^UGM$)", message = "sex 不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

    @Email(message = "email 格式不正确")
    @NotNull(message = "email 不能为空")
    private String email;

    @Region
    private String region;

    @PhoneNumber(message = "phoneNumber 格式不正确")
    @NotNull(message = "phoneNumber 不能为空")
    private String phoneNumber;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "classId='" + classId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", region='" + region + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static void testPerson(@Valid Person person) {
        System.out.println(person);
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setSex("UUU");
        testPerson(person);
    }
}
