package DAO;


import bin.Produto;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    public void insert(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql = "INSERT INTO product(name, description, quantity, price, category) VALUES(?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setInt(3, p.getQuantity());
            stmt.setDouble(4, p.getPrice());
            stmt.setInt(5, p.getCategoryId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!!" + e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
    
    public Iterator<Produto> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList();
        
        try {
            String sql = "SELECT pr.id, pr.name, pr.description, pr.quantity, pr.price, cat.id, cat.name AS category_name"
                    + " FROM product AS pr INNER JOIN category AS cat ON pr.category = cat.id";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setName(rs.getString("name"));
                produto.setDescription(rs.getString("description"));
                produto.setQuantity(rs.getInt("quantity"));
                produto.setPrice(rs.getDouble("price"));
                produto.setCategory(rs.getString("category_name"));
                produtos.add(produto);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produto!!" + e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        Iterator<Produto> it_produtos = produtos.iterator();
        return it_produtos;
    }
    
    public void update(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql = "UPDATE product SET name = ?, description = ?, quantity = ?, price = ?, category = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setInt(3, p.getQuantity());
            stmt.setDouble(4, p.getPrice());
            stmt.setInt(5, p.getCategoryId());
            stmt.setInt(6, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a categoria:" + e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        
    }
    
    public void delete(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql = "DELETE FROM product WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Categoria excluida com sucesso!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a categoria:" + e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        
    }
}
