package org.example.servletimc.DAO;

import org.example.servletimc.model.IMC;

import java.sql.*;

public class imcDAO {
    //atributos
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    //MÉTODO CONECTAR
    //<editor-fold desc="método conectar">
    public boolean conectar() {
        boolean retorno = true;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbIMC", "postgres", "livia");
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = false;
        }catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            retorno = false;
        }
        return retorno;
    }
    //</editor-fold>

    //método consultar imcs - geral
    //<editor-fold desc="consultar imcs - geral">
    public ResultSet consultarIMCS(){
        try{
            conectar();//Abrindo a conexão com o banco
            //Instanciando o objeto prepareStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM imc");

            //Executando o comando sql do objeto prepareStatement e armazenando no ResultSet
            rs = pstmt.executeQuery();
            return rs; //Retornando o ResultSet

        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            desconectar(); //Fechando a conexão com o banco
        }
    }
    //</editor-fold>

    //método consultar IMC -- com base no cpf e nome Completo
    //<editor-fold desc="consultar IMC -- com base no cpf e nome Completo">
    public ResultSet consultarFuncionarios(String nome, String cpf){
        try{
            conectar();//Abrindo a conexão com o banco

            //Instanciando o objeto prepareStatement (pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM imc WHERE nome = ? AND cpf = ?");

            //Executando o comando sql do objeto prepareStatement e armazenando no ResultSet
            pstmt.setString(1, nome);
            pstmt.setString(2, cpf);

            rs = pstmt.executeQuery();
            return rs; //Retornando o ResultSet

        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            desconectar(); //Fechando a conexão com o banco
        }
    }
    //</editor-fold>

    //método calcular novo imc
    //<editor-fold desc="calcular novo imc - cadastrar">
    public int calcularNovoIMC(String nome, String cpf, int altura, double peso){
        try{
            conectar();

            //Definindo o parâmetro com base no objeto recebido.
            pstmt = conn.prepareStatement("insert into imc(nome, cpf, altura, peso) values(?,?,?,?)");
            pstmt.setString(1,nome);
            pstmt.setString(2,cpf);
            pstmt.setInt(3, altura);
            pstmt.setDouble(4, peso);

            if(pstmt.executeUpdate() > 0) {
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            desconectar();
        }
    }
    //</editor-fold>

    //METODO REMOVER IMC
    //<editor-fold desc="remover imc">
    public int excluirIMC(String nomeP, String cpfP){
        try{
            conectar();//Abrindo a conexão com o banco

            String remover = "DELETE FROM funcionario";

            if(nomeP != null && cpfP != null){
                remover += " WHERE nome = nomeP AND cpf = cpfP";

            }else if(nomeP !=null){
                remover += " WHERE nome = nomeP";

            }else if(cpfP != null && nomeP == null){
                remover += " WHERE cpf = cpfP";

            }else{
                return -2; //vai retornar -2 se o usuário não colocar nem o nome nem o cpf
            }

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(remover);

            //Não precisa definir o parâmetro com base no objeto recebido agora, pois eu fiz antes
//            pstmt.setString(1, cpf);

            if (pstmt.executeUpdate() > 0) {
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            desconectar(); //Fechando a conexão com o banco
        }
    }
    //</editor-fold>

    //alterar o peso - que altera o imc também
    //<editor-fold desc="alterar peso">
    public int alterarPeso(String nomeP, String cpfP, double peso){
        try{
            conectar();
            IMC imcExemplo = new IMC();

            Double imcAtualizado = peso/(imcExemplo.getAltura()*imcExemplo.getAltura());
            String update = "UPDATE imc SET peso = ?, imc = ? ";

            if(nomeP != null && cpfP != null){
                update += " WHERE nome = nomeP AND cpf = cpfP";

            }else if(nomeP!=null){
                update += " WHERE nome = nomeP";
            }else if(cpfP != null && nomeP == null){
                update += " WHERE cpf = cpfP";
            }else{
                return -2; //vai retornar -2 se o usuário não colocar nem o nome nem o cpf
            }

            //Definindo o parâmetro com base no objeto recebido.
            pstmt.setDouble(1, peso);
            pstmt.setDouble(2, imcAtualizado);

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(update);

            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            desconectar();
        }
    }
    //</editor-fold>

    //alterar a altura - que altera o imc também
    //<editor-fold desc="alterar altura">
    public int alterarAltura(String nomeP, String cpfP, int alturaP){
        try{
            conectar();
            IMC imcExemplo = new IMC();

            Double imcAtualizado = imcExemplo.getPeso()/(alturaP*alturaP);

            String update = "UPDATE imc SET altura = ?, imc = ? ";

            if(nomeP != null && cpfP != null){
                update += " WHERE nome = nomeP AND cpf = cpfP";

            }else if(nomeP!=null){
                update += " WHERE nome = nomeP";
            }else if(cpfP != null && nomeP == null){
                update += " WHERE cpf = cpfP";
            }else{
                return -2; //vai retornar -2 se o usuário não colocar nem o nome nem o cpf
            }

            //Definindo o parâmetro com base no objeto recebido.
            pstmt.setInt(1, alturaP);
            pstmt.setDouble(2, imcAtualizado);

            //Instanciando o objeto preparedStatement (pstmt)
            pstmt = conn.prepareStatement(update);

            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            desconectar();
        }
    }
    //</editor-fold>

    //método desconectar
    //<editor-fold desc="método desconectar">
    public boolean desconectar(){
        boolean retorno = true;
        try{
            if(conn != null && !conn.isClosed()){
                //desconectando do bd
                conn.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            retorno = false;
        }
        return retorno;
    }
    //</editor-fold>
}
