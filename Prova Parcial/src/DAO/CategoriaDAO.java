package DAO;

import bin.Categoria;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO {
    
    public void insert(Categoria c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO category(name,description) VALUES (?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getDescription());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar a categoria:" + e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        
    }
    
    public Iterator<Categoria> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Categoria> categorias = new ArrayList();
        
        try {
            String sql = "SELECT * FROM category";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Categoria categoria = new Categoria();
                
                categoria.setId(rs.getInt("id"));
                categoria.setName(rs.getString("name"));
                categoria.setDescription(rs.getString("description"));
                categorias.add(categoria);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Whoops!! Erro ao listar no banco de dados...", e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        Iterator<Categoria> it_categorias = categorias.iterator();
        return it_categorias;
    }
    
    public void update(Categoria c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE category SET name = ?,description = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getDescription());
            stmt.setInt(3, c.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a categoria:" + e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        
    }
    
    public void delete(Categoria c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM category WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria excluida com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a categoria:" + e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        
    }
    
    
    public Categoria retornaCategoria(String category) {
        Categoria categoria = new Categoria();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        
        try {
            String sql = "SELECT * FROM category WHERE name = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, category);
            rs = stmt.executeQuery();
            
            categoria.setId(rs.getInt("id"));
            categoria.setName(rs.getString("name"));
            categoria.setDescription(rs.getString("description"));
            
        } catch(SQLException e) {
            throw new RuntimeException("Whoops!! Erro ao listar no banco de dados...", e);
        } 
        
        return categoria;
    }
}
