package com.example.pramo.patients;

/**
 * Created by pramo on 4/22/2017.
 */

public class Test {
    public String date;
    public String choice1,choice2,choice3,choice4,audio_url,ans;
    public Test(String date) {
        this.date = date;
    }

    public Test(String date, String choice1, String choice2, String choice3, String choice4,String audio_url,String ans) {
        this.date = date;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.ans=ans;
        this.audio_url=audio_url;
    }

    public Test(){}

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate(){
         return date;
    }


    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }


    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
