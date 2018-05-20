
public class Contacts2{
    int _id;
    String wordName;
    String star;
    public Contacts2(){
    }
    public Contacts2(int id, String wordName , String star){
        this._id = id;
        this.wordName = wordName;
        this.star=star;
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
    public String getStar(){
        return this.star;
    }

    public void setStar(String star){
        this.star = star;
    }
}
