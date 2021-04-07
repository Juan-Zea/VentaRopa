package Model.Client.DAO;

import Model.Client.DTO.DTOClient;
import Model.DATA.DataSource;

import java.util.Collection;

public abstract class DAO <T extends DTOClient>{
    private final DataSource dataSource;

    protected DAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean insert(DTOClient data) {
        return dataSource.runUpdateQuery(data.insert());
    }

    public abstract Collection<T> read();

    public boolean update(DTOClient data) {
        return dataSource.runUpdateQuery(data.update());
    }

    public boolean delete(DTOClient data) {
        return dataSource.runUpdateQuery(data.delete());
    }

    public abstract T findById(DTOClient data);
}
