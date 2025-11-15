// Importa as classes necessárias do Java para lidar com banco de dados (SQL).
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.Statement; 

// Declaração da classe pública chamada 'User'.
public class User {
    
    /**
     * Método público que tenta estabelecer uma conexão com o banco de dados.
     * @return Um objeto Connection (a conexão) ou null se a conexão falhar.
     */
    public Connection conectarBD(){
        // Inicializa a variável de conexão 'conn' como nula.
        Connection conn = null;
        try {
            // Tenta carregar e instanciar o driver JDBC do MySQL.
            Class.forName("com.mysql.jdbc.Driver.Manager").newInstance();
            
            // Define a string de conexão (URL) para o banco de dados local "test".
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            
            // Tenta de fato estabelecer a conexão usando o driver e a URL.
            conn = DriverManager.getConnection(url);
            
        } catch (Exception e) { 
            // Se qualquer erro ocorrer , a exceção é capturada, mas nada é feito. O erro é "engolido".
        }
        // Retorna a conexão. Se o 'try' falhou, 'conn' ainda será 'null'.
        return conn;
    }
    
    // Declara uma variável pública para guardar o nome do usuário.
    public String nome="";
    
    // Declara uma variável pública para guardar o resultado da verificação.
    public boolean result = false;
    
    /**
     * Método público que verifica se um login e senha existem no banco de dados.
     * @param login O login do usuário a ser verificado.
     * @param senha A senha do usuário a ser verificada.
     * @return true se o usuário for encontrado, false caso contrário .
     */
    public boolean verificarUsuario(String login, String senha) {
        // Inicializa uma string vazia para montar a consulta SQL.
        String sql = "";
        
        // Chama o método 'conectarBD' para obter uma conexão.
        Connection conn = conectarBD();
        
        //INSTRUÇÃO SQL 
        
        // Constrói a string SQL concatenando os valores de 'login' e 'senha' diretamente na consulta.
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        
        try {
            // Cria um objeto Statement para executar a SQL.
            Statement st = conn.createStatement();
            
            // armazena os resultados em 'rs'.
            ResultSet rs = st.executeQuery(sql);
            
            // Verifica se o 'ResultSet' (rs) tem pelo menos um próximo registro.
            if (rs.next()) {
                // Se encontrou, muda a variável de instância 'result' para true.
                result = true;
                // Armazena o nome na variável de instância 'nome'.
                nome = rs.getString("nome");
            }
        } catch (Exception e) { 
            // Se qualquer erro ocorrer , a exceção é capturada, mas nada é feito. O erro é "engolido".
        }
        
        // Retorna o valor da variável de instância 'result'.
        return result;
    }
}//fim da class 
