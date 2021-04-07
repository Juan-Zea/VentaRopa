package Model.Entity;

import Model.Client.DAO.DAO;
import Model.Client.DTO.DTOClient;
import Model.DATA.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class InventarioDao extends DAO<Inventario> {


    public InventarioDao() {
        super(DataSource.getInstance());
    }

    @Override
    public Collection<Inventario> read() {
        DataSource         dataSource = DataSource.getInstance();
        Inventario             data       = new Inventario();
        ResultSet resultSet  = dataSource.runQuery(data.read());
        Collection<Inventario> inventarioList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                inventarioList.add(getData(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>(inventarioList);
    }

    @Override
    public Inventario findById(DTOClient data) {
        DataSource dataSource = DataSource.getInstance();
        ResultSet  resultSet  = dataSource.runQuery(data.findById());
        Inventario     inventario     = null;
        try {
            while (resultSet.next()) {
                inventario = getData(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return inventario;
    }

    private Inventario getData(ResultSet resultSet) throws SQLException {
        return new Inventario(resultSet.getLong("Id"));              //resultSet.getString(6)
    }
}
