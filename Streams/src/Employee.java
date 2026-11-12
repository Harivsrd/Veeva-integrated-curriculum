class Employee {
    String name;
    int age;
    String dept;

    public Employee(String name, int age, String dept) {
        this.name = name;
        this.age = age;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, dept='%s'}", name, age, dept);
    }
}

