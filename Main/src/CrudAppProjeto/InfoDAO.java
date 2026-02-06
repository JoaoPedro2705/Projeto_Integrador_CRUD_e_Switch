
package CrudAppProjeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;



public class InfoDAO {
    private Connection connection;
    
    public  InfoDAO() throws SQLException{
      connection = DBConnection.getConnection();
    }
    
    public int inserirInfo(Info info){
        try{
            String sql = "insert into infos (senhas,cpf,emails,cartaoSus,numeroDoCartao) values (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, info.getSenhas());
            stmt.setString(2, info.getCpf());
            stmt.setString(3, info.getEmails());
            stmt.setString(4, info.getCartaoSus());
            stmt.setString(5, info.getNumeroDoCartao());
            stmt.executeUpdate();
            return 1;
        }catch(SQLException e){
            return 0;
        }
               
    }
    
   public List<Info> getInfo(){
      List<Info> infos = new ArrayList<>();
      
      
      try{
          String sql = "select * from infos";
          PreparedStatement stmt = connection.prepareStatement(sql);
          
          ResultSet rs = stmt.executeQuery();
          while(rs.next()){
              int id = rs.getInt("id");
              String senhas = rs.getString("senhas");
              String cpf = rs.getString("cpf");
              String emails = rs.getString("emails");
              String cartaoSus = rs.getString("cartaoSus");
              String numeroDoCartao = rs.getString("numeroDoCartao");
              
              infos.add(new Info(id,senhas,cpf,emails,cartaoSus,numeroDoCartao));
              
              
          } 
      }catch(SQLException e){
          e.printStackTrace();
      }
      return infos;
  }
  
    public int updateInfo(Info info){
        try{
            String sql = "update infos set cpf=?, emails=?, cartaoSus=?, numeroDoCartao=? where id=?";
             PreparedStatement stmt = connection.prepareStatement(sql);
             stmt.setString(1, info.getSenhas());
             stmt.setString(2, info.getCpf());
             stmt.setString(3, info.getEmails());
             stmt.setString(4, info.getCartaoSus());
             stmt.setString(5, info.getNumeroDoCartao());
             stmt.setInt(6, info.getId());
             stmt.executeUpdate();
             return 1;
        }catch(SQLException e){
            return 0;
        }
    }
    
    public void deleteInfo(int id){
        try{
            String sql = "delete from infos where id=?";
             PreparedStatement stmt = connection.prepareStatement(sql);
             stmt.setInt(1, id);
             stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
    
    
     