package com.dmv.sql;

import com.dmv.dao.DaoAbstract;
import com.dmv.dao.DaoException;
import com.dmv.dto.VideoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class VideoDAO extends DaoAbstract<VideoDTO, Integer> {

    public VideoDAO(Connection connection) throws DaoException, SQLException {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name, data, video FROM videos";
    }

    @Override
    protected List<VideoDTO> parseResultSet(ResultSet rs) throws DaoException {
        List<VideoDTO> lst = new LinkedList<>();
        try {
            while (rs.next()) {
                VideoDTO videoDTO = new VideoDTO();
                videoDTO.setId(rs.getInt(1));
                videoDTO.setName(rs.getString(2));
                videoDTO.setData(rs.getInt(3));
                videoDTO.setVideo(rs.getString(4));
                lst.add(videoDTO);
            }
        } catch (Exception sqlE) {
            throw new DaoException(sqlE);
        }
        return lst;
    }
}