package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class InfoDAO {

    private Connection connection;

    public InfoDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public int inserirCpf(String cpf) {
        String sql = "INSERT INTO infos (cpf) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
          
        }
    }
    
     public int inserirCardSus(String cardSus) {
        String sql = "INSERT INTO infos (cartaoSus) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cardSus);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
     }
     
         public int inserirCartao(String cartao) {
        String sql = "INSERT INTO infos (numeroDoCartao) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cartao);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
          
        }
    }
         
         
         public int inserirEmail(String email) {
        String sql = "INSERT INTO infos (emails) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
          
        }
    }
         
          public int inserirSenha(String senha) {
        String sql = "INSERT INTO infos (senhas) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, senha);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
          
        }
    }
          
          public List<String> listarSenhas() {
    List<String> lista = new ArrayList<>();

    try {
        String sql = "SELECT senhas FROM infos";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(rs.getString("senhas"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
          
           public List<String> listarEmail() {
    List<String> listEmails = new ArrayList<>();

    try {
        String sql = "SELECT emails FROM infos";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            listEmails.add(rs.getString("emails"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listEmails;
}
           
           public List<String> listarCardSus() {
    List<String> listaCardSus = new ArrayList<>();

    try {
        String sql = "SELECT cartaoSus FROM infos";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            listaCardSus.add(rs.getString("cartaoSus"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaCardSus;
}
           
                
           public List<String> listarCartao() {
    List<String> listaCartao = new ArrayList<>();

    try {
        String sql = "SELECT numeroDoCartao FROM infos";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            listaCartao.add(rs.getString("numeroDoCartao"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaCartao;
}

           
           public List<String> listarCpf() {
    List<String> listaCpf = new ArrayList<>();

    try {
        String sql = "SELECT cpf FROM infos";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            listaCpf.add(rs.getString("cpf"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaCpf;
}
           
           

    // READ: Retorna todos os registros (Caso precise para uma JTable no futuro)
    public List<Info> getInfo() {
        List<Info> infos = new ArrayList<>();
        String sql = "SELECT * FROM infos";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                infos.add(new Info(
                        rs.getInt("id"),
                        rs.getString("senhas"),
                        rs.getString("cpf"),
                        rs.getString("emails"),
                        rs.getString("cartaoSus"),
                        rs.getString("numeroDoCartao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infos;
    }

    // UPDATE: Atualiza os dados de um registro existente baseado no ID
    public int updateInfo(Info info) {
        // Corrigido: Adicionado 'senhas' na query e ajustado as posições
        String sql = "UPDATE infos SET senhas=?, cpf=?, emails=?, cartaoSus=?, numeroDoCartao=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, info.getSenhas());
            stmt.setString(2, info.getCpf());
            stmt.setString(3, info.getEmails());
            stmt.setString(4, info.getCartaoSus());
            stmt.setString(5, info.getNumeroDoCartao());
            stmt.setInt(6, info.getId()); // O ID deve ser o último parâmetro do WHERE

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // READ: Busca apenas o último registro inserido (Perfeito para o seu "Bloco de Notas")
    public Info buscarUltimo() {
        String sql = "SELECT * FROM infos ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                // Ordem rigorosa do seu construtor: id, senhas, cpf, emails, cartaoSus, numeroDoCartao
                return new Info(
                        rs.getInt("id"),
                        rs.getString("senhas"),
                        rs.getString("cpf"),
                        rs.getString("emails"),
                        rs.getString("cartaoSus"),
                        rs.getString("numeroDoCartao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ultimo: " + e.getMessage());
        }
        return null; // Retorna null se a tabela estiver vazia
    }

    // DELETE: Apaga um registro pelo ID
    public void deleteInfo(int id) {
        String sql = "DELETE FROM infos WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
