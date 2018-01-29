package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.GuestroomDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.epam.project.hostel.controller.pagination.PageWrapper.MAX_ENTRIES_PER_PAGE;

public class GuestroomDAOImpl extends BaseDAO implements GuestroomDAO {

    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private static final String SELECT_ROOM_BY_ID = "SELECT guestrooms.id,  night_price,  tv,  wifi,  bath,  capacity,  description, hostel_id FROM guestrooms  INNER JOIN tguestrooms t ON guestrooms.id = t.guestrooms_id   INNER JOIN language l ON t.language_id = l.id WHERE guestrooms.id = ? AND language = ?";
    private static final String SELECT_ROOMS_BY_HOSTEL_ID_LIMIT = "SELECT  guestrooms.id,  night_price,  tv,  wifi,  bath,  capacity,  description ,hostel_id FROM guestrooms INNER JOIN tguestrooms t ON guestrooms.id = t.guestrooms_id INNER JOIN language l ON t.language_id = l.id WHERE l.language=? AND hostel_id=? LIMIT ?,?";
    private static final String SELECT_IMAGES_PATH = "SELECT file FROM picture WHERE guestrooms_id = ?";
    private static final String DELETE_RU_EN_ROOM_PART = "DELETE FROM tguestrooms WHERE guestrooms_id = ?";
    private static final String DELETE_ROOM_BY_ID = "DELETE FROM guestrooms WHERE id =?";
    private static final String INSERT_ROOM = "INSERT INTO guestrooms(hostel_id,night_price,tv,wifi,bath,capacity) VALUES (?,?,?,?,?,?)";
    private static final String INSERT_ROOM_DESCRIPTIONS = "INSERT INTO tguestrooms(language_id, guestrooms_id, description) VALUES (?,?,?)";
    private static final String INSERT_ROOM_PICTURE = "INSERT INTO picture(guestrooms_id, file) VALUES (?,?)";
    private static final String SELECT_GUESTROOMS_LIMIT = "SELECT guestrooms.id,  night_price,  tv,  wifi,  bath,  capacity,  description ,hostel_id  FROM guestrooms INNER JOIN tguestrooms t ON guestrooms.id = t.guestrooms_id INNER JOIN language l ON t.language_id = l.id  WHERE language = ? LIMIT ?,?";
    private static final String DELETE_ROOM_PICTURES = "DELETE FROM picture WHERE guestrooms_id = ?";
    private static final String DELETE_TGUESTROOMS_BY_HOSTEL_ID = "DELETE FROM tguestrooms WHERE tguestrooms.guestrooms_id IN (SELECT id FROM guestrooms WHERE hostel_id = ?)";

