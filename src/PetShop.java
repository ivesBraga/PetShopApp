/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ivesm
 */
import java.sql.*;
import java.util.ArrayList;

public class PetShop {
    private int id;
    private String nomeAnimal;
    private String nomeDono;
    private String especie;
    private String endereco;
    private String alergia;
    private double peso;
    private int idade;
    private String observacao;

    public PetShop(int id, String nomeDono, String nomeAnimal, String especie, String endereco, String alergia, double peso, int idade, String observacao) {
        this.id = id;
        this.nomeAnimal = nomeAnimal;
        this.nomeDono = nomeDono;
        this.especie = especie;
        this.peso = peso;
        this.endereco = endereco;
        this.idade = idade;
        this.alergia = alergia;
        this.observacao = observacao;
    }
    
    public static PetShop buscarPetPorId(int id) {
    PetShop pet = null;
    try {
        // Conexão com o banco de dados
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "SUA_SENHA");

        // Consulta SQL para buscar o pet pelo ID
        String query = "SELECT * FROM pets WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, id);

        // Executa a consulta
        ResultSet rs = stmt.executeQuery();

        // Verifica se encontrou o registro
        if (rs.next()) {
            pet = new PetShop(
                    rs.getInt("idade"), rs.getString("nomeDono"),
                rs.getString("nomeAnimal"),
                rs.getString("especie"),
                rs.getString("endereco"),
                rs.getString("alergia"),
                rs.getDouble("peso"),
                rs.getInt("idade"),
                rs.getString("observacao")
            );
            pet.id = rs.getInt("id"); // Define o ID do objeto
        }

        con.close(); // Fecha a conexão
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pet; // Retorna o pet encontrado ou null se não encontrado
}
    
    public void atualizarNoBanco(int id) {
    try {
        // Conexão com o banco de dados
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "SUA_SENHA");

        // Comando SQL para atualizar os dados do pet
        String query = "UPDATE pets SET nomeDono = ?, nomeAnimal = ?, especie = ?, endereco = ?, alergia = ?, peso = ?, idade = ?, observacao = ? WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(query);

        // Define os valores nos placeholders
        stmt.setString(1, nomeDono);
        stmt.setString(2, nomeAnimal);
        stmt.setString(3, especie);
        stmt.setString(4, endereco);
        stmt.setString(5, alergia);
        stmt.setDouble(6, peso);
        stmt.setInt(7, idade);
        stmt.setString(8, observacao);
        stmt.setInt(9, id);

        // Executa o comando
        stmt.executeUpdate();

        con.close(); // Fecha a conexão
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public static void deletarNoBanco(int id) {
    try {
        // Conexão com o banco de dados
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "SUA_SENHA");

        // Comando SQL para excluir o registro
        String query = "DELETE FROM pets WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(query);

        // Define o ID no placeholder
        stmt.setInt(1, id);

        // Executa o comando
        stmt.executeUpdate();

        con.close(); // Fecha a conexão
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




    // Método para salvar o pet no banco de dados
    public void salvarNoBanco() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "SUA_SENHA");
            String query = "INSERT INTO pets (nomeDono, nomeAnimal, especie, endereco, alergia, peso, idade, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, nomeDono);
            stmt.setString(2, nomeAnimal);
            stmt.setString(3, especie);
            stmt.setString(4, endereco);
            stmt.setString(5, alergia);
            stmt.setDouble(6, peso);
            stmt.setInt(7, idade);
            stmt.setString(8, observacao);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar todos os pets no banco de dados
    public static ArrayList<PetShop> buscarTodosPets() {
    ArrayList<PetShop> pets = new ArrayList<>();
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "SUA_SENHA");
        String query = "SELECT * FROM pets";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            PetShop pet = new PetShop(
                rs.getInt("id"),
                rs.getString("nomeDono"),
                rs.getString("nomeAnimal"),
                rs.getString("especie"),
                rs.getString("endereco"),
                rs.getString("alergia"),
                rs.getDouble("peso"),
                rs.getInt("idade"),
                rs.getString("observacao")
            );
            pets.add(pet);
        }

        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pets;
}

    public int getId() {
        return id;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public String getEspecie() {
        return especie;
    }

    public double getPeso() {
        return peso;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getAlergia() {
        return alergia;
    }

    public String getObservacao() {
        return observacao;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}


