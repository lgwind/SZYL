package com.szyl.entity;

/**
 * 简历信息实体类
 * @author chennaile
 *
 */
public class Resume {
    
    private String resume_id;
    private String username;
    private String name;
    private String phone;
    private String email;
    private String school;
    private String major;
    private String position;
    private int age;
    private String state;
    private int work_age;
    private String skill;
    private String eml;
    private String update_time; 
    
    public Resume() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Resume(String resume_id, String username, String name, String phone,
            String email, String school, String major, String position,
            int age, String state, int work_age, String skill, String eml,
            String update_time) {
        super();
        this.resume_id = resume_id;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.school = school;
        this.major = major;
        this.position = position;
        this.age = age;
        this.state = state;
        this.work_age = work_age;
        this.skill = skill;
        this.eml = eml;
        this.update_time = update_time;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getWork_age() {
        return work_age;
    }

    public void setWork_age(int work_age) {
        this.work_age = work_age;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEml() {
        return eml;
    }

    public void setEml(String eml) {
        this.eml = eml;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
    
    @Override
    public String toString() {
        return "Resume [resume_id=" + resume_id + ", username=" + username
                + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", school=" + school + ", major=" + major + ", position="
                + position + ", age=" + age + ", state=" + state
                + ", work_age=" + work_age + ", skill=" + skill + ", eml="
                + eml + ", update_time=" + update_time + "]";
    }
    
}
