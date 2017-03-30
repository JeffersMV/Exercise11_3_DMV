package com.dmv.sql;

import com.dmv.dao.DaoAbstract;
import com.dmv.dao.DaoException;
import com.dmv.dto.PhotoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class PhotoDAO extends DaoAbstract<PhotoDTO> {

    public PhotoDAO(Connection connection) throws DaoException, SQLException {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name, data, photo FROM photos";
    }

    @Override
    protected List<PhotoDTO> parseResultSet(ResultSet rs) throws DaoException {
        List<PhotoDTO> lst = new LinkedList<>();
        try {
            while (rs.next()) {
                PhotoDTO photoDTO = new PhotoDTO();
                photoDTO.setId(rs.getInt(1));
                photoDTO.setName(rs.getString(2));
                photoDTO.setData(rs.getDate(3));
                photoDTO.setPhoto(rs.getString(4));
                lst.add(photoDTO);
            }
        } catch (Exception sqlE) {
            throw new DaoException(sqlE);
        }
        return lst;
    }
}