    @Override
    public List<Guestroom> getGuestroomsByHostelId(int id, String language, int page) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ROOMS_BY_HOSTEL_ID_LIMIT)) {
            ps.setString(1, language);
            ps.setInt(2, id);
            ps.setInt(3, (page - 1) * MAX_ENTRIES_PER_PAGE);
            ps.setInt(4, MAX_ENTRIES_PER_PAGE);
            return createListOfGuestrooms(connection, ps);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting guestrooms by hostel id = " + id + ", page = " + page, e);
        }
    }

    @Override
    public Guestroom getGuestroomById(int id, String language) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ROOM_BY_ID)) {
            ps.setInt(1, id);
            ps.setString(2, language);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                Guestroom guestroom = createGuestroom(rs);
                guestroom.setImgPath(getGuestroomImage(id, connection));
                return guestroom;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting guestrooms by id = " + id, e);
        }
    }

    @Override
    public List<Guestroom> getGuestroomBySearchParam(int currentPage, SearchGuestroomsParams searchParams, String language) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = getSQLSelect(connection, searchParams)) {
            setSearchParametersInPreparedStatement(currentPage, searchParams, language, ps);
            return createListOfGuestrooms(connection, ps);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting guestroom by searching params = " + searchParams.toString(), e);
        }

    }

    @Override
    public Integer getTotalRowCount(SearchGuestroomsParams searchParams, String language) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = getSQLTotalRowCount(connection, searchParams)) {
            setSearchParametersInPreparedStatement(0, searchParams, language, ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("error during getting total row count", e);
        }
    }

    @Override
    public void deleteGuestroomByIdTransaction(Integer guestroomId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_ROOM_BY_ID)) {
            ps.setInt(1, guestroomId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during delete guestroom by id", e);
        }
    }

    @Override
    public void addImage(Integer guestroomId, String imgPath) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO picture(guestrooms_id, file) VALUES (?,?)")) {
            ps.setInt(1, guestroomId);
            ps.setString(2, imgPath);
            ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during adding image into db, guestroomId = " + guestroomId + ", imgPath = " + imgPath, e);
        }
    }

    @Override
    public void deleteImage(Integer imgId) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM picture WHERE id = ?")) {
            ps.setInt(1, imgId);
            ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during delete image", e);
        }
    }

    @Override
    public List<Guestroom> getGuestrooms(String language, Integer currentPage) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_GUESTROOMS_LIMIT)) {
            ps.setString(1, language);
            ps.setInt(2, (currentPage - 1) * MAX_ENTRIES_PER_PAGE);
            ps.setInt(3, MAX_ENTRIES_PER_PAGE);
            return getListGuestrooms(connection, ps);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting guestrooms", e);
        }
    }

    @Override
    public int addGuestroomWithTransaction(Guestroom guestroom) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ROOM, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, guestroom.getHostelId());
            ps.setBigDecimal(2, guestroom.getNightPrice());
            ps.setBoolean(3, guestroom.isTv());
            ps.setBoolean(4, guestroom.isWifi());
            ps.setBoolean(5, guestroom.isBath());
            ps.setInt(6, guestroom.getCapacity());
            ps.executeUpdate();
            return getGuestroomId(ps);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during adding guestroom into db", e);
        }
    }

    @Override
    public void addImageWithTransaction(int guestroomId, List<String> imgPaths) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO picture(guestrooms_id, file) VALUES (?,?)")) {
            for (String imgPath : imgPaths) {
                ps.setInt(1, guestroomId);
                ps.setString(2, imgPath);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("error during adding images path", e);
        }
    }

    @Override
    public void addDescriptionWithTransaction(int guestroomId, String descriptionEn, String descriptionRu) throws DAOException {
        try {
            addRuEnDescriptions(descriptionEn, descriptionRu, connection, guestroomId);
        } catch (SQLException e) {
            throw new DAOException("error during adding description with transaction", e);
        }
    }

    @Override
    public void deleteImagesByGuestroomIdTransaction(Integer guestroomId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM picture WHERE guestrooms_id = ?")) {
            ps.setInt(1, guestroomId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during delete images by guestroom id", e);
        }
    }

    @Override
    public void deleteDescriptionsByGuestroomIdTransaction(Integer guestroomId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE  FROM tguestrooms WHERE guestrooms_id = ?")) {
            ps.setInt(1, guestroomId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during delete guestroom's descriptions", e);
        }
    }

    @Override
    public void deleteGuestroomsDescriptionByHostelIdTransaction(Integer hostelId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TGUESTROOMS_BY_HOSTEL_ID)) {
            ps.setInt(1, hostelId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during deleting guestrooms by hostel id", e);
        }
    }

    @Override
    public void deleteGuestroomsPicturesByHostelIdTransaction(Integer hostelId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM picture WHERE guestrooms_id IN (SELECT guestrooms.id FROM guestrooms INNER JOIN hostel h ON guestrooms.hostel_id = h.id WHERE h.id=?)")) {
            ps.setInt(1, hostelId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during deleting guestrooms pictures by hostel id = " + hostelId, e);
        }
    }

    @Override
    public void deleteGuestroomsByHostelIdTransaction(Integer hostelId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM guestrooms WHERE hostel_id = ?")) {
            ps.setInt(1, hostelId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("error during deleting guestrooms by hostel id = " + hostelId, e);
        }
    }

    @Override
    public List<Guestroom> getGuestroomsByHostelName(String hostelName, Integer currentPage) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT guestrooms.id FROM guestrooms INNER JOIN hostel h ON guestrooms.hostel_id = h.id WHERE h.name=? LIMIT ?,?")) {
            ps.setString(1, hostelName);
            ps.setInt(2, (currentPage - 1) * MAX_ENTRIES_PER_PAGE);
            ps.setInt(3, MAX_ENTRIES_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                List<Guestroom> guestrooms = new ArrayList<>();
                while (rs.next()) {
                    Guestroom guestroom = new Guestroom();
                    guestroom.setId(rs.getInt(1));
                    guestrooms.add(guestroom);
                }
                return guestrooms;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting guestrooms by hostel name", e);
        }
    }

    @Override
    public Integer getTotalRowCount(String hostelName) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT count(*) FROM guestrooms INNER JOIN hostel h ON guestrooms.hostel_id = h.id WHERE h.name = ?")) {
            ps.setString(1, hostelName);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return rs.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("error during getting total row count", e);
        }
    }

    private void addRuEnDescriptions(
            String descriptionEn, String descriptionRu, Connection connection,
            int guestroomId) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(INSERT_ROOM_DESCRIPTIONS)) {
            pst.setInt(1, 2);
            pst.setInt(2, guestroomId);
            pst.setString(3, descriptionRu);
            pst.executeUpdate();
            pst.setInt(1, 1);
            pst.setInt(2, guestroomId);
            pst.setString(3, descriptionEn);
            pst.executeUpdate();
        }
    }

    private int getGuestroomId(PreparedStatement ps) throws SQLException, DAOException {
        int guestroomId;
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                guestroomId = generatedKeys.getInt(1);
            } else {
                throw new DAOException("cannot get id of inserting guestroom");
            }
        }
        return guestroomId;
    }

    private PreparedStatement getSQLTotalRowCount(Connection connection, SearchGuestroomsParams searchParams) throws
            SQLException {
        StringBuilder sqlSelectCount = new StringBuilder("SELECT count(DISTINCT (guestrooms.id)) FROM guestrooms   INNER JOIN tguestrooms t ON guestrooms.id = t.guestrooms_id   INNER JOIN language l ON t.language_id = l.id   INNER JOIN hostel h ON guestrooms.hostel_id = h.id   INNER JOIN thostel t2 ON h.id = t2.hostel_id WHERE language = ? ");
        String city = searchParams.getCity();
        LocalDate dateFrom = searchParams.getDateFrom();
        LocalDate dateTo = searchParams.getDateTo();
        Boolean wifi = searchParams.getWifi();
        Boolean tv = searchParams.getTv();
        Boolean shower = searchParams.getShower();
        Integer capacityFrom = searchParams.getCapacityFrom();
        Integer capacityTo = searchParams.getCapacityTo();
        BigDecimal nightPriceTo = searchParams.getNightPriceTo();
        BigDecimal nightPriceFrom = searchParams.getNightPriceFrom();

        if (city != null && !city.equals("any")) {
            sqlSelectCount.append(" AND t2.city = ? ");
        }

        if (nightPriceFrom != null && nightPriceTo != null) {
            sqlSelectCount.append(" AND (guestrooms.night_price BETWEEN ? AND ?) ");
        }
        if (dateFrom != null && dateTo != null) {
            sqlSelectCount.append(" AND guestrooms.id NOT IN (SELECT DISTINCT guestrooms_id FROM bookings AS b  WHERE (? >= b.last_day AND ? >= b.start_day) OR (? <= b.last_day AND ? <= b.start_day)) ");
        }
        if (wifi) {
            sqlSelectCount.append("  AND wifi=1 ");
        }
        if (tv) {
            sqlSelectCount.append(" AND tv=1 ");
        }
        if (shower) {
            sqlSelectCount.append(" AND bath=1 ");
        }
        if (capacityFrom != null && capacityTo != null) {
            sqlSelectCount.append(" and (capacity BETWEEN ? and ?) ");
        }
        return connection.prepareStatement(sqlSelectCount.toString());
    }

    private PreparedStatement getSQLSelect(Connection connection, SearchGuestroomsParams searchParams) throws
            SQLException {
        StringBuilder selectByParams = new StringBuilder("SELECT DISTINCT(guestrooms.id),   night_price,   tv,   wifi,   bath,   capacity,   t.description,   guestrooms.hostel_id FROM guestrooms   INNER JOIN tguestrooms t ON guestrooms.id = t.guestrooms_id   INNER JOIN language l ON t.language_id = l.id   INNER JOIN hostel h ON guestrooms.hostel_id = h.id   INNER JOIN thostel t2 ON h.id = t2.hostel_id WHERE language = ?  ");

        String city = searchParams.getCity();
        LocalDate dateFrom = searchParams.getDateFrom();
        LocalDate dateTo = searchParams.getDateTo();
        String sorting = searchParams.getSorting();
        Boolean wifi = searchParams.getWifi();
        Boolean tv = searchParams.getTv();
        Boolean shower = searchParams.getShower();
        String direction = searchParams.getOrder();
        BigDecimal nightPriceTo = searchParams.getNightPriceTo();
        BigDecimal nightPriceFrom = searchParams.getNightPriceFrom();
        Integer capacityFrom = searchParams.getCapacityFrom();
        Integer capacityTo = searchParams.getCapacityTo();


        if (city != null && !city.equals("any")) {
            selectByParams.append(" AND t2.city = ? ");
        }

        if (nightPriceFrom != null && nightPriceTo != null) {
            selectByParams.append(" AND (guestrooms.night_price BETWEEN ? AND ?) ");
        }

        if (dateFrom != null && dateTo != null) {
            selectByParams.append(" AND guestrooms.id NOT IN (SELECT DISTINCT guestrooms_id FROM bookings AS b  WHERE (? >= b.last_day AND ? >= b.start_day) OR (? <= b.last_day AND ? <= b.start_day)) ");
        }
        if (wifi) {
            selectByParams.append("  AND wifi=1 ");
        }
        if (tv) {
            selectByParams.append(" AND tv=1 ");
        }
        if (shower) {
            selectByParams.append(" AND bath=1 ");
        }
        if (capacityFrom != null && capacityTo != null) {
            selectByParams.append(" and (capacity BETWEEN ? and ?) ");
        }

        if (sorting != null) {
            switch (sorting) {
                case "nightPrice":
                    selectByParams.append(" ORDER BY guestrooms.night_price ");
                    break;
                case "capacity":
                    selectByParams.append(" ORDER BY capacity ");
                    break;
                case "none":
                    break;
            }
        }
        if (sorting != null && !sorting.equals("none") && direction != null) {
            switch (direction) {
                case "ascending":
                    selectByParams.append(" ASC ");
                    break;
                case "descending":
                    selectByParams.append(" DESC ");
                    break;
            }
        }
        selectByParams.append(" LIMIT ?,?");

        return connection.prepareStatement(selectByParams.toString());
    }

    private void setSearchParametersInPreparedStatement(
            int currentPage, SearchGuestroomsParams
            searchParams, String language, PreparedStatement ps) throws SQLException {
        int numberParameter = 1;

        String city = searchParams.getCity();
        LocalDate dateFrom = searchParams.getDateFrom();
        LocalDate dateTo = searchParams.getDateTo();
        BigDecimal nightPriceFrom = searchParams.getNightPriceFrom();
        BigDecimal nightPriceTo = searchParams.getNightPriceTo();
        Integer capacityFrom = searchParams.getCapacityFrom();
        Integer capacityTo = searchParams.getCapacityTo();

        ps.setString(numberParameter++, language);
        if (city != null && !city.equals("any")) {
            ps.setString(numberParameter++, city);
        }
        if (nightPriceFrom != null && nightPriceTo != null) {
            ps.setBigDecimal(numberParameter++, nightPriceFrom);
            ps.setBigDecimal(numberParameter++, nightPriceTo);
        }


        if (dateFrom != null && dateTo != null) {
            ps.setDate(numberParameter++, Date.valueOf(dateFrom));
            ps.setDate(numberParameter++, Date.valueOf(dateTo));
            ps.setDate(numberParameter++, Date.valueOf(dateFrom));
            ps.setDate(numberParameter++, Date.valueOf(dateTo));
        }
        if (capacityFrom != null && capacityTo != null) {
            ps.setInt(numberParameter++, capacityFrom);
            ps.setInt(numberParameter++, capacityTo);
        }

        if (currentPage != 0) {
            ps.setInt(numberParameter++, (currentPage - 1) * MAX_ENTRIES_PER_PAGE);
            ps.setInt(numberParameter, MAX_ENTRIES_PER_PAGE);
        }
    }

    private List<Guestroom> createListOfGuestrooms(Connection connection, PreparedStatement ps) throws
            SQLException, DAOException {
        return getListGuestrooms(connection, ps);
    }

    private List<Guestroom> getListGuestrooms(Connection connection, PreparedStatement ps) throws
            SQLException, DAOException {
        try (ResultSet rs = ps.executeQuery()) {
            List<Guestroom> guestrooms = new ArrayList<>();
            while (rs.next()) {
                guestrooms.add(createGuestroom(rs));
            }
            for (Guestroom guestroom : guestrooms) {
                guestroom.setImgPath(getGuestroomImage(guestroom.getId(), connection));
            }
            return guestrooms;
        }
    }

    private Guestroom createGuestroom(ResultSet rs) throws SQLException {
        Guestroom guestroom = new Guestroom();
        guestroom.setId(rs.getInt(1));
        guestroom.setNightPrice(rs.getBigDecimal(2));
        guestroom.setTv(rs.getInt(3));
        guestroom.setWifi(rs.getInt(4));
        guestroom.setBath(rs.getInt(5));
        guestroom.setCapacity(rs.getInt(6));
        guestroom.setDescription(rs.getString(7));
        guestroom.setHostelId(rs.getInt(8));
        return guestroom;
    }

    //todo this method remade in inner join
    private List<String> getGuestroomImage(int id, Connection connection) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_IMAGES_PATH)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                List<String> images = new ArrayList<>();
                while (rs.next()) {
                    images.add(rs.getString(1));
                }
                return images;
            }
        } catch (SQLException e) {
            throw new DAOException("error during getting guestroom images", e);
        }
    }

    private void addPicturePath(Guestroom guestroom, Connection connection, int guestroomId) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(INSERT_ROOM_PICTURE)) {
            pst.setInt(1, guestroomId);
            pst.setString(2, guestroom.getImgPath().get(0));
            pst.executeUpdate();
        }
    }

    @Override
    protected String getTableName() {
        return "guestrooms";
    }

}
