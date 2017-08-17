    package integrator;

  
import Model.Conexao;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
    import java.util.List;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    public class upload extends HttpServlet {

        private static List parseRequest(HttpServletRequest request) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {

              String codigo = request.getParameter("id");

              String nomeParametro = "file";
              String id = request.getParameter(nomeParametro);

                out.println("O código da página é: "+codigo); // imprime id nula

         File dir = new File(getServletContext().getRealPath("/")+ "eventos");

         String contentType = request.getContentType();

         if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {

                  DataInputStream in = new DataInputStream(request.getInputStream());

                     int formDataLength = request.getContentLength();
                     byte dataBytes[] = new byte[formDataLength];
                     int byteRead = 0;
                     int totalBytesRead = 0;

                   while (totalBytesRead < formDataLength) {

                       byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                          totalBytesRead += byteRead;
                     }

                   String file = new String(dataBytes);
                   String saveFile = file.substring(file.indexOf("filename=\"") + 10);
                     saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
                     saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));

                     int lastIndex = contentType.lastIndexOf("=");
                     String boundary = contentType.substring(lastIndex + 1,contentType.length());

                     int pos;

                  pos = file.indexOf("filename=\"");
                  pos = file.indexOf("\n", pos) + 1;
                  pos = file.indexOf("\n", pos) + 1;
                  pos = file.indexOf("\n", pos) + 1;

                    int boundaryLocation = file.indexOf(boundary, pos) - 4;
                    int startPos = ((file.substring(0, pos)).getBytes()).length;
                    int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;

                  String filepath = dir+ "/" +saveFile;

                  FileOutputStream fileOut = new FileOutputStream(filepath);
                     fileOut.write(dataBytes, startPos, (endPos - startPos));
                     fileOut.flush();
                     fileOut.close();

            try {
                   Connection conn = Conexao.getConection();

                  // gravando no banco de dados
                   PreparedStatement pstmt =
                   conn.prepareStatement("INSERT INTO images_001 (url_image) values ( ? )  WHERE codigoTopic = ? ");

                   pstmt.setObject(1, saveFile);
                   pstmt.setObject(2, id);
                   pstmt.execute();

                   out.println("<h1>A imagem do upload foi: " + saveFile+"</h1>");  // não imprime na tela
                   out.println("<img src=\"eventos/" +saveFile+ "\" width=\"300\" height=\"300\" />"); // não imprime na tela
                   out.println("Arquivo enviado com sucesso"); // não imprime na tela

                   } catch (SQLException sqlex) {
                      out.println("Não foi possível selecionar o nome do diretórios<br>" + sqlex);
                      sqlex.printStackTrace();
                   }    
              }
            } finally {
                out.close();
            }
        }

        // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">
        /**
        * Handles the HTTP <code>GET</code> method.
        * @param request servlet request
        * @param response servlet response
        */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
        * Handles the HTTP <code>POST</code> method.
        * @param request servlet request
        * @param response servlet response
        */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
        * Returns a short description of the servlet.
        */
        @Override
        public String getServletInfo() {
            return "Short description";
        }
        // </editor-fold>
    }