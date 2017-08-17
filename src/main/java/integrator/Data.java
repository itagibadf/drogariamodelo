
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;



  public class Data{

    private String data;
    Locale locale = new Locale("pt","BR");
    GregorianCalendar calendar = new GregorianCalendar();

     public void data(String formato){
          SimpleDateFormat formatador = new SimpleDateFormat(formato,locale);
          setData(formatador.format(calendar.getTime()));
           System.out.println(data);
      }

     /**
       * Retorna a data formatada
      * @return
      */
       public String getData() {
       return data;
    }
//* Seta a data do log
       /* @param newDataLog*/

   public void setData(String newData) {
     this.data = newData;
      }

     protected static void main(String[] args) {
        Data d=new Data();
        d.data("yyyy/MM/dd" + " " + "h:mm:ss ");
    }
}














