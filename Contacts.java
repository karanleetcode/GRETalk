
/**
 * Created by Acer on 4/28/2018.
 */

public class Contacts {
    int _id;
    String wordName;
    String mean,sentence;
    String star,voice;
    public Contacts(){

    }
    public Contacts(int id, String wordName , String mean,String sentence,String star,String voice){
        this._id = id;
        this.wordName = wordName;
        this.mean=mean;
        this.sentence = sentence;
        this.star = star;
        this.voice  = voice;
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getWordName(){
        return this.wordName;
    }
    public void setWordName(String wordName){
        this.wordName = wordName;
    }

    public String getMean(){
        return this.mean;
    }
    public void setMean(String mean){
        this.mean = mean;
    }

    public String getSentence(){
        return this.sentence;
    }
    public void setSentence(String sentence){
        this.sentence = sentence;
    }

    public String getStar(){
        return this.star;
    }
    public void setStar(String star){
        this.star = star;
    }

    public String getVoice(){
        return this.voice;
    }
    public void setVoice(String voice){
        this.voice = voice;
    }
}
