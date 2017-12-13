package com.example.vindh.myapplication;



public class contacts {

    public contacts(String pressure,String time,String temp,String humid){
        this.setPressure(pressure);

        this.settemp(temp);
        this.sethumid(humid);
        this.setTime(time);
    }


    private String pressure,time,temp,humid;

    public String getPressure(){
        return pressure;
    }

    public String gettime(){
        return time;
    }
    public String gettemp(){
        return temp;
    }
    public String gethumid(){
      
        return humid;
    }

    public void setPressure(String pressure){
        this.pressure=pressure;
    }
    public void settemp(String temp){
        this.temp=temp;
    }
    public void sethumid(String humid){
        this.humid=humid;
}

    public void setTime(String time){
        this.time=time;
    }
}
