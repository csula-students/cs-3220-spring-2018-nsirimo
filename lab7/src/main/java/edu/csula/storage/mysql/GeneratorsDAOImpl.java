package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class GeneratorsDAOImpl implements GeneratorsDAO {
    private final Database context;

    protected static final String getAllQuery = "SELECT * FROM generators;";
    protected static final String getByIdQuery = "SELECT * FROM generators WHERE id = ?";
    protected static final String setQuery = "UPDATE generators SET name=?, description=?, rate=?, base_cost=?, unlock_at=? WHERE id=?;";
    protected static final String addQuery = "INSERT INTO generators VALUES (?, ?, ?, ?, ?, ?)";
    protected static final String removeQuery = "DELETE FROM generators WHERE id=?;";

    public GeneratorsDAOImpl(Database context) {
        this.context = context;
    }

    @Override
    public List<Generator> getAll() {
        List<Generator> result = new ArrayList<>();
        try (Connection c = context.getConnection(); Statement statem = c.createStatement()) {
            ResultSet rs = statem.executeQuery(getAllQuery);
            while (rs.next()) {
                Generator generator = new Generator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6));
                result.add(generator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public Optional<Generator> getById(int id) {
        try (Connection c = context.getConnection();) {
            PreparedStatement ps = c.prepareStatement(getByIdQuery);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return Optional.of(new Generator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
                    rs.getInt(6)));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void set(int id, Generator generator) {
        try (Connection c = context.getConnection();) {
            PreparedStatement ps = c.prepareStatement(setQuery);
            ps.setString(1, generator.getName());
            ps.setString(2, generator.getDescription());
            ps.setInt(3, generator.getRate());
            ps.setInt(4, generator.getBaseCost());
            ps.setInt(5, generator.getUnlockAt());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Generator generator) {
        try (Connection c = context.getConnection();) {
            PreparedStatement ps = c.prepareStatement(addQuery);
            ps.setInt(1, generator.getId());
            ps.setString(2, generator.getName());
            ps.setString(3, generator.getDescription());
            ps.setInt(4, generator.getRate());
            ps.setInt(5, generator.getBaseCost());
            ps.setInt(6, generator.getUnlockAt());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (Connection c = context.getConnection();) {
            PreparedStatement ps = c.prepareStatement(removeQuery);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
