public  class SportsCamp {
    private  String name; //Имя спортсмена
    private  Integer day; //Количество дней в спортивном лагере

    public SportsCamp(String name, int day) {

        this.name = name;
        this.day = day;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDay() {
        return day;
    }
    public void setDay(Integer day) {
        this.day = day;
    }
